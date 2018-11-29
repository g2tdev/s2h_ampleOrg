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

import com.google.gson.annotations.Expose;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orgnanizationstatus")
public class OrganizationStatus implements Comparable<OrganizationStatus> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID orgnanizationstatus_id;
	
	@Expose
	private String orgnanizationstatus_type;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orgnanizationstatus_created;
	
	private Date orgnanizationstatus_updated;
	
	private Boolean orgnanizationstatus_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedby", referencedColumnName="user_id")
	private User lastupdatedby;

	@Override
	public int compareTo(OrganizationStatus o) {
		return orgnanizationstatus_id.compareTo(o.getOrgnanizationstatus_id());
	}
}