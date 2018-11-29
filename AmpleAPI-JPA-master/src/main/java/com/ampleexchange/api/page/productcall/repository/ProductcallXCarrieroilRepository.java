package com.ampleexchange.api.page.productcall.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.productcall.model.ProductcallXAllergen;
import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;
import com.ampleexchange.api.page.productcall.model.ProductcallXCarrieroil;

public interface ProductcallXCarrieroilRepository extends JpaRepository<ProductcallXCarrieroil,UUID> {
	
	@Query(value = "SELECT * FROM productcallxcarrieroil WHERE productcall_id= ?1", nativeQuery = true)
	List<ProductcallXCarrieroil> selectRows(UUID productcallId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE productcallxcarrieroil SET productcarrieroil_deleted = TRUE WHERE productcall_id = ?1", nativeQuery = true)
	Integer setDeleted(UUID productCallId);
}
