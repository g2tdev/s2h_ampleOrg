package com.ampleexchange.api.page.product.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "uom")  
public class Uom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID uom_id;

	private String uom_shortname;
	private String uom_longname;
	private Double uom_conversionrate;
	private Boolean uom_base;
	private Boolean uom_deleted;	
	private Timestamp uom_createdate;
	private Date uom_updatedate;
	private UUID lastupdatedby;	
	private UUID baseuom_id;
	public UUID getUom_id() {
		return uom_id;
	}
	public void setUom_id(UUID uom_id) {
		this.uom_id = uom_id;
	}
	public String getUom_shortname() {
		return uom_shortname;
	}
	public void setUom_shortname(String uom_shortname) {
		this.uom_shortname = uom_shortname;
	}
	public String getUom_longname() {
		return uom_longname;
	}
	public void setUom_longname(String uom_longname) {
		this.uom_longname = uom_longname;
	}
	public Double getUom_conversionrate() {
		return uom_conversionrate;
	}
	public void setUom_conversionrate(Double uom_conversionrate) {
		this.uom_conversionrate = uom_conversionrate;
	}
	public Boolean getUom_base() {
		return uom_base;
	}
	public void setUom_base(Boolean uom_base) {
		this.uom_base = uom_base;
	}
	public Boolean getUom_deleted() {
		return uom_deleted;
	}
	public void setUom_deleted(Boolean uom_deleted) {
		this.uom_deleted = uom_deleted;
	}
	public Timestamp getUom_createdate() {
		return uom_createdate;
	}
	public void setUom_createdate(Timestamp uom_createdate) {
		this.uom_createdate = uom_createdate;
	}
	public Date getUom_updatedate() {
		return uom_updatedate;
	}
	public void setUom_updatedate(Date uom_updatedate) {
		this.uom_updatedate = uom_updatedate;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public UUID getBaseuom_id() {
		return baseuom_id;
	}
	public void setBaseuom_id(UUID baseuom_id) {
		this.baseuom_id = baseuom_id;
	}
	
}

