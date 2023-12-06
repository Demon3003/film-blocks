package com.zhurawell.base.api.configurations;

import com.zhurawell.base.api.converters.GenericBigDecimalConverter;
import com.zhurawell.base.api.converters.UserRestConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserRestConverter());
        registry.addConverter(new GenericBigDecimalConverter());
    }

}
