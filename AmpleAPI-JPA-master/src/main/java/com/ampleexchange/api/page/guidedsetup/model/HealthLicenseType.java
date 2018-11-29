package com.ampleexchange.api.page.guidedsetup.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "healthlicensetype")
public class HealthLicenseType implements Comparable<HealthLicenseType> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID healthlicensetype_id;
	
	@Expose
	private String healthlicensetype_shortname;
	
	@Expose
	private String healthlicensetype_longname;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id", referencedColumnName="country_id")
	private Country country_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date companystatus_created;
	
	private Date companystatus_updated;
	
	private Boolean companystatus_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedbyid", referencedColumnName="user_id")
	private User lastupdatedbyid;

	@Override
	public int compareTo(HealthLicenseType o) {
		return healthlicensetype_id.compareTo(o.getHealthlicensetype_id());
	}
}