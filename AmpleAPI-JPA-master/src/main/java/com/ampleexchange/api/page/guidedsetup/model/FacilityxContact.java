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
@Table(name = "facilityxcontact")
public class FacilityxContact implements Comparable<FacilityxContact>{
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID facilityxcontact_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="facility_id", referencedColumnName="facility_id")
	private Facility facility_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="contact_id", referencedColumnName="contact_id")
	private Contact contact_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date facilitycontact_createdate;	
	
	private Date facilitycontact_updatedate;
	
	private Boolean facilitycontact_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedby", referencedColumnName="user_id")
	private User lastupdatedby;

	@Override
	public int compareTo(FacilityxContact o) {
		return facilityxcontact_id.compareTo(o.getFacilityxcontact_id());
	}
}