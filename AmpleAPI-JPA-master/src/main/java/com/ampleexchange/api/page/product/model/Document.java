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
@Table(name = "document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID document_id;

	private String document_type;
	private UUID category_id;
	private UUID subcategory_id;
	private UUID product_id;
	private UUID uom_id;
	private Integer document_priceperbaseuom;
	private String document_deliveryterms;
	private String document_shippingterms;
	private Date document_expirydate;
	private Timestamp organizationxdocument_createdate;
	private Date organizationxdocument_updatedate;
	private Boolean organizationxdocument_deleted;
	private UUID lastupdatedby;
	public UUID getDocument_id() {
		return document_id;
	}
	public void setDocument_id(UUID document_id) {
		this.document_id = document_id;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public UUID getCategory_id() {
		return category_id;
	}
	public void setCategory_id(UUID category_id) {
		this.category_id = category_id;
	}
	public UUID getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(UUID subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public UUID getProduct_id() {
		return product_id;
	}
	public void setProduct_id(UUID product_id) {
		this.product_id = product_id;
	}
	public UUID getUom_id() {
		return uom_id;
	}
	public void setUom_id(UUID uom_id) {
		this.uom_id = uom_id;
	}
	public Integer getDocument_priceperbaseuom() {
		return document_priceperbaseuom;
	}
	public void setDocument_priceperbaseuom(Integer document_priceperbaseuom) {
		this.document_priceperbaseuom = document_priceperbaseuom;
	}
	public String getDocument_deliveryterms() {
		return document_deliveryterms;
	}
	public void setDocument_deliveryterms(String document_deliveryterms) {
		this.document_deliveryterms = document_deliveryterms;
	}
	public String getDocument_shippingterms() {
		return document_shippingterms;
	}
	public void setDocument_shippingterms(String document_shippingterms) {
		this.document_shippingterms = document_shippingterms;
	}
	public Date getDocument_expirydate() {
		return document_expirydate;
	}
	public void setDocument_expirydate(Date document_expirydate) {
		this.document_expirydate = document_expirydate;
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
