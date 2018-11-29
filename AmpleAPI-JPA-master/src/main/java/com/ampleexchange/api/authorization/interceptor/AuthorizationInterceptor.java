package com.ampleexchange.api.authorization.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ampleexchange.api.authorization.annotation.Authorization;
import com.ampleexchange.api.authorization.config.AuthorizationConstants;
import com.ampleexchange.api.authorization.manager.TokenManager;
import com.ampleexchange.api.authorization.model.TokenModel;
import com.ampleexchange.api.util.StringHelper;

/**
 * @author JL
 * @date 2018/10/30.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if (!(handler instanceof HandlerMethod)) {
            return true;
        } 
        
    		if (! hasValidAcceptType(request)){
    			//if doesnot have a valid Accept type, return SC_NOT_ACCEPTABLE
    			// Servers MUST respond with a 406 Not Acceptable status code if a request’s Accept header contains the JSON:API media type and all instances of that media type are modified with media type parameters
          response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);			 
          return false;
    		}
    		if (! hasValidContentType(request)){
    			//if doesnot have a valid ContentType, return SC_UNSUPPORTED_MEDIA_TYPE
          response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);			 
          return false;
    		}
    		
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(Authorization.class) != null) {
	        //get token from Header
	        String authorization = request.getHeader(AuthorizationConstants.AUTHORIZATION);
	        if (authorization != null){
	        	try{
			        //Verify token
			        TokenModel model = manager.getToken(authorization);
			        if (manager.checkToken(model)) {
	        			//if success, save userId into request and return true
		            request.setAttribute(AuthorizationConstants.CURRENT_USER_ID, model.getUserId());
		            return true;
			        }
	        	} catch (Exception ex){ // If exception while checking Token, then return 'SC_PROXY_AUTHENTICATION_REQUIRED'
	            response.setStatus(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);
	            return false;        		
	        	}
	        }
	        //Failed to verify token and its Annotation is Authorization, return 401
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return false;
        } else {
          request.setAttribute(AuthorizationConstants.CURRENT_USER_ID, AuthorizationConstants.DUMMY_USER_ID);  // This code should be removed once Security is in placed    	
        }
        return true; // return true if its Annotation is not Authorization
    }
    
    protected boolean hasValidAcceptType(HttpServletRequest request){
    	boolean retVal = false;
      //String aType = request.getHeader(AuthorizationConstants.ACCCEPT_HEADER);
      // Just bypass this verification cause this verification should be on API level - JL
    	//if (AuthorizationConstants.VALID_CONTENT_TYPE.equalsIgnoreCase(aType))
    		retVal = true;
    	return retVal;
    }
    protected boolean hasValidContentType(HttpServletRequest request){
    	boolean retVal = false;
      String cType = request.getHeader(AuthorizationConstants.CONTENT_TYPE);
       	if (!StringHelper.isEmpty(cType) && cType.startsWith(AuthorizationConstants.VALID_CONTENT_TYPE_JSON))
    		retVal = true;
    	return retVal;
    }
}
