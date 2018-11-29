package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.OrganizationStatus;
 
public interface OrganizationStatusService {
	public OrganizationStatus getById(UUID organizationStatusId);
}