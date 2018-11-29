package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.CategoryDBService;
import com.ampleexchange.api.page.productcall.model.Category;
import com.ampleexchange.api.page.productcall.repository.CategoryRepository;

@Service
public class CategoryDBServiceImpl implements CategoryDBService {
	@Autowired
	private CategoryRepository categoryRepository;	
	
	@Override
	public String getCategoryName(UUID categoryId) {
		return categoryRepository.findById(categoryId).get().getCategory_shortname();		
	}
}
