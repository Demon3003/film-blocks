package com.zhurawell.base.api.controllers.authorization;

import com.zhurawell.base.api.dto.jwt.JwtResponseDto;
import com.zhurawell.base.api.dto.jwt.JwtTokenDto;
import com.zhurawell.base.api.dto.user.UserLoginDto;
import com.zhurawell.base.api.security.jwt.JwtTokenProvider;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    @Autowired
    public AuthorizationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")     //TODO Add logout. Add token black list
    public ResponseEntity login(@RequestBody UserLoginDto user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(user.getLogin());
        return ResponseEntity.ok(JwtResponseDto.builder()
                .login(user.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

    @PostMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestBody JwtTokenDto tokenDto) {
        jwtTokenProvider.validateRefreshToken(tokenDto.getToken());
        String login = jwtTokenProvider.getUsernameFromRefreshToken(tokenDto.getToken());
        User usr = userRepository.findByEmailOrLogin(login, login).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(usr.getLogin());
        return ResponseEntity.ok(JwtResponseDto.builder()
                .login(usr.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

    @GetMapping("/validateToken")
    public ResponseEntity validateToken(@RequestHeader("Authorization") String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(JwtResponseDto.builder()
                .accessToken(token)
                .login(auth.getName())
                .grantedAuthorities((List<GrantedAuthority>) auth.getAuthorities())
                .build());
    }

}
