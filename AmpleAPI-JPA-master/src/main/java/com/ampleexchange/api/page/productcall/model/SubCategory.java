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
@Table(name = "subcategory")  // Define Table name here
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID subcategory_id;
	@Column(nullable = true)
	private String subcategory_shortname;
	private String subcategory_longname;
	@Column(nullable = true)
	private Boolean subcategory_deleted;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date subcategory_createdate;
	@Basic
	@Temporal(TemporalType.DATE)
	private Date subcategory_updatedate;
	@Column(nullable = true)
	private UUID lastupdatedby;	
	
	@OneToMany(mappedBy = "subcategory")
	@ToString.Exclude @EqualsAndHashCode.Exclude private transient Set<ProductCall> productcall;
}
