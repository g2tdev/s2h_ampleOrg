package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ampleexchange.api.page.guidedsetup.model.Facility;

/**
 * @author JG
 * @date 2018/11/23
 */
public interface FacilityRepository extends JpaRepository<Facility,UUID> {
	@Query("FROM Facility f WHERE f.org_id.org_id = :orgId")
    public Set<Facility> findByOrgId(@Param("orgId") UUID orgId);
}
