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
@Table(name = "contact")
public class Contact implements Comparable<Contact> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID contact_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="org_id", referencedColumnName="org_id")
	private Organization org_id;
	
	@Expose
	private String contact_firstname;
	
	@Expose
	private String contact_lastname;
	
	@Expose
	private String contact_role;
	
	@Expose
	private String contact_jobtitle;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date contact_createdate;	
	
	private Date contact_updatedate;
	
	private Boolean contact_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedby", referencedColumnName="user_id")
	private User lastupdatedby;	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contact_id")
	private Set<ContactComm> contactComms;

	@Override
	public int compareTo(Contact o) {
		return contact_id.compareTo(o.getContact_id());
	}
	
	@Expose
	@Transient
	private String contact_email;
	
	@Expose
	@Transient
	private String contact_phonenumber;
	

	@Expose
	@Transient
	private String contact_altphonenumber;
	
	@Expose
	@Transient
	private String contact_fax;
}