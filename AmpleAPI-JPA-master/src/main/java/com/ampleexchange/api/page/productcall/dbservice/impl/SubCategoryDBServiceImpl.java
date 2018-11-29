package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.SubCategoryDBService;
import com.ampleexchange.api.page.productcall.repository.CategoryRepository;
import com.ampleexchange.api.page.productcall.repository.SubCategoryRepository;

@Service
public class SubCategoryDBServiceImpl implements SubCategoryDBService {
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;	

	@Override
	public String getSubCategoryName(UUID subCategoryId) {
		// TODO Auto-generated method stub
		return subCategoryRepository.findById(subCategoryId).get().getSubcategory_shortname();
	}

}
