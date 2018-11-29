package com.ampleexchange.api.authorization.manager;

import com.ampleexchange.api.authorization.model.TokenModel;

/**
 * @author JL
 * @date 2018/10/31.
 */
public interface TokenManager {

    public TokenModel createToken(long userId);

    public boolean checkToken(TokenModel model);

    /**
     * Check if duplicate Login
     * @param long userId
     */
    public TokenModel duplicateLogin(long uId);
    public TokenModel getToken(String authentication);

    public void deleteToken(long userId);

}
