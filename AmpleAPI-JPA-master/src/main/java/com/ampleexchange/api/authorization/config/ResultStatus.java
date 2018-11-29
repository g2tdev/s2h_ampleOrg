package com.ampleexchange.api.authorization.config;

/**
 * @author JL
 * @date 2018/10/29.
 */
public enum ResultStatus {
    SUCCESS(100, "Success"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "ApiUser name or Password error!"),
    USER_NOT_FOUND(-1002, "No such user!"),
    USER_NOT_LOGIN(-1003, "Not logged in!"),
    
    FILE_NOT_EXIST(-1010, "File doesnot exist!"),
  	MISSING_PARAMETER(-1011, "Missing parameter!");

    private int code;

    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
