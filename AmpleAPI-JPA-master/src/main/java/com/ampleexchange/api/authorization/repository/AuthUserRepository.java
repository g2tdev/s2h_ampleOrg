package com.ampleexchange.api.authorization.repository;

import org.springframework.data.repository.CrudRepository;

import com.ampleexchange.api.authorization.model.ApiUser;

/**
 * @see com.ampleexchange.api.page.guidedsetup.model.jl.ApiUser.User
 * @author JL
 * @date 2018/10/31.
 */
public interface AuthUserRepository extends CrudRepository<ApiUser, Long> {

    public ApiUser findByUsername(String username);
}
