package com.ampleexchange.api.page.guidedsetup.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.google.gson.annotations.Expose;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "country")
public class Country implements Comparable<Country> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID country_id;
	
	@Expose
	private String country_shortname;
	
	@Expose
	private String country_longname;

	@Override
	public int compareTo(Country o) {
		return country_id.compareTo(o.getCountry_id());
	}
}