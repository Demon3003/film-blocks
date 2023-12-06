package com.zhurawell.base.api.controllers.authorization;

import com.zhurawell.base.api.dto.jwt.JwtResponseDto;
import com.zhurawell.base.security.jwt.JwtTokenProvider;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity login(@RequestBody User user) {
        User usr = userRepository.findByEmailOrLogin(user.getEmail(), user.getLogin()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usr.getLogin(), usr.getPassword()));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(usr.getLogin());
        return ResponseEntity.ok(JwtResponseDto.builder()
                .login(usr.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

    @GetMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestParam("token") String token) {
        jwtTokenProvider.validateRefreshToken(token);
        String login = jwtTokenProvider.getUsernameFromRefreshToken(token);
        User usr = userRepository.findByEmailOrLogin(login, login).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(usr.getLogin());
        return ResponseEntity.ok(JwtResponseDto.builder()
                .login(usr.getLogin())
                .accessToken(tokens.getFirst())
                .refreshToken(tokens.getSecond())
                .build());
    }

}
