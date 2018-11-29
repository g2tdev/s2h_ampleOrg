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
@Table(name = "organization")
public class Organization implements Comparable<Organization> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID org_id;
	
	@Expose
	private String org_shortname;
	
	@Expose
	private String org_longname;
	
	@Expose
	private String org_type;
	
	@Expose
	private Boolean org_allowedtopickup;
	
	@Expose
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="organizationstatus_id", referencedColumnName="orgnanizationstatus_id")	
	private OrganizationStatus organizationstatus_id;
	
	@Expose
	private String org_buysell;
	
	@Expose
	private String org_buydomesticinternational;
	
	@Expose
	private String org_selldomesticinternational;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date org_createdate;
	
	private Date org_updatedate;
	
	private Boolean org_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedby", referencedColumnName="user_id")
	private User lastupdatedby;

	@Override
	public int compareTo(Organization o) {
		return org_id.compareTo(o.getOrg_id());
	}
}