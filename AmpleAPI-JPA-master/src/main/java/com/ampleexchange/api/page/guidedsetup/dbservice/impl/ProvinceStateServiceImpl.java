package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.ProvinceStateService;
import com.ampleexchange.api.page.guidedsetup.model.ProvinceState;
import com.ampleexchange.api.page.guidedsetup.repository.ProvinceStateRepository;
 
@Service
public class ProvinceStateServiceImpl implements ProvinceStateService{
	
	@Autowired
	private ProvinceStateRepository provinceStateRepository;
	
	@Override
	public ProvinceState getById(UUID provinceStateId){
		return provinceStateRepository.getOne(provinceStateId);
	} 
}
