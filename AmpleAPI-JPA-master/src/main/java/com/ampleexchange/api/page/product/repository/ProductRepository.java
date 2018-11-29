package com.ampleexchange.api.page.product.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " update product set product_deleted = true where product_id = :product_id ", nativeQuery = true)
	void deleteProduct(@Param("product_id") UUID product_id);

	@Query(value = " select * from product where product_deleted = false and product_availableforsale <= now() ", nativeQuery = true)
	List<Product> saleProducts();

	@Query(value = " select * from product where product_deleted = true ", nativeQuery = true)
	List<Product> removedProducts();

	@Query(value = " select * from product where product_deleted = false and product_availableforsale > now() ", nativeQuery = true)
	List<Product> futureProducts();

}
