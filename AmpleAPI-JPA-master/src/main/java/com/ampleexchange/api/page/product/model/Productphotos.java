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
@Table(name = "productsphotos")
public class Productphotos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID productphoto_id;

	private UUID product_id;
	private String photo_type;
	private String productphoto_linkid;
	private Timestamp productphoto_createdate;
	private Date productphote_updatedate;
	private Boolean productphote_deleted;
	private UUID lastupdatedby;
	private Integer productphoto_sort;
	public UUID getProductphoto_id() {
		return productphoto_id;
	}
	public void setProductphoto_id(UUID productphoto_id) {
		this.productphoto_id = productphoto_id;
	}
	public UUID getProduct_id() {
		return product_id;
	}
	public void setProduct_id(UUID product_id) {
		this.product_id = product_id;
	}
	public String getPhoto_type() {
		return photo_type;
	}
	public void setPhoto_type(String photo_type) {
		this.photo_type = photo_type;
	}
	public String getProductphoto_linkid() {
		return productphoto_linkid;
	}
	public void setProductphoto_linkid(String productphoto_linkid) {
		this.productphoto_linkid = productphoto_linkid;
	}
	public Timestamp getProductphoto_createdate() {
		return productphoto_createdate;
	}
	public void setProductphoto_createdate(Timestamp productphoto_createdate) {
		this.productphoto_createdate = productphoto_createdate;
	}
	public Date getProductphote_updatedate() {
		return productphote_updatedate;
	}
	public void setProductphote_updatedate(Date productphote_updatedate) {
		this.productphote_updatedate = productphote_updatedate;
	}
	public Boolean getProductphote_deleted() {
		return productphote_deleted;
	}
	public void setProductphote_deleted(Boolean productphote_deleted) {
		this.productphote_deleted = productphote_deleted;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public Integer getProductphoto_sort() {
		return productphoto_sort;
	}
	public void setProductphoto_sort(Integer productphoto_sort) {
		this.productphoto_sort = productphoto_sort;
	}
}
