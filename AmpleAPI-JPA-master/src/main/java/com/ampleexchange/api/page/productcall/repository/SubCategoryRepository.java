package com.ampleexchange.api.page.productcall.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ampleexchange.api.page.productcall.model.Category;
import com.ampleexchange.api.page.productcall.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory,UUID> {

}
