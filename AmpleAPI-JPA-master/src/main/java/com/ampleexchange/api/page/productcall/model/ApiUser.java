package com.ampleexchange.api.page.productcall.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString 
@Entity
@Table(name = "apiuser")  // Define Table name here
public class ApiUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID user_id;
	private UUID org_id;
	private String user_email;
	private String user_password;
	private String user_name;
	private String user_role;
	private Boolean user_deleted;
	private Timestamp user_createdate;
	private Date user_updatedate;
	private UUID lastupdatedby;
	public UUID getUser_id() {
		return user_id;
	}
	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
	public UUID getOrg_id() {
		return org_id;
	}
	public void setOrg_id(UUID org_id) {
		this.org_id = org_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public Boolean getUser_deleted() {
		return user_deleted;
	}
	public void setUser_deleted(Boolean user_deleted) {
		this.user_deleted = user_deleted;
	}
	public Timestamp getUser_createdate() {
		return user_createdate;
	}
	public void setUser_createdate(Timestamp user_createdate) {
		this.user_createdate = user_createdate;
	}
	public Date getUser_updatedate() {
		return user_updatedate;
	}
	public void setUser_updatedate(Date user_updatedate) {
		this.user_updatedate = user_updatedate;
	}
	public UUID getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

}
