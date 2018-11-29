package com.ampleexchange.api.page.productcall.dbservice;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.productcall.model.ProductcallXAllergen;
import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;

public interface ProductcallXAllergenDBService {
	
	public ProductcallXAllergen insertProductcallAllergen(ProductcallXAllergen pxa);	
	
	public List<ProductcallXAllergen> selectRows(UUID productCallId);
	
	public void deleteRowsBatch(List<ProductcallXAllergen> pxcList);
	
	public Integer setRowsDeleted(UUID productCallId);
}