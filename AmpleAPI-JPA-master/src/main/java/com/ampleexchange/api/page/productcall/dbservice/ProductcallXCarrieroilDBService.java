package com.ampleexchange.api.page.productcall.dbservice;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;
import com.ampleexchange.api.page.productcall.model.ProductcallXCarrieroil;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;

public interface ProductcallXCarrieroilDBService {
	
	public ProductcallXCarrieroil insertProductcallCarrieroil(ProductcallXCarrieroil pxo);	
	
	public void deleteRows(UUID productCallId);
	
	public List<ProductcallXCarrieroil> selectRows(UUID productCallId);
	
	public void deleteRowsBatch(List<ProductcallXCarrieroil> pxoList);
	
	public Integer setRowsDeleted(UUID productCallId);
}