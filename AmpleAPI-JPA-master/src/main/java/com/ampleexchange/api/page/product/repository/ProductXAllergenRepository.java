package com.ampleexchange.api.page.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.product.model.ProductXAllergen;

public interface ProductXAllergenRepository extends JpaRepository<ProductXAllergen,UUID> {

}


