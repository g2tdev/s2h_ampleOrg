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
@Table(name = "brand")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID brand_id;
	
	private UUID brandfamily_id;
	private String brand_shortname;
	private String brand_longname;
	private String brand_logo;
	private Timestamp brand_createdate;
	private Date brand_updatedate;
	private Boolean brand_deleted;
	private UUID lastupdatedby;
	public UUID getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(UUID brand_id) {
		this.brand_id = brand_id;
	}
	public UUID getBrandfamily_id() {
		return brandfamily_id;
	}
	public void setBrandfamily_id(UUID brandfamily_id) {
		this.brandfamily_id = brandfamily_id;
	}
	public String getBrand_shortname() {
		return brand_shortname;
	}
	public void setBrand_shortname(String brand_shortname) {
		this.brand_shortname = brand_shortname;
	}
	public String getBrand_longname() {
		return brand_longname;
	}
	public void setBrand_longname(String brand_longname) {
		this.brand_longname = brand_longname;
	}
	public String getBrand_logo() {
		return brand_logo;
	}
	public void setBrand_logo(String brand_logo) {
		this.brand_logo = brand_logo;
	}
	public Timestamp getBrand_createdate() {
		return brand_createdate;
	}
	public void setBrand_createdate(Timestamp brand_createdate) {
		this.brand_createdate = brand_createdate;
	}
	public Date getBrand_updatedate() {
		return brand_updatedate;
	}
	public void setBrand_updatedate(Date brand_updatedate) {
		this.brand_updatedate = brand_updatedate;
	}
	public Boolean getBrand_deleted() {
		return brand_deleted;
	}
	public void setBrand_deleted(Boolean brand_deleted) {
		this.brand_deleted = brand_deleted;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	
}
