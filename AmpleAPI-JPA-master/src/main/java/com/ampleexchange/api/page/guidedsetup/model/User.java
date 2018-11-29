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
@Table(name = "apiuser")
public class User implements Comparable<User> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID user_id;
	
	@Expose
	private String user_email;
	
	private String user_password;
	
	@Expose
	private String user_name;
	
	@Expose
	private String user_role;
	
	private Boolean user_deleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date user_createdate;
	
	private Date user_updatedate;
	
	private UUID lastupdatedby;
		
	@Expose
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="org_id", referencedColumnName="org_id")	
	private Organization org_id;

	@Override
	public int compareTo(User o) {
		return user_id.compareTo(o.user_id);
	}
}
