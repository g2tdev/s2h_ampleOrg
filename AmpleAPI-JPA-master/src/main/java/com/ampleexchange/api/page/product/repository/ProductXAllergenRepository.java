package com.ampleexchange.api.page.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.product.model.ProductXAllergen;

public interface ProductXAllergenRepository extends JpaRepository<ProductXAllergen,UUID> {
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " delete from productxallergen where product_id = :product_id ", nativeQuery = true)
	void deleteProductxallergen(@Param("product_id") UUID product_id);

}


