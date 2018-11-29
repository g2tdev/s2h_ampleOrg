package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.CategoryService;
import com.ampleexchange.api.page.productcall.model.Category;
 
@Service
public class CategoryServiceImpl implements CategoryService{
	
//	@Autowired
//	private CategoryDao categoryDao;
 
	@Override
	public Integer insertCategory(Category newCategory){
//		return categoryDao.insertCategory(newCategory);
		return null;
	}	
 
}
