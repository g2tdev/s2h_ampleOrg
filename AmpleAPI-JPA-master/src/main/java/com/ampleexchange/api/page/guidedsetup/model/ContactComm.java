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
@Table(name = "contactcomm")
public class ContactComm implements Comparable<ContactComm>{
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID contactcomm_id;
	
	@ManyToOne
	@JoinColumn(name="contact_id")
	private Contact contact_id;
	
	private String contactcomm_type;
	
	private String contactcomm_detail;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date contact_createdate;
	
	private Date contact_updatedate;
	
	private Boolean contact_deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lastupdatedby", referencedColumnName="user_id")
	private User lastupdatedby;

	@Override
	public int compareTo(ContactComm o) {
		return contactcomm_id.compareTo(o.getContactcomm_id());
	}
}