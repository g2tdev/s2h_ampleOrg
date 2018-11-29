package com.ampleexchange.api.page.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ampleexchange.api.page.product.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, UUID> {

	@Query(value = " select * from brand where brand_deleted = 'false' ", nativeQuery = true)
	List<Brand> getBrands();

}
