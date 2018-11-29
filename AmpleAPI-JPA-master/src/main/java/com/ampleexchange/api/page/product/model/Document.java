package com.ampleexchange.api.page.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	/*
	 * private UUID category_id; private UUID subcategory_id; private UUID
	 * product_id; private UUID uom_id; private Integer document_priceperbaseuom;
	 * private String document_deliveryterms; private String document_shippingterms;
	 */

	private Date document_expirydate;

	/*
	 * private Timestamp organizationxdocument_createdate; private Date
	 * organizationxdocument_updatedate; private Boolean
	 * organizationxdocument_deleted;
	 */

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

	public Date getDocument_expirydate() {
		return document_expirydate;
	}

	public void setDocument_expirydate(Date document_expirydate) {
		this.document_expirydate = document_expirydate;
	}

	public UUID getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

}
