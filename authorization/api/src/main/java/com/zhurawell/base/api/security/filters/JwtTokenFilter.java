package com.zhurawell.base.api.security.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurawell.base.api.dto.error.ErrorDto;
import com.zhurawell.base.api.security.jwt.JwtTokenProvider;
import com.zhurawell.blocks.common.utils.exception.BuilderRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String authorizationHeader;

    private final JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper mapper = new ObjectMapper();

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = request.getHeader(authorizationHeader);
        try {
            if (token != null) {
                jwtTokenProvider.validateAccessToken(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (BuilderRuntimeException ex) {
            log.error("Error during token processing", ex);
            SecurityContextHolder.clearContext();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write(convertErrorToJson(ex));
            response.getWriter().close();
        }
        filterChain.doFilter(request, response);
    }

    protected String convertErrorToJson(BuilderRuntimeException ex) throws JsonProcessingException {
        if (ex == null) {
            return null;
        }
        return mapper.writeValueAsString(new ErrorDto(ex.getCode(), ex.getMessage()));
    }
}