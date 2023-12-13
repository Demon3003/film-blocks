package com.zhurawell.base.api.dto.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class JwtResponseDto {

    private String login;

    private String accessToken;

    private String refreshToken;

    private List<GrantedAuthority> authorities;

    @Override
    public String toString() {
        return "JwtResponse{" +
                "login='" + login + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
