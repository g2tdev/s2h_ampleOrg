package com.ampleexchange.api.page.productcall.dbservice;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.productcall.model.BpcReturn;
import com.ampleexchange.api.page.productcall.model.ProductCall;
import com.ampleexchange.api.page.productcall.model.SpcReturn;

public interface ProductCallDBService {
	
	public ProductCall insertProductCall(ProductCall pc);		//Insert still needs to be implemented
	public ProductCall selectProductCallById(UUID productCallId);		//Currently implemented
	public List<ProductCall> getAllSpcProductCalls(String userId);
	public List<ProductCall> getAllBpcProductCalls(String orgID);
	public ProductCall updateProductCall(ProductCall pc);			//Currently implemented
	public ProductCall publishProductCall(ProductCall pc);
	public ProductCall deleteProductCall(ProductCall pc);
	public String getProductCallId(String userId);			//TODO: potentially change return type to ProductCall
	public String getOrgId(String userId);
	public String getCategoryId(String categoryName);
	public String getSubCategoryId(String subCategoryName);
	public List<ProductCall> getAllProductCalls();
	
}