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
@Table(name = "extractionprocess")  // Define Table name here
public class ExtractionProcess{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID extractionprocess_id;	
	private String extractionprocess_shortname;
	private String extractionprocess_longname;		
	private Boolean extractionprocess_deleted;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date extractionprocess_createdate;
	@Basic
	@Temporal(TemporalType.DATE)
	private Date extractionprocess_updatedate;
	@Column(nullable = true)
	private UUID lastupdatedby;	
	
	@OneToMany(mappedBy = "extractionprocess")
	@ToString.Exclude @EqualsAndHashCode.Exclude private transient Set<ProductCall> productcall;

}
