package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.Organization;
 
public interface OrganizationService {
	public Organization save(Organization organization);
	 
	public List<Organization> getAll();
	
	public Organization getById(UUID orgId);
}