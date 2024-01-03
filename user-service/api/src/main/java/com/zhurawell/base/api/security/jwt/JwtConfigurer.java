package com.zhurawell.base.api.security.jwt;

import com.zhurawell.base.api.security.filters.JwtVerificationFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity httpSecurity) {
        httpSecurity.addFilterBefore(new JwtVerificationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
