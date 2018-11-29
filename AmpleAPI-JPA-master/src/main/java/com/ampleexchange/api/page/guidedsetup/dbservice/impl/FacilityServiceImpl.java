package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.FacilityService;
import com.ampleexchange.api.page.guidedsetup.model.Facility;
import com.ampleexchange.api.page.guidedsetup.repository.FacilityRepository;
 
@Service
public class FacilityServiceImpl implements FacilityService{
	
	@Autowired
	private FacilityRepository facilityRepository;
 
	@Override
	public Facility save(Facility facility){
		return facilityRepository.save(facility);
	}
	
	@Override
	public Set<Facility> getAll() {
		Set<Facility> setFacilities = new HashSet<Facility>();
		setFacilities.addAll(facilityRepository.findAll());
		return setFacilities;
	}

	@Override
	public Facility getById(UUID facilityId) {
		return facilityRepository.getOne(facilityId);
	} 
	
	@Override
	public Set<Facility> getByOrgId(UUID orgId) {
		return facilityRepository.findByOrgId(orgId);
	} 
}
