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
@Table(name = "vendorrelation") 
public class VendorRelation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID org_id;
	private UUID vendorrelation_org_id;
	private Integer vendorrelation_type;
	private Boolean vendorrelation_deleted;
	private Timestamp vendorrelation_createdate;
	private Date vendorrelation_updatedate;
	private UUID lastupdatedby;


}
