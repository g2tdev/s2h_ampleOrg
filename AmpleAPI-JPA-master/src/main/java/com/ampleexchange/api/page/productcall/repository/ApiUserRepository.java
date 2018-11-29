package com.ampleexchange.api.page.productcall.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.productcall.model.ApiUser;

public interface ApiUserRepository extends JpaRepository<ApiUser,UUID> {

}
