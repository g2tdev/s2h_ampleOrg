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
import lombok.Data;
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
@Table(name = "productxallergen")
public class ProductXAllergen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID productallergen_id;

	private UUID product_id;
	private UUID allergen_id;
	private Integer productallergen_sort;
	private Timestamp productallergen_createdate;
	private Date productallergen_updatedate;
	private Boolean productallergen_deleted;
	private UUID lastupdatedby;

	@ManyToMany(mappedBy = "productxallergen")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private transient Set<Product> product;

	public UUID getProductallergen_id() {
		return productallergen_id;
	}

	public void setProductallergen_id(UUID productallergen_id) {
		this.productallergen_id = productallergen_id;
	}

	public UUID getProduct_id() {
		return product_id;
	}

	public void setProduct_id(UUID product_id) {
		this.product_id = product_id;
	}

	public UUID getAllergen_id() {
		return allergen_id;
	}

	public void setAllergen_id(UUID allergen_id) {
		this.allergen_id = allergen_id;
	}

	public Integer getProductallergen_sort() {
		return productallergen_sort;
	}

	public void setProductallergen_sort(Integer productallergen_sort) {
		this.productallergen_sort = productallergen_sort;
	}

	public Timestamp getProductallergen_createdate() {
		return productallergen_createdate;
	}

	public void setProductallergen_createdate(Timestamp productallergen_createdate) {
		this.productallergen_createdate = productallergen_createdate;
	}

	public Date getProductallergen_updatedate() {
		return productallergen_updatedate;
	}

	public void setProductallergen_updatedate(Date productallergen_updatedate) {
		this.productallergen_updatedate = productallergen_updatedate;
	}

	public Boolean getProductallergen_deleted() {
		return productallergen_deleted;
	}

	public void setProductallergen_deleted(Boolean productallergen_deleted) {
		this.productallergen_deleted = productallergen_deleted;
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
