package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.Set;
import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.FacilityxContact;
 
public interface FacilityxContactService {
	public FacilityxContact save(FacilityxContact facilityxContact);
	
	public FacilityxContact getByFacilityContact(UUID facilityId, UUID contactId);
	
	public Set<FacilityxContact> getByFacility(UUID facilityId);
}
