package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.Set;
import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.Contact;
 
public interface ContactService {
	public Contact save(Contact contact);		
	
	public Contact getById(UUID contactId);
	
	public Set<Contact> getByOrgId(UUID orgId);
	
	public Set<Contact> getAll();
}
