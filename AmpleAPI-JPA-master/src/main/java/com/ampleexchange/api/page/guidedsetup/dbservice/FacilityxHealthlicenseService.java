package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.Set;
import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.FacilityxHealthlicense;
 
public interface FacilityxHealthlicenseService {
	public FacilityxHealthlicense save(FacilityxHealthlicense facilityxHealthlicense);
	
	public FacilityxHealthlicense getByFacilityType(UUID facilityId, UUID typeId);
	
	public Set<FacilityxHealthlicense> getByFacility(UUID facilityId);
}
