package com.ampleexchange.api.page.common.modelhelper;

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
public class OrganizationHelper {

	private String org_id;
	private String selectCompanyStatus;
	private String buySellOrBoth;
	private String buyDomesticInternational;
	private String sellDomesticInternational;
}