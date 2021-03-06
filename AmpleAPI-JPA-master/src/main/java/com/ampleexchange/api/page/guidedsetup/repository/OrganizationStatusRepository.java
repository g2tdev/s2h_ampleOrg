package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.guidedsetup.model.OrganizationStatus;

/**
 * @author JG
 * @date 2018/11/26
 */
public interface OrganizationStatusRepository extends JpaRepository<OrganizationStatus,UUID> {

}
