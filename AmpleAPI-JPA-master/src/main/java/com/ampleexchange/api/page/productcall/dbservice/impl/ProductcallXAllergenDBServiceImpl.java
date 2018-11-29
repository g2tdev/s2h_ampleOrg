package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.ApiUserDBService;
import com.ampleexchange.api.page.productcall.dbservice.ProductcallXAllergenDBService;
import com.ampleexchange.api.page.productcall.dbservice.ProductcallXTerpeneDBService;
import com.ampleexchange.api.page.productcall.model.ApiUser;
import com.ampleexchange.api.page.productcall.model.ProductcallXAllergen;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;
import com.ampleexchange.api.page.productcall.repository.ApiUserRepository;
import com.ampleexchange.api.page.productcall.repository.ProductCallRepository;
import com.ampleexchange.api.page.productcall.repository.ProductcallXAllergenRepository;
import com.ampleexchange.api.page.productcall.repository.ProductcallXTerpeneRepository;

@Service
public class ProductcallXAllergenDBServiceImpl implements ProductcallXAllergenDBService{
	
	@Autowired
	private ProductcallXAllergenRepository productcallXAllergenRepository;

	@Override
	public ProductcallXAllergen insertProductcallAllergen(ProductcallXAllergen pxa) {
		return productcallXAllergenRepository.save(pxa);
	}

	@Override
	public List<ProductcallXAllergen> selectRows(UUID productCallId) {
		return productcallXAllergenRepository.selectRows(productCallId);
	}

	@Override
	public void deleteRowsBatch(List<ProductcallXAllergen> pxcList) {
		productcallXAllergenRepository.deleteInBatch(pxcList);		
	}

	@Override
	public Integer setRowsDeleted(UUID productCallId) {
		return productcallXAllergenRepository.setDeleted(productCallId);
	}
	

}
