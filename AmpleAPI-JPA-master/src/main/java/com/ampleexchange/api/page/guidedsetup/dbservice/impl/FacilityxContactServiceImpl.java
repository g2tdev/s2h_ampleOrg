package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.FacilityxContactService;
import com.ampleexchange.api.page.guidedsetup.model.FacilityxContact;
import com.ampleexchange.api.page.guidedsetup.repository.FacilityxContactRepository;
 
@Service
public class FacilityxContactServiceImpl implements FacilityxContactService{
	
	@Autowired
	private FacilityxContactRepository facilityxContactRepository;
 
	@Override
	public FacilityxContact save(FacilityxContact facilityxContact){
		return facilityxContactRepository.save(facilityxContact);
	}

	@Override
	public FacilityxContact getByFacilityContact(UUID facilityId, UUID contactId) {
		return facilityxContactRepository.findByFacilityContact(facilityId, contactId);
	}
	
	@Override
	public Set<FacilityxContact> getByFacility(UUID facilityId) {
		return facilityxContactRepository.findByFacility(facilityId);
	}
}
