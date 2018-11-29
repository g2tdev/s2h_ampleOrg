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
public class UserHelper {

	private String Email;
	private String Role;
	private String Status;
	private String OrganizationName;
	private String Password;
}