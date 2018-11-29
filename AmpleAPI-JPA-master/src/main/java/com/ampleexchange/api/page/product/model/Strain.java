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
@Table(name = "strain")
public class Strain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID strain_id;

	private String strain_shortname;
	private String strain_longname;
	private Timestamp strain_createdate;
	private Date strain_updatedate;
	private Boolean strain_deleted;
	private UUID lastupdatedby;
	public UUID getStrain_id() {
		return strain_id;
	}
	public void setStrain_id(UUID strain_id) {
		this.strain_id = strain_id;
	}
	public String getStrain_shortname() {
		return strain_shortname;
	}
	public void setStrain_shortname(String strain_shortname) {
		this.strain_shortname = strain_shortname;
	}
	public String getStrain_longname() {
		return strain_longname;
	}
	public void setStrain_longname(String strain_longname) {
		this.strain_longname = strain_longname;
	}
	public Timestamp getStrain_createdate() {
		return strain_createdate;
	}
	public void setStrain_createdate(Timestamp strain_createdate) {
		this.strain_createdate = strain_createdate;
	}
	public Date getStrain_updatedate() {
		return strain_updatedate;
	}
	public void setStrain_updatedate(Date strain_updatedate) {
		this.strain_updatedate = strain_updatedate;
	}
	public Boolean getStrain_deleted() {
		return strain_deleted;
	}
	public void setStrain_deleted(Boolean strain_deleted) {
		this.strain_deleted = strain_deleted;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
}
