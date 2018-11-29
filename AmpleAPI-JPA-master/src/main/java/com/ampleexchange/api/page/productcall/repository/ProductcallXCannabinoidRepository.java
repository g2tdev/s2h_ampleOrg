package com.ampleexchange.api.page.productcall.repository;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ampleexchange.api.page.productcall.model.ProductCall;
import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;

public interface ProductcallXCannabinoidRepository extends JpaRepository<ProductcallXCannabinoid,UUID> {
	/*@Transactional
	@Query(value = "DELETE FROM productcallxcannabinoid where productcall_id = ?1 ", nativeQuery = true)
	void deleteRows(UUID productCallId);*/	
	
	@Query(value = "SELECT * FROM productcallxcannabinoid WHERE productcall_id= ?1", nativeQuery = true)
	List<ProductcallXCannabinoid> selectRows(UUID productcallId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE productcallxcannabinoid SET productcallcannabinoid_deleted = TRUE WHERE productcall_id = ?1", nativeQuery = true)
	Integer setDeleted(UUID productCallId);
	
}
