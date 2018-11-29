package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.Set;
import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.ContactComm;
 
public interface ContactCommService {
	public ContactComm save(ContactComm contactComm);	
	
	public Set<ContactComm> getByContactId(UUID contactId);
	
	public ContactComm getByContactIdType(UUID contactId, String conType);
}
