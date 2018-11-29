package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.ContactCommService;
import com.ampleexchange.api.page.guidedsetup.model.ContactComm;
import com.ampleexchange.api.page.guidedsetup.repository.ContactCommRepository;
 
@Service
public class ContactCommServiceImpl implements ContactCommService{
	
	@Autowired
	private ContactCommRepository contactCommRepository;
 
	@Override
	public ContactComm save(ContactComm contactComm){
		return contactCommRepository.save(contactComm);
	}

	@Override
	public Set<ContactComm> getByContactId(UUID contactId) {
		return contactCommRepository.findByContactId(contactId);
	}
	
	@Override
	public ContactComm getByContactIdType(UUID contactId, String contactType){
		return contactCommRepository.findByContactIdType(contactId, contactType);
	}
}
