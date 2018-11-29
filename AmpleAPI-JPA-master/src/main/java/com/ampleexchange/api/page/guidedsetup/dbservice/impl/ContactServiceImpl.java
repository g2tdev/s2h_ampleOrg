package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.guidedsetup.dbservice.ContactService;
import com.ampleexchange.api.page.guidedsetup.model.Contact;
import com.ampleexchange.api.page.guidedsetup.repository.ContactRepository;
 
@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactRepository contactRepository;
 
	@Override
	public Contact save(Contact contact){
		return contactRepository.save(contact);
	}

	@Override
	public Contact getById(UUID contactId) {
		return contactRepository.getOne(contactId);
	} 
	
	@Transactional
	@Override
	public Set<Contact> getByOrgId(UUID orgId) {
		return contactRepository.findByOrgId(orgId);
	}
	
	@Transactional
	@Override
	public Set<Contact> getAll() {
		Set<Contact> set = new HashSet<Contact>();
		set.addAll(contactRepository.findAll());
		return set;
	}
}
