package com.ampleexchange.api.authorization.manager.impl;

import com.ampleexchange.api.authorization.manager.TokenManager;
import com.ampleexchange.api.authorization.model.TokenModel;
import com.ampleexchange.api.authorization.config.AuthorizationConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @see com.jl.authorization.manager.TokenManager
 * @author JL
 * @date 2018/10/31.
 */
@Component
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<Long, String> redis;

    @Autowired // AutoWire进行注入的时候，默认 按照Type进行注入. 当同一个Type存在多个bean的时候，又会按照Name进行注入(注入bean的id和field name相同的bean)
    public void setRedis( RedisTemplate redisTemplate) {
        this.redis = redisTemplate;
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public TokenModel createToken(long userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        redis.boundValueOps(userId).set(token, AuthorizationConstants.TOKEN_EXPIRES_MINS, TimeUnit.MINUTES);
        return model;
    }


    public TokenModel duplicateLogin(long uId) {
    	TokenModel model = null;
      if (uId <=0 ) {
          return model;
      }
      String token = redis.boundValueOps(uId).get();
      if (token == null) {
          return model;
      }
      model = new TokenModel(uId, token);      
      //It is duplicateLogin, just reset it
      redis.boundValueOps(uId).expire(AuthorizationConstants.TOKEN_EXPIRES_MINS, TimeUnit.MINUTES);
      return model;
    }
    
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        redis.boundValueOps(model.getUserId()).expire(AuthorizationConstants.TOKEN_EXPIRES_MINS, TimeUnit.MINUTES);
        return true;
    }

    public void deleteToken(long userId) {
        redis.delete(userId);
    }
}
