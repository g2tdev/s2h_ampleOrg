package com.ampleexchange.api.page.guidedsetup.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
@Table(name = "facility")
public class Facility implements Comparable<Facility> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID facility_id;
	
	@Expose
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="org_id", referencedColumnName="org_id")
	private Organization org_id;
	
	@Expose
	private String facility_shortname;
	
	@Expose
	private String facility_longname;
	
	@Expose
	private String facility_apartment;
	
	@Expose
	private String facility_street;
	
	@Expose
	private String facility_city;
	
	@Expose
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="provincestate_id", referencedColumnName="provincestate_id")
	private ProvinceState provincestate_id;
	
	@Expose
	private String facility_postalzip;
	
	@Expose
	private String facility_billtoshipto;
	
	@Expose
	private String facility_buyingselling;
	
	@Expose
	private UUID tax_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date facility_createdate;	
	
	private Date Facility_updatedate;
	
	private Boolean facility_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedby", referencedColumnName="user_id")
	private User lastupdatedby;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "facility_id")
	private Set<FacilityxContact> facilityxcontacts;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "facility_id")
	private Set<FacilityxHealthlicense> facilityxhealthlicenses;

	@Override
	public int compareTo(Facility o) {
		return facility_id.compareTo(o.getFacility_id());
	}
	
	@Expose
	@Transient
	private Set<Contact> contacts;
	
	@Expose
	@Transient
	private HealthLicenseType facilityhealthlicense_type;
	
	@Expose
	@Transient
	private String facilityhealthlicense_number;
	
	@Expose
	@Transient
	private Date facilityhealthlicense_expirydate;
}