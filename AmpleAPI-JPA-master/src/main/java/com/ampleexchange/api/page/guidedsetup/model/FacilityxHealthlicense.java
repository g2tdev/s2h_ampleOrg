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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "facilityxhealthlicense")
public class FacilityxHealthlicense implements Comparable<FacilityxHealthlicense>{
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID facilityhealthlicense_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="facility_id", referencedColumnName="facility_id")
	private Facility facility_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="healthlicensetype_id", referencedColumnName="healthlicensetype_id")
	private HealthLicenseType healthlicensetype_id;
	
	private String facilityhealthlicense_status;
	
	private String facilityhealthlicense_number;
	
	private Date facilityhealthlicense_expirydate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date facilityhealthlicense_createdate;	
	
	private Date facilityhealthlicense_updatedate;
	
	private Boolean facilityhealthlicense_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedby", referencedColumnName="user_id")
	private User lastupdatedby;

	@Override
	public int compareTo(FacilityxHealthlicense o) {
		return facilityhealthlicense_id.compareTo(o.getFacilityhealthlicense_id());
	}
}