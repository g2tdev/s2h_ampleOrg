package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.HealthLicenseTypeService;
import com.ampleexchange.api.page.guidedsetup.model.HealthLicenseType;
import com.ampleexchange.api.page.guidedsetup.repository.HealthLicenseTypeRepository;
 
@Service
public class HealthLicenseTypeServiceImpl implements HealthLicenseTypeService{
	
	@Autowired
	private HealthLicenseTypeRepository healthLicenseTypeRepository;
	
	@Override
	public HealthLicenseType getById(UUID healthLicenseTypeId){
		return healthLicenseTypeRepository.getOne(healthLicenseTypeId);
	} 
}
