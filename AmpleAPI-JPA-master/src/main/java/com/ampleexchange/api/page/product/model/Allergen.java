package com.ampleexchange.api.page.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "allergen")
public class Allergen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID allergen_id;

	private String allergen_shortname;
	private String allergen_longname;
	private Integer allergen_sort;
	private Timestamp allergen_createdate;
	private Date allergen_updatedate;
	private Boolean allergen_deleted;
	private UUID lastupdatedby;
	
	@ManyToMany(mappedBy = "allergen")
	@ToString.Exclude @EqualsAndHashCode.Exclude private transient Set<Product> product;

	
}
