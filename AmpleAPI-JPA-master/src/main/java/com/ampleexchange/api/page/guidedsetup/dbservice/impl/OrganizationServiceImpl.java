package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.OrganizationService;
import com.ampleexchange.api.page.guidedsetup.model.Organization;
import com.ampleexchange.api.page.guidedsetup.repository.OrganizationRepository;
 
@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	public Organization save(Organization organization){
		return organizationRepository.save(organization);
	}

	@Override
	public List<Organization> getAll() {
		return organizationRepository.findAll();
	}

	@Override
	public Organization getById(UUID orgId) {
		return organizationRepository.getOne(orgId);
	} 
}
