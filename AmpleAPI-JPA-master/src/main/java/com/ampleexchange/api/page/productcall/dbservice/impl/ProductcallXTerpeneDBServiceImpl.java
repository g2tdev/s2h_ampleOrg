package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.ApiUserDBService;
import com.ampleexchange.api.page.productcall.dbservice.ProductcallXTerpeneDBService;
import com.ampleexchange.api.page.productcall.model.ApiUser;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;
import com.ampleexchange.api.page.productcall.repository.ApiUserRepository;
import com.ampleexchange.api.page.productcall.repository.ProductCallRepository;
import com.ampleexchange.api.page.productcall.repository.ProductcallXTerpeneRepository;

@Service
public class ProductcallXTerpeneDBServiceImpl implements ProductcallXTerpeneDBService{
	
	@Autowired
	private ProductcallXTerpeneRepository productcallXTerpeneRepository;

	@Override
	public ProductcallXTerpene insertProductcallTerpene(ProductcallXTerpene pxt) {
		return productcallXTerpeneRepository.save(pxt);
	}

	@Override
	public List<ProductcallXTerpene> selectRows(UUID productCallId) {
		return productcallXTerpeneRepository.selectRows(productCallId);
	}

	@Override
	public void deleteRowsBatch(List<ProductcallXTerpene> pxcList) {
		productcallXTerpeneRepository.deleteInBatch(pxcList);		
	}

	@Override
	public Integer setRowsDeleted(UUID productCallId) {
		return productcallXTerpeneRepository.setDeleted(productCallId);
	}
	

}
