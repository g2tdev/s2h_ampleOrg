package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.guidedsetup.model.Organization;

/**
 * @author JG
 * @date 2018/11/22
 */
public interface OrganizationRepository extends JpaRepository<Organization,UUID> {

}
