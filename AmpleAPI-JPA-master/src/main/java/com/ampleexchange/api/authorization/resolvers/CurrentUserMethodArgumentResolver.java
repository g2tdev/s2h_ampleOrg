package com.ampleexchange.api.authorization.resolvers;

import com.ampleexchange.api.authorization.annotation.CurrentUser;
import com.ampleexchange.api.authorization.config.AuthorizationConstants;
import com.ampleexchange.api.authorization.model.ApiUser;
import com.ampleexchange.api.authorization.repository.AuthUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * @see com.jl.authorization.annotation.CurrentUser
 * @author JL
 * @date 2018/10/31.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private AuthUserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().isAssignableFrom(ApiUser.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Long currentUserId = (Long) webRequest.getAttribute(AuthorizationConstants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null ) {
        	if (currentUserId != AuthorizationConstants.DUMMY_USER_ID)
            return userRepository.findById(currentUserId).get(); // V1.5.6 userRepository.findOne(currentUserId);
        	else
        		return new ApiUser();  // JamesLee - This code should be removed after the security is in placed
        }
        throw new MissingServletRequestPartException(AuthorizationConstants.CURRENT_USER_ID);
    }
}
