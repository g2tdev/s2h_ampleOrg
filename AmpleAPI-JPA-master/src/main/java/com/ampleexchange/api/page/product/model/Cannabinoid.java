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
@Table(name = "cannabinoid")
public class Cannabinoid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID cannabinoid_id;

	private String cannabinoid_shortname;
	private String cannabinoid_longname;
	private Integer cannabinoid_sort;
	private Timestamp cannabinoid_createdate;
	private Date cannabinoid_updatedate;
	private Boolean cannabinoid_deleted;
	private UUID lastupdatedby;

}
