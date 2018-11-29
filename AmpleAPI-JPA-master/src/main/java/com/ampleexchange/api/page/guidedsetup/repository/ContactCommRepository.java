package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ampleexchange.api.page.guidedsetup.model.ContactComm;

/**
 * @author JG
 * @date 2018/11/23
 */
public interface ContactCommRepository extends JpaRepository<ContactComm,UUID> {
	@Query("FROM ContactComm cc WHERE cc.contact_id.contact_id = :contactId")
    public Set<ContactComm> findByContactId(@Param("contactId") UUID contactId);
	
	@Query("FROM ContactComm cc WHERE cc.contact_id.contact_id = :contactId and cc.contactcomm_type = :contactType")
    public ContactComm findByContactIdType(@Param("contactId") UUID contactId, @Param("contactType") String contactType);
}
