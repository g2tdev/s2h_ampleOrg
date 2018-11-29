package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.HealthLicenseType;
 
public interface HealthLicenseTypeService {
	public HealthLicenseType getById(UUID healthLicenseTypeId);
}