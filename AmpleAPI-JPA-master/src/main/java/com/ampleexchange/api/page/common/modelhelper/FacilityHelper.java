package com.ampleexchange.api.page.common.modelhelper;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FacilityHelper {

	private String facility_id;
	private String org_id;
	private String facilityName;
	private String aptOrSuite;
	private String address;
	private String city;
	private String provinceOrState;
	private String postalCode;
	private boolean billto;
	private boolean shipto;
	private String healthCanadaLicenceNumber;
	private String healthCanadaLicenceType;
	private String healthCanadaLicenceExpiryDate;
	private List<ContactHelper> contacts;
}