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
import javax.persistence.ManyToMany;
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
@Table(name = "allergen")  // Define Table name here
public class Allergen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID allergen_id;
	@Column(nullable = true)
	private String allergen_shortname;
	private String allergen_longname;
	private Integer allergen_sort;
	@Column(nullable = true)
	private Boolean allergen_deleted;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date allergen_createdate;
	@Basic
	@Temporal(TemporalType.DATE)
	private Date allergen_updatedate;
	@Column(nullable = true)
	private UUID lastupdatedby;	
	
	@ManyToMany(mappedBy = "allergen")
	@ToString.Exclude @EqualsAndHashCode.Exclude private transient Set<ProductCall> productcall;
}
