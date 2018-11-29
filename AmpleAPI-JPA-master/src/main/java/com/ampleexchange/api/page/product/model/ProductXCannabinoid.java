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
@Table(name = "productxcannabinoid")
public class ProductXCannabinoid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID productcannabinoid_id;

	private UUID product_id;
	private UUID cannabinoid_id;
	private Double productcannabinoid_minperc;
	private Double productcannabinoid_maxperc;
	private Double productcannabinoid_actualperc;
	private Boolean productcannabinoid_deleted;
	private Timestamp productcannabinoid_createdate;
	private Date productcannabinoid_updatedate;
	private UUID lastupdatedby;

	@ManyToMany(mappedBy = "productxcannabinoid")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private transient Set<Product> product;

	public UUID getProductcannabinoid_id() {
		return productcannabinoid_id;
	}

	public void setProductcannabinoid_id(UUID productcannabinoid_id) {
		this.productcannabinoid_id = productcannabinoid_id;
	}

	public UUID getProduct_id() {
		return product_id;
	}

	public void setProduct_id(UUID product_id) {
		this.product_id = product_id;
	}

	public UUID getCannabinoid_id() {
		return cannabinoid_id;
	}

	public void setCannabinoid_id(UUID cannabinoid_id) {
		this.cannabinoid_id = cannabinoid_id;
	}

	public Double getProductcannabinoid_minperc() {
		return productcannabinoid_minperc;
	}

	public void setProductcannabinoid_minperc(Double productcannabinoid_minperc) {
		this.productcannabinoid_minperc = productcannabinoid_minperc;
	}

	public Double getProductcannabinoid_maxperc() {
		return productcannabinoid_maxperc;
	}

	public void setProductcannabinoid_maxperc(Double productcannabinoid_maxperc) {
		this.productcannabinoid_maxperc = productcannabinoid_maxperc;
	}

	public Double getProductcannabinoid_actualperc() {
		return productcannabinoid_actualperc;
	}

	public void setProductcannabinoid_actualperc(Double productcannabinoid_actualperc) {
		this.productcannabinoid_actualperc = productcannabinoid_actualperc;
	}

	public Boolean getProductcannabinoid_deleted() {
		return productcannabinoid_deleted;
	}

	public void setProductcannabinoid_deleted(Boolean productcannabinoid_deleted) {
		this.productcannabinoid_deleted = productcannabinoid_deleted;
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

	public Timestamp getProductcannabinoid_createdate() {
		return productcannabinoid_createdate;
	}

	public void setProductcannabinoid_createdate(Timestamp productcannabinoid_createdate) {
		this.productcannabinoid_createdate = productcannabinoid_createdate;
	}

	public Date getProductcannabinoid_updatedate() {
		return productcannabinoid_updatedate;
	}

	public void setProductcannabinoid_updatedate(Date productcannabinoid_updatedate) {
		this.productcannabinoid_updatedate = productcannabinoid_updatedate;
	}

}
