package com.zhurawell.base.api.security.filters;

import com.zhurawell.base.api.constants.RestHeaders;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtVerificationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        String login = servletRequest.getHeader(RestHeaders.LOGIN_HEADER);
        String authorities = servletRequest.getHeader(RestHeaders.AUTHORITIES_HEADER);
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();

        if(StringUtils.isNotEmpty(authorities)) {
            simpleGrantedAuthorities= Arrays.stream(authorities.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());;
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(login, null, simpleGrantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
