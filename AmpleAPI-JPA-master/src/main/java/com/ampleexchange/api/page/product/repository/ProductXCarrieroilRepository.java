package com.ampleexchange.api.page.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.product.model.ProductXCarrieroil;

public interface ProductXCarrieroilRepository extends JpaRepository<ProductXCarrieroil, UUID> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " delete from productxcarrieroil where product_id = :product_id ", nativeQuery = true)
	void deleteProductxcarrieroil(@Param("product_id") UUID product_id);

}
