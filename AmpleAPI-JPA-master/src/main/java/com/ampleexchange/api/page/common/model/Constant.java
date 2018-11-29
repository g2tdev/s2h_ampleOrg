package com.ampleexchange.api.page.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "constantview")  // Define Table name here
public class Constant {
	private String constant_name;
  @Id
  @org.hibernate.annotations.Type(type = "pg-uuid")  
	private UUID constant_id;
	private String constant_longname;
	private String constant_shortname;
	private UUID dependant_id;
	private String dependant_longname;
	private String dependant_shortname;
}
