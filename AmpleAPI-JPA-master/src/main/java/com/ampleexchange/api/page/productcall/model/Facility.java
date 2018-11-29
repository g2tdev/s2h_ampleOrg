package com.ampleexchange.api.page.productcall.model;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "facility")  // Define Table name here
public class Facility {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID facility_id;
	private UUID org_id;
	private String facility_shortname;
	private String facility_longname;
	private String facility_apartment;
	private String facility_street;
	private String facility_city;
	private String facility_postalzip;
	//private UUID country_id;
	private Character facility_billtoshipto;
	private Character facility_buyingselling;
	private UUID tax_id;
	private UUID provincestate_id;	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date facility_createdate;
	@Basic
	@Temporal(TemporalType.DATE)	
	private Date facility_updatedate;	
	private Boolean facility_deleted;
	@Column(nullable = true)
	private UUID lastupdatedby;		
	
	@OneToMany(mappedBy = "facility")
	@ToString.Exclude @EqualsAndHashCode.Exclude private transient Set<ProductCall> productcall;
	
}