package com.ampleexchange.api.page.productcall.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;

public interface ProductcallXTerpeneRepository extends JpaRepository<ProductcallXTerpene,UUID> {
	
	@Query(value = "SELECT * FROM productcallxterpene WHERE productcall_id= ?1", nativeQuery = true)
	List<ProductcallXTerpene> selectRows(UUID productcallId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE productcallxterpene SET productcallterpene_deleted = TRUE WHERE productcall_id = ?1", nativeQuery = true)
	Integer setDeleted(UUID productCallId);
}
