package com.ampleexchange.api.page.guidedsetup.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "provincestate")
public class ProvinceState implements Comparable<ProvinceState> {
	@Expose
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID provincestate_id;
	
	@Expose
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id", referencedColumnName="country_id")
	private Country country_id;
	
	@Expose
	private String provincestate_shortname;
	
	@Expose
	private String provincestate_longname;
	
	@Expose
	private Integer provincestate_sort;

	@Override
	public int compareTo(ProvinceState o) {
		return provincestate_id.compareTo(o.getProvincestate_id());
	}
}