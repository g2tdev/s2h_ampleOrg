package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.FacilityxHealthlicenseService;
import com.ampleexchange.api.page.guidedsetup.model.FacilityxContact;
import com.ampleexchange.api.page.guidedsetup.model.FacilityxHealthlicense;
import com.ampleexchange.api.page.guidedsetup.repository.FacilityxHealthlicenseRepository;
 
@Service
public class FacilityxHealthlicenseServiceImpl implements FacilityxHealthlicenseService{
	
	@Autowired
	private FacilityxHealthlicenseRepository facilityxHealthlicenseRepository;
 
	@Override
	public FacilityxHealthlicense save(FacilityxHealthlicense facilityxHealthlicense){
		return facilityxHealthlicenseRepository.save(facilityxHealthlicense);
	}

	@Override
	public FacilityxHealthlicense getByFacilityType(UUID facilityId, UUID typeId) {
		return facilityxHealthlicenseRepository.findByFacilityType(facilityId, typeId);
	}
	
	@Override
	public Set<FacilityxHealthlicense> getByFacility(UUID facilityId) {
		return facilityxHealthlicenseRepository.findByFacility(facilityId);
	}
}
