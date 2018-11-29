package com.ampleexchange.api.page.productcall.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.productcall.model.ProductcallXAllergen;
import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;

public interface ProductcallXAllergenRepository extends JpaRepository<ProductcallXAllergen,UUID> {
	
	@Query(value = "SELECT * FROM productcallxallergen WHERE productcall_id= ?1", nativeQuery = true)
	List<ProductcallXAllergen> selectRows(UUID productcallId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE productcallxallergen SET productcallallergen_deleted = TRUE WHERE productcall_id = ?1", nativeQuery = true)
	Integer setDeleted(UUID productCallId);
	
}
