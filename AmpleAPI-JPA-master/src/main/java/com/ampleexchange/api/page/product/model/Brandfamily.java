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
@Table(name = "brandfamily")
public class Brandfamily {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID brandfamily_id;

	private String brandfamily_shortname;
	private String brandfamily_longname;
//	private String brand_logo;
	private Timestamp brandfamily_createdate;
	private Date brandfamily_updatedate;
	private Boolean brandfamily_deleted;
	private UUID lastupdatedby;
	public UUID getBrandfamily_id() {
		return brandfamily_id;
	}
	public void setBrandfamily_id(UUID brandfamily_id) {
		this.brandfamily_id = brandfamily_id;
	}
	public String getBrandfamily_shortname() {
		return brandfamily_shortname;
	}
	public void setBrandfamily_shortname(String brandfamily_shortname) {
		this.brandfamily_shortname = brandfamily_shortname;
	}
	public String getBrandfamily_longname() {
		return brandfamily_longname;
	}
	public void setBrandfamily_longname(String brandfamily_longname) {
		this.brandfamily_longname = brandfamily_longname;
	}

	public Timestamp getBrandfamily_createdate() {
		return brandfamily_createdate;
	}
	public void setBrandfamily_createdate(Timestamp brandfamily_createdate) {
		this.brandfamily_createdate = brandfamily_createdate;
	}
	public Date getBrandfamily_updatedate() {
		return brandfamily_updatedate;
	}
	public void setBrandfamily_updatedate(Date brandfamily_updatedate) {
		this.brandfamily_updatedate = brandfamily_updatedate;
	}
	public Boolean getBrandfamily_deleted() {
		return brandfamily_deleted;
	}
	public void setBrandfamily_deleted(Boolean brandfamily_deleted) {
		this.brandfamily_deleted = brandfamily_deleted;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	
}
