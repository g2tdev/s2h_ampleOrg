package com.ampleexchange.api.page.product.model;

import java.sql.Date;
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
@Table(name = "attachement")
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID attachment_id;

	private UUID document_id;
	private String attachment_link;
	private Date attachment_created;
	private Date attachment_updated;
	private Boolean attachment_deleted;
	private UUID lastupdatedby;
	public UUID getAttachment_id() {
		return attachment_id;
	}
	public void setAttachment_id(UUID attachment_id) {
		this.attachment_id = attachment_id;
	}
	public UUID getDocument_id() {
		return document_id;
	}
	public void setDocument_id(UUID document_id) {
		this.document_id = document_id;
	}
	public String getAttachment_link() {
		return attachment_link;
	}
	public void setAttachment_link(String attachment_link) {
		this.attachment_link = attachment_link;
	}
	public Date getAttachment_created() {
		return attachment_created;
	}
	public void setAttachment_created(Date attachment_created) {
		this.attachment_created = attachment_created;
	}
	public Date getAttachment_updated() {
		return attachment_updated;
	}
	public void setAttachment_updated(Date attachment_updated) {
		this.attachment_updated = attachment_updated;
	}
	public Boolean getAttachment_deleted() {
		return attachment_deleted;
	}
	public void setAttachment_deleted(Boolean attachment_deleted) {
		this.attachment_deleted = attachment_deleted;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	
}
