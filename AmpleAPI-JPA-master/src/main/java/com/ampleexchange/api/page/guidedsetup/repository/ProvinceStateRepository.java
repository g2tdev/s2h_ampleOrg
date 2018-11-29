package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.guidedsetup.model.ProvinceState;

/**
 * @author JG
 * @date 2018/11/26
 */
public interface ProvinceStateRepository extends JpaRepository<ProvinceState,UUID> {

}
