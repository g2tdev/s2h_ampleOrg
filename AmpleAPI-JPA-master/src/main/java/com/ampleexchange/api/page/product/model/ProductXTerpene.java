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
@Table(name = "productxterpene")
public class ProductXTerpene {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID productterpene_id;

	private UUID product_id;
	private UUID terpene_id;
	private Double productterpene_minperc;
	private Double productterpene_maxperc;
	private Double productterpene_actualperc;
	private Boolean productterpene_deleted;
	private Timestamp productterpene_createdate;
	private Date productterpene_updatedate;
	private UUID lastupdatedby;

	@ManyToMany(mappedBy = "productxterpene")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private transient Set<Product> product;

	public UUID getProductterpene_id() {
		return productterpene_id;
	}

	public void setProductterpene_id(UUID productterpene_id) {
		this.productterpene_id = productterpene_id;
	}

	public UUID getProduct_id() {
		return product_id;
	}

	public void setProduct_id(UUID product_id) {
		this.product_id = product_id;
	}

	public UUID getTerpene_id() {
		return terpene_id;
	}

	public void setTerpene_id(UUID terpene_id) {
		this.terpene_id = terpene_id;
	}

	public Double getProductterpene_minperc() {
		return productterpene_minperc;
	}

	public void setProductterpene_minperc(Double productterpene_minperc) {
		this.productterpene_minperc = productterpene_minperc;
	}

	public Double getProductterpene_maxperc() {
		return productterpene_maxperc;
	}

	public void setProductterpene_maxperc(Double productterpene_maxperc) {
		this.productterpene_maxperc = productterpene_maxperc;
	}

	public Double getProductterpene_actualperc() {
		return productterpene_actualperc;
	}

	public void setProductterpene_actualperc(Double productterpene_actualperc) {
		this.productterpene_actualperc = productterpene_actualperc;
	}

	public Boolean getProductterpene_deleted() {
		return productterpene_deleted;
	}

	public void setProductterpene_deleted(Boolean productterpene_deleted) {
		this.productterpene_deleted = productterpene_deleted;
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

	public Timestamp getProductterpene_createdate() {
		return productterpene_createdate;
	}

	public void setProductterpene_createdate(Timestamp productterpene_createdate) {
		this.productterpene_createdate = productterpene_createdate;
	}

	public Date getProductterpene_updatedate() {
		return productterpene_updatedate;
	}

	public void setProductterpene_updatedate(Date productterpene_updatedate) {
		this.productterpene_updatedate = productterpene_updatedate;
	}

}
