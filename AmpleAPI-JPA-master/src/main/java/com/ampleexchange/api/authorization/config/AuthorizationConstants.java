package com.ampleexchange.api.authorization.config;

/**
 * AuthorizationConstants
 * @author JL
 * @date 2018/10/31
 */
public class AuthorizationConstants {

    /**
     * Field Name for Current UserID
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    public static final String USER_NAME = "USER_NAME";
    public static final String USER_AGE = "USER_AGE";
    public static final String USER_SEX = "USER_SEX";

    
    public static final long DUMMY_USER_ID = -1l;

    /**
     * token Expiration(Minutes After)
     */
    public static final int TOKEN_EXPIRES_MINS = 10;

    /**
     * Field Name for Authorization in header
     */
    public static final String AUTHORIZATION = "Authorization";
    public static final String API_VERSION = "ApiVersion";   
    public static final String CONTENT_TYPE = "Content-Type"; 
    public static final String ACCCEPT_HEADER = "Accept"; 
    public static final String VALID_CONTENT_TYPE_JSON = "application/json";
    public static final String VALID_CONTENT_TYPE_FILE = "multipart/form-data"; 

}
