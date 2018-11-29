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
@Table(name = "carrieroil")
public class Carrieroil {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID carrieroil_id;

	private String carrieroil_shortname;
	private String carrieroil_longname;
	private Timestamp carrieroil_createdate;
	private Date carrieroil_updatedate;
	private Boolean carrieroil_deleted;
	private UUID lastupdatedby;

}
