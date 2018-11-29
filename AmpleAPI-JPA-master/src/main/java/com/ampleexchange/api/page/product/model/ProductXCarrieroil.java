package com.ampleexchange.api.page.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "productxcarrieroil")
public class ProductXCarrieroil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID productcarrieroil_id;
	
	private UUID product_id;
	private UUID carrieroil_id;
	private Timestamp productcarrieroil_createdate;
	private Date productcarrieroil_updatedate;
	private Boolean productcarrieroil_deleted;
	private UUID lastupdatedby;

	@ManyToMany(mappedBy = "productxcarrieroil")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private transient Set<Product> product;

	public UUID getProductcarrieroil_id() {
		return productcarrieroil_id;
	}

	public void setProductcarrieroil_id(UUID productcarrieroil_id) {
		this.productcarrieroil_id = productcarrieroil_id;
	}

	public UUID getProduct_id() {
		return product_id;
	}

	public void setProduct_id(UUID product_id) {
		this.product_id = product_id;
	}

	public UUID getCarrieroil_id() {
		return carrieroil_id;
	}

	public void setCarrieroil_id(UUID carrieroil_id) {
		this.carrieroil_id = carrieroil_id;
	}

	public Timestamp getProductcarrieroil_createdate() {
		return productcarrieroil_createdate;
	}

	public void setProductcarrieroil_createdate(Timestamp productcarrieroil_createdate) {
		this.productcarrieroil_createdate = productcarrieroil_createdate;
	}

	public Date getProductcarrieroil_updatedate() {
		return productcarrieroil_updatedate;
	}

	public void setProductcarrieroil_updatedate(Date productcarrieroil_updatedate) {
		this.productcarrieroil_updatedate = productcarrieroil_updatedate;
	}

	public Boolean getProductcarrieroil_deleted() {
		return productcarrieroil_deleted;
	}

	public void setProductcarrieroil_deleted(Boolean productcarrieroil_deleted) {
		this.productcarrieroil_deleted = productcarrieroil_deleted;
	}

	public UUID getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

}
