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
@Table(name = "dryingmethod")  // Define Table name here
public class DryingMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID dryingmethod_id;
	@Column(nullable = true)
	private String dryingmethod_shortname;
	private String dryingmethod_longname;	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date dryingmethod_createdate;
	@Basic
	@Temporal(TemporalType.DATE)	
	private Date dryingmethod_updatedate;
	@Column(nullable = true)
	private Boolean dryingmethod_deleted;
	@Column(nullable = true)
	private UUID lastupdatedby;	
	
	@OneToMany(mappedBy = "dryingmethod")
	@ToString.Exclude @EqualsAndHashCode.Exclude private transient Set<ProductCall> productcall;
	
}
