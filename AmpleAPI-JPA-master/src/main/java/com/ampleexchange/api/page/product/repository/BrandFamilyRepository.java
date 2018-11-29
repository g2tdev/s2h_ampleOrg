package com.ampleexchange.api.page.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ampleexchange.api.page.product.model.Brandfamily;

public interface BrandFamilyRepository extends JpaRepository<Brandfamily, UUID> {

	@Query(value = " select * from brandfamily where brandfamily_deleted = 'false' ", nativeQuery = true)
	List<Brandfamily> getBrandFamily();

}
