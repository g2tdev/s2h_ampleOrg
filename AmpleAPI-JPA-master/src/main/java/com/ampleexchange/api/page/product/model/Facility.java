package com.ampleexchange.api.page.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
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
@Table(name = "facility")
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
	private UUID country_id;
	private String facility_billtoshipto;
	private String facility_buyingselling;
	private UUID tax_id;
	private UUID provincestate_id;
	private Timestamp facility_createdate;
	private Date facility_updatedate;
	private Boolean facility_deleted;
	private UUID lastupdatedby;

}
