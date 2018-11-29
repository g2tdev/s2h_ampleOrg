package com.ampleexchange.api.page.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "documentxattachments")
public class DocumentXAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID document_idxattachment;
	
	private UUID document_id;
	private UUID attachment_id;
	private Timestamp organizationxdocument_createdate;
	private Date organizationxdocument_updatedate;
	private Boolean organizationxdocument_deleted;
	private UUID lastupdatedby;
	public UUID getDocument_idxattachment() {
		return document_idxattachment;
	}
	public void setDocument_idxattachment(UUID document_idxattachment) {
		this.document_idxattachment = document_idxattachment;
	}
	public UUID getDocument_id() {
		return document_id;
	}
	public void setDocument_id(UUID document_id) {
		this.document_id = document_id;
	}
	public UUID getAttachment_id() {
		return attachment_id;
	}
	public void setAttachment_id(UUID attachment_id) {
		this.attachment_id = attachment_id;
	}
	public Timestamp getOrganizationxdocument_createdate() {
		return organizationxdocument_createdate;
	}
	public void setOrganizationxdocument_createdate(Timestamp organizationxdocument_createdate) {
		this.organizationxdocument_createdate = organizationxdocument_createdate;
	}
	public Date getOrganizationxdocument_updatedate() {
		return organizationxdocument_updatedate;
	}
	public void setOrganizationxdocument_updatedate(Date organizationxdocument_updatedate) {
		this.organizationxdocument_updatedate = organizationxdocument_updatedate;
	}
	public Boolean getOrganizationxdocument_deleted() {
		return organizationxdocument_deleted;
	}
	public void setOrganizationxdocument_deleted(Boolean organizationxdocument_deleted) {
		this.organizationxdocument_deleted = organizationxdocument_deleted;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
}
