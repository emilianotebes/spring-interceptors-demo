package com.etsoft.interceptors

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class FilterConfig {
    @Bean
    fun perfFilter(): FilterRegistrationBean<LoggingFilter> {
        val registration: FilterRegistrationBean<LoggingFilter> = FilterRegistrationBean()
        registration.filter = LoggingFilter();
        registration.addUrlPatterns("/*");
        return registration;
    }
}