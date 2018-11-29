package com.ampleexchange.api.page.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ampleexchange.api.page.product.model.Lot;
import com.ampleexchange.api.page.productcall.model.ProductCall;

public interface LotRepository extends JpaRepository<Lot, UUID> {

	@Query(value = " select * from lot where lot_deleted = false and lot_number = ?1 ", nativeQuery = true)
	Lot getLotid(UUID lotnumber);

}
