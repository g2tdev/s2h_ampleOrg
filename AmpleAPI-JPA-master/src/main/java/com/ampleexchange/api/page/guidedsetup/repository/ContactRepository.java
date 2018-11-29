package com.ampleexchange.api.page.guidedsetup.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ampleexchange.api.page.guidedsetup.model.Contact;

/**
 * @author JG
 * @date 2018/11/23
 */
public interface ContactRepository extends JpaRepository<Contact,UUID> {
	@Query("FROM Contact c WHERE c.org_id.org_id = :orgId")
    public Set<Contact> findByOrgId(@Param("orgId") UUID orgId);
}
