package com.ampleexchange.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ampleexchange.api.common.MyConstants;
import com.ampleexchange.api.exception.BaseException;

@RestController
@ControllerAdvice // Global Exception Handling
public class GlobalExceptionHandler {
 
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Object baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return e.getMessage();
    }
 
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        //return JSONObject.toJSONString("Unrecognized URL format");        
        JsonObject jObject = new JsonObject();
        jObject.addProperty(MyConstants.RETURN_MESSAGE_TITLE,"Unrecognized URL format");
        return jObject;
    }
}