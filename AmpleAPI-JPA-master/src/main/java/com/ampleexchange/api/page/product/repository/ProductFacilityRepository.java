package com.ampleexchange.api.page.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ampleexchange.api.page.product.model.Facility;

public interface ProductFacilityRepository extends JpaRepository<Facility, UUID> {

	@Query(value = "select * from facility where facility_deleted = 'false' ", nativeQuery = true)
	List<Facility> retrieveFacilities();
}
