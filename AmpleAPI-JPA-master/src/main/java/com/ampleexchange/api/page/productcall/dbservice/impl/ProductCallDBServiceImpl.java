package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.ProductCallDBService;
import com.ampleexchange.api.page.productcall.model.BpcReturn;
import com.ampleexchange.api.page.productcall.model.HibernateUtil;
import com.ampleexchange.api.page.productcall.model.ProductCall;
import com.ampleexchange.api.page.productcall.model.SpcReturn;
import com.ampleexchange.api.page.productcall.repository.ApiUserRepository;
import com.ampleexchange.api.page.productcall.repository.ProductCallRepository;

import lombok.extern.java.Log;

@Log
@Service
public class ProductCallDBServiceImpl implements ProductCallDBService {

	@Autowired
	private ProductCallRepository productCallRepository;	
	
	@Autowired
	private ApiUserDBServiceImpl apiUserService;

	/*@Override
	public List<ProductCall> getProductCall() {
		// TODO Auto-generated method stub
		return pcDoa.getProductCall();
	}*/

	@Override
	public ProductCall selectProductCallById(UUID productCallId) {
		// TODO Auto-generated method stub
//		return productCallRepository.selectProductCallById(uId);
//		return productCallRepository.selectPC(productCallId);
		return productCallRepository.findById(productCallId).get();
	}

	@Override
	public ProductCall updateProductCall(ProductCall pc) {
		// TODO Auto-generated method stub
		return productCallRepository.save(pc);			
	}

	@Override
	public ProductCall deleteProductCall(ProductCall pc) {
		// TODO Auto-generated method stub		
		return productCallRepository.save(pc);
	}

	@Override
	public ProductCall insertProductCall(ProductCall pc) {
		// TODO Auto-generated method stub
		return productCallRepository.save(pc);		
	}

	@Override
	public String getProductCallId(String userId) {
		// TODO Auto-generated method stub
//		return productCallRepository.getProductCallId(userId);
		return null;
	}

	@Override
	public String getOrgId(String userId) {
		// TODO Auto-generated method stub
//		return productCallRepository.getOrgId(userId);
		return null;
	}

	@Override
	public String getCategoryId(String categoryName) {
		// TODO Auto-generated method stub
//		return productCallRepository.getCategoryId(categoryName);
		return null;
	}

	@Override
	public String getSubCategoryId(String subCategoryName) {
		// TODO Auto-generated method stub
//		return productCallRepository.getSubCategoryId(subCategoryName);
		return null;
	}

	@Override
	public ProductCall publishProductCall(ProductCall pc) {		
		return productCallRepository.save(pc);
	}
	

	@Override
	public List<ProductCall> getAllBpcProductCalls(String orgId) {
		// TODO Auto-generated method stub
	/*	List<ProductCall> pcList = productCallRepository.findAll();
		List<ProductCall> buyerProductCallList = new ArrayList<ProductCall>();
		
		for(int i=0; i< pcList.size(); i++) {
			if(pcList.get(i).getOrg_id().equals(UUID.fromString(orgId)) && !pcList.get(i).getProductcall_status().equals("Deleted")) {
				buyerProductCallList.add(pcList.get(i));
			}
		}
		return buyerProductCallList;*/
		
		List<ProductCall> buyerProductCallList = productCallRepository.getAllBpc(UUID.fromString(orgId));
		return buyerProductCallList;
	}

	@Override
	public List<ProductCall> getAllProductCalls() {
		// TODO Auto-generated method stub
		List<ProductCall> allProductCalls = productCallRepository.selectPCAll();
		return allProductCalls;
	}

	@Override
	public List<ProductCall> getAllSpcProductCalls(String userId) {
					
			List<ProductCall> sellerProductCallList = productCallRepository.getAllSellerPC(UUID.fromString(userId));
			return sellerProductCallList;		
		
	}

}