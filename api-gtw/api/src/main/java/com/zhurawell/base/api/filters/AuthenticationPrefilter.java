package com.zhurawell.base.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurawell.base.api.constants.RestHeaders;
import com.zhurawell.base.api.constants.ServiceUrl;
import com.zhurawell.base.api.dto.grants.Authorities;
import com.zhurawell.base.api.dto.jwt.JwtResponseDto;
import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.intregration.broker.client.UserBrokerClient;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class AuthenticationPrefilter extends AbstractGatewayFilterFactory<AuthenticationPrefilter.Config> {

    @Autowired
    @Qualifier("excludedUrls")
    private List<String> excludedUrls;

    private final WebClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${jwt.header}")
    private String AUTHORIZATION_HEADER;

    @Autowired
    private UserBrokerClient userBrokerClient;


    public Predicate<ServerHttpRequest> isSecured = request -> excludedUrls.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

    @Autowired
    public AuthenticationPrefilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClient=webClientBuilder.build();
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("URL is - " + request.getURI().getPath());
            String token = request.getHeaders().getFirst(AUTHORIZATION_HEADER);
            log.info("Access token: "+ token);
            //TEST
            if(request.getURI().getPath().contains("createUser")) {
                UserDto user = new UserDto();
                user.setEmail("dhjcbjhdc@dsd.com");
                user.setFirstName("Test");
                user.setLastName("Tester");
                user.setRegistrationDate(LocalDate.now());
                userBrokerClient.createUser(user);
                return chain.filter(exchange);
            }
            //TEST
            if(isSecured.test(request)) { // TODO remove isSecured, instead use FilterRegistrationBean
                return webClient.get()
                        .uri(ServiceUrl.VALIDATE_TOKEN)
                        .header(AUTHORIZATION_HEADER, token)
                        .retrieve().bodyToMono(JwtResponseDto.class)
                        .map(response -> {
                            exchange.getRequest().mutate().header(RestHeaders.LOGIN_HEADER, response.getLogin());
                            exchange.getRequest().mutate().header(RestHeaders.AUTHORITY_HEADER, response.getAuthorities().stream().map(Authorities::getAuthority).reduce("", (a, b) -> a + "," + b));
                            return exchange;
                        }).flatMap(chain::filter)
                        .onErrorResume(error -> {
                            log.info("Error Happened:", error);
                            HttpStatus errorCode = null;
                            String errorMsg = "";
                            if (error instanceof WebClientResponseException) {
                                WebClientResponseException webCLientException = (WebClientResponseException) error;
                                errorCode = webCLientException.getStatusCode();
                                errorMsg = webCLientException.getStatusText();

                            } else {
                                errorCode = HttpStatus.BAD_GATEWAY;
                                errorMsg = HttpStatus.BAD_GATEWAY.getReasonPhrase();
                            }
//                            AuthorizationFilter.AUTH_FAILED_CODE
                            return onError(exchange, String.valueOf(errorCode.value()) ,errorMsg, "JWT Authentication Failed", errorCode);
                        });
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errCode, String err, String errDetails, HttpStatus httpStatus) {
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
//        try {
//            response.getHeaders().add("Content-Type", "application/json");
////            ExceptionResponseModel data = new ExceptionResponseModel(errCode, err, errDetails, null, new Date());
//            byte[] byteData = objectMapper.writeValueAsBytes(data);
//            return response.writeWith(Mono.just(byteData).map(t -> dataBufferFactory.wrap(t)));
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//
//        }
        return response.setComplete();
    }

    @NoArgsConstructor
    public static class Config {


    }
}
