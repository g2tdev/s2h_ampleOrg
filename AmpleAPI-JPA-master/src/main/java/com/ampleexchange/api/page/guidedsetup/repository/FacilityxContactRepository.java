package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ampleexchange.api.page.guidedsetup.model.FacilityxContact;

/**
 * @author JG
 * @date 2018/11/23
 */
public interface FacilityxContactRepository extends JpaRepository<FacilityxContact,UUID> {
	@Query("FROM FacilityxContact fc WHERE fc.facility_id.facility_id = :facilityId and fc.contact_id.contact_id = :contactId")
    public FacilityxContact findByFacilityContact(@Param("facilityId") UUID facilityId, @Param("contactId") UUID contactId);
	
	@Query("FROM FacilityxContact fc WHERE fc.facility_id.facility_id = :facilityId")
    public Set<FacilityxContact> findByFacility(@Param("facilityId") UUID facilityId);
}
