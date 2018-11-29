package com.ampleexchange.api.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see com.jl.authorization.resolvers.NewUserMethodArgumentResolver
 * @author JL
 * @date 2018/11/08
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewUser {
}
