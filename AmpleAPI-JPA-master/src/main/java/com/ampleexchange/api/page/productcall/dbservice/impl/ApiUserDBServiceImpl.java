package com.ampleexchange.api.page.productcall.dbservice.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.productcall.dbservice.ApiUserDBService;
import com.ampleexchange.api.page.productcall.model.ApiUser;
import com.ampleexchange.api.page.productcall.repository.ApiUserRepository;
import com.ampleexchange.api.page.productcall.repository.ProductCallRepository;

@Service
public class ApiUserDBServiceImpl implements ApiUserDBService{
	
	@Autowired
	private ApiUserRepository apiUserRepository;	
	
	@Override
	public UUID getOrgId(String userId) {
		// TODO Auto-generated method stub
		return apiUserRepository.findById(UUID.fromString(userId)).get().getOrg_id();
	}

	@Override
	public String getApiUserName(UUID userId) {
		// TODO Auto-generated method stub
		return apiUserRepository.findById(userId).get().getUser_name();
	}

}
