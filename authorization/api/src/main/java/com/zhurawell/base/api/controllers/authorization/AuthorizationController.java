package com.zhurawell.base.api.controllers.authorization;

import com.zhurawell.base.api.dto.jwt.JwtResponseDto;
import com.zhurawell.base.api.dto.jwt.JwtTokenDto;
import com.zhurawell.base.api.dto.user.UserLoginDto;
import com.zhurawell.base.api.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;

@Slf4j
@RestController
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final Scheduler jdbcScheduler;

    @Autowired
    public AuthorizationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                   Scheduler jdbcScheduler) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jdbcScheduler = jdbcScheduler;
    }

    @PostMapping("/login")     //TODO Add logout. Add token black list
    public Mono<JwtResponseDto> login(@RequestBody UserLoginDto user) {
        return Mono
            .fromCallable(() ->
            {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
                return jwtTokenProvider.createAccessAndRefreshTokens(user.getLogin());
            })
            .map(tokens -> JwtResponseDto.builder()
                .login(user.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build())
            .subscribeOn(jdbcScheduler);
    }

    @PostMapping("/refreshToken")
    public Mono<JwtResponseDto> refreshToken(@RequestBody JwtTokenDto tokenDto) {
        return Mono
                .fromCallable(() ->
                {
                    jwtTokenProvider.validateRefreshToken(tokenDto.getToken());
                    return jwtTokenProvider.createAccessAndRefreshTokens(jwtTokenProvider.getUsernameFromRefreshToken(tokenDto.getToken()));
                })
                .map(tokens -> JwtResponseDto.builder()
                        .login(jwtTokenProvider.getUsernameFromRefreshToken(tokenDto.getToken()))
                        .accessToken(tokens.getFirst())
                        .refreshToken(tokens.getSecond())
                        .build())
                .subscribeOn(jdbcScheduler);
    }

    @GetMapping("/validateToken")
    public Mono<JwtResponseDto> validateToken(@RequestHeader("Authorization") String token) {
        return Mono
                .fromCallable(() ->
                        SecurityContextHolder.getContext().getAuthentication())
                .map(auth -> JwtResponseDto.builder()
                        .accessToken(token)
                        .login(auth.getName())
                        .authorities((List<GrantedAuthority>) auth.getAuthorities())
                        .build())
                .subscribeOn(jdbcScheduler);

    }

}
