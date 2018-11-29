package com.ampleexchange.api.page.common.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.common.model.Constant;

/**
 * @author JL
 * @date 2018/11/19
 */
public interface ConstantRepository extends JpaRepository<Constant,UUID> {

}
