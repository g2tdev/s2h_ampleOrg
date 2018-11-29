package com.ampleexchange.api.page.productcall.dbservice;

import java.util.UUID;

import com.ampleexchange.api.page.productcall.model.Category;

public interface CategoryDBService {
	public String getCategoryName(UUID categoryId);
}
