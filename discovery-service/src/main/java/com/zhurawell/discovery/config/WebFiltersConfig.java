//package com.app.art.registry.config;
//
//import com.app.art.registry.filters.LoggingFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebFiltersConfig {
//
//    @Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter(){
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoggingFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(2);
//
//        return registrationBean;
//    }
//
//}
