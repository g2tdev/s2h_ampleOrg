package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.OrganizationStatusService;
import com.ampleexchange.api.page.guidedsetup.model.OrganizationStatus;
import com.ampleexchange.api.page.guidedsetup.repository.OrganizationStatusRepository;
 
@Service
public class OrganizationStatusServiceImpl implements OrganizationStatusService{
	
	@Autowired
	private OrganizationStatusRepository organizationStatusRepository;
	
	@Override
	public OrganizationStatus getById(UUID organizationStatusId){
		return organizationStatusRepository.getOne(organizationStatusId);
	} 
}
