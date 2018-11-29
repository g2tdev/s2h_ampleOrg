package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.Set;
import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.Facility;
 
public interface FacilityService {
	public Facility save(Facility facility);
	
	public Set<Facility> getAll();
	
	public Facility getById(UUID facilityId);
	
	public Set<Facility> getByOrgId(UUID orgId);
}
