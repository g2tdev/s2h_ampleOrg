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
public class ContactHelper {

	private String contact_id;
	private String org_id;
	private String facility_id;
	private String firstName;
	private String lastName;
	private String contactRole;
	private String jobTitle;
	private String email;
	private String phoneNumber;
	private String altPhoneNumber;
	private String fax;
}