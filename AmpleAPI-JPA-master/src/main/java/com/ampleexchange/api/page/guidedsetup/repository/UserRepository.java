package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ampleexchange.api.page.guidedsetup.model.User;

/**
 * @author JG
 * @date 2018/11/22
 */
public interface UserRepository extends JpaRepository<User,UUID> {
	@Query("FROM User u WHERE u.user_email = :email")
    public Set<User> findByEmail(@Param("email") String email);
}
