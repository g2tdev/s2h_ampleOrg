package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.ApiUserDBService;
import com.ampleexchange.api.page.productcall.dbservice.ProductcallXCannabinoidDBService;
import com.ampleexchange.api.page.productcall.dbservice.ProductcallXTerpeneDBService;
import com.ampleexchange.api.page.productcall.model.ApiUser;
import com.ampleexchange.api.page.productcall.model.ProductCall;
import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;
import com.ampleexchange.api.page.productcall.repository.ApiUserRepository;
import com.ampleexchange.api.page.productcall.repository.ProductCallRepository;
import com.ampleexchange.api.page.productcall.repository.ProductcallXCannabinoidRepository;

@Service
public class ProductcallXCannabinoidDBServiceImpl implements ProductcallXCannabinoidDBService{
	
	@Autowired
	private ProductcallXCannabinoidRepository productcallXCannabinoidRepository;

	@Override
	public ProductcallXCannabinoid insertProductcallCannabinoid(ProductcallXCannabinoid pxc) {
		return productcallXCannabinoidRepository.save(pxc);
	}

	@Override
	public void deleteRows(UUID productCallId) {
		//productcallXCannabinoidRepository.deleteRows(productCallId);
		productcallXCannabinoidRepository.deleteById(productCallId);	
		//productcallXCannabinoidRepository.deleteInBatch(entities);
	}
	
	@Override
	public void deleteRowsBatch(List<ProductcallXCannabinoid> pxcList) {
		productcallXCannabinoidRepository.deleteInBatch(pxcList);
	}
	
	@Override
	public List<ProductcallXCannabinoid> selectRows(UUID productCallId){
		return productcallXCannabinoidRepository.selectRows(productCallId);
	}

	@Override
	public Integer setRowsDeleted(UUID productCallId) {
		return productcallXCannabinoidRepository.setDeleted(productCallId);
	}
	

}
