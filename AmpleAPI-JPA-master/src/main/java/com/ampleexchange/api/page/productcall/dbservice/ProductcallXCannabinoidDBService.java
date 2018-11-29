package com.ampleexchange.api.page.productcall.dbservice;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;

public interface ProductcallXCannabinoidDBService {
	
	public ProductcallXCannabinoid insertProductcallCannabinoid(ProductcallXCannabinoid pxc);	
	
	public void deleteRows(UUID productCallId);
	
	public List<ProductcallXCannabinoid> selectRows(UUID productCallId);
	
	public void deleteRowsBatch(List<ProductcallXCannabinoid> pxcList);
	
	public Integer setRowsDeleted(UUID productCallId);
}