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
@Table(name = "organization")  // Define Table name here
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID org_id;	
	private String org_shortname;
	private String org_longname;
	private Character org_type;	
	private Boolean org_allowedtopickup;
	private UUID organizationstatus_id;
	private Character org_buysell;
	private Character org_buydomesticinternational;
	private Character org_selldomesticinternational;
	private Boolean org_deleted;	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date org_createdate;
	@Basic
	@Temporal(TemporalType.DATE)	
	private Date org_updatedate;
	@Column(nullable = true)
	private UUID lastupdatedby;	
	
	@OneToMany(mappedBy = "organization")
	@ToString.Exclude @EqualsAndHashCode.Exclude private transient Set<ProductCall> productcall;
	
}
