package com.ampleexchange.api.page.productcall.dbservice;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;

public interface ProductcallXTerpeneDBService {
	
	public ProductcallXTerpene insertProductcallTerpene(ProductcallXTerpene pxt);	
	
	public List<ProductcallXTerpene> selectRows(UUID productCallId);
	
	public void deleteRowsBatch(List<ProductcallXTerpene> pxcList);
	
	public Integer setRowsDeleted(UUID productCallId);
}