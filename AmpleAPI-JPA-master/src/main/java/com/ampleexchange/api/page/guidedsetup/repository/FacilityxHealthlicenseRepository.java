package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ampleexchange.api.page.guidedsetup.model.FacilityxHealthlicense;

/**
 * @author JG
 * @date 2018/11/23
 */
public interface FacilityxHealthlicenseRepository extends JpaRepository<FacilityxHealthlicense,UUID> {
	@Query("FROM FacilityxHealthlicense fh WHERE fh.facility_id.facility_id = :facilityId and fh.healthlicensetype_id.healthlicensetype_id = :typeId")
    public FacilityxHealthlicense findByFacilityType(@Param("facilityId") UUID facilityId, @Param("typeId") UUID typeId);
	
	@Query("FROM FacilityxHealthlicense fh WHERE fh.facility_id.facility_id = :facilityId")
    public Set<FacilityxHealthlicense> findByFacility(@Param("facilityId") UUID facilityId);
}
