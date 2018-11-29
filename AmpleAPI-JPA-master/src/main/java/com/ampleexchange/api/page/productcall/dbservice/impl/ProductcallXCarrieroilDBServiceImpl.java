package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.ProductcallXCarrieroilDBService;
import com.ampleexchange.api.page.productcall.model.ProductcallXCarrieroil;
import com.ampleexchange.api.page.productcall.repository.ProductcallXCarrieroilRepository;
import com.ampleexchange.api.page.productcall.repository.ProductcallXTerpeneRepository;


@Service
public class ProductcallXCarrieroilDBServiceImpl implements ProductcallXCarrieroilDBService{

	@Autowired
	private ProductcallXCarrieroilRepository productcallXCarrieroilRepository;
	
	@Override
	public ProductcallXCarrieroil insertProductcallCarrieroil(ProductcallXCarrieroil pxo) {
		return productcallXCarrieroilRepository.save(pxo);
	}

	@Override
	public void deleteRows(UUID productCallId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductcallXCarrieroil> selectRows(UUID productCallId) {
		return productcallXCarrieroilRepository.selectRows(productCallId);
	}

	@Override
	public void deleteRowsBatch(List<ProductcallXCarrieroil> pxoList) {
		productcallXCarrieroilRepository.deleteInBatch(pxoList);		
	}

	@Override
	public Integer setRowsDeleted(UUID productCallId) {
		return productcallXCarrieroilRepository.setDeleted(productCallId);
	}	
	
	

}
