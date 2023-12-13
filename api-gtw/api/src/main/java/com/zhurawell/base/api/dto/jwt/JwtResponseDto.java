package com.zhurawell.base.api.dto.jwt;

import com.zhurawell.base.api.dto.grants.Authorities;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponseDto {

    private String login;

    private String accessToken;

    private String refreshToken;

    private List<Authorities> authorities;

    @Override
    public String toString() {
        return "JwtResponse{" +
                "login='" + login + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
