package com.ampleexchange.api.authorization.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ampleexchange.api.authorization.interceptor.AuthorizationInterceptor;
import com.ampleexchange.api.authorization.resolvers.CurrentUserMethodArgumentResolver;

/**
 * @see com.jl.authorization.resolvers.CurrentUserMethodArgumentResolver
 * @see com.jl.authorization.interceptor.AuthorizationInterceptor
 * @author JL
 * @date 2018/10/30.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }
}
