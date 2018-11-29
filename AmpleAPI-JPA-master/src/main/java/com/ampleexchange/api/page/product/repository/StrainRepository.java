package com.ampleexchange.api.page.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ampleexchange.api.page.product.model.Strain;
import com.ampleexchange.api.page.productcall.model.ProductCall;

public interface StrainRepository extends JpaRepository<Strain, UUID> {

	@Query(value = "select * from strain where strain_deleted = 'false' ", nativeQuery = true)
	List<Strain> retrieveStrains();
}
