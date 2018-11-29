
package com.ampleexchange.api.common;

import org.springframework.beans.factory.annotation.Value;

public class MyConstants {
	public static final String URL_QUERY = "URL_QUERY";
	public static final String QUERY_TYPE = "QUERY_TYPE";
	public static final String ROLE_ADMIN = "Admin";
	public static final String NEW_ORG_STATUS = "New organization";
	public static final String RETURN_MESSAGE_TITLE = "Return Message";
	public static final String ERR_ORG_NOT_EXIST = "Organization has not been set up, contact your administrator";
//	public static final JSONObject CATEGORIES = JSON.parseObject("{\"firstCategory\":\"Dried Flower\", \"secondCategory\":\"Oils\", \"thirdCategory\":\"Capsules\", \"fourthCategory\":\"Pre-rolls\"}");
//	public static final JSONObject SUBCATEGORIES = JSON.parseObject("{\"firstSubCategory\":\"Blend\", \"secondSubCategory\":\"Indica Dominant\", \"thirdSubCategory\":\"Sativa Dominant\", \"fourthSubCategory\":\"Hybrid\", \"fithSubCategory\":\"Indica\", \"sixthSubCategory\":\"Sativa\"}");

	public static final String BPCDRAFTSTATUS = "Draft";
	public static final String BPCPUBLISHEDSTATUS = "Published";
	public static final String BPCDELETEDSTATUS = "Deleted";
	public static final String BPCCREATIONTITLE = "Untitled";

	// public static final String FILEPATH = "C:\\Users\\Jalpa\\Desktop\\";
	@Value("${FILEPATH}")
	public static String FILEPATH;

	public static final class CATEGORIES {
		String firstCategory = "Dried Flower";
		String secondCategory = "Oils";
		String thirdCategory = "Capsules";
		String fourthCategory = "Pre-rolls";
	}

	public static final class SUBCATEGORIES {
		String firstSubCategory = "Blend";
		String secondSubCategory = "Indica Dominant";
		String thirdSubCategory = "Sativa Dominant";
		String fourthSubCategory = "Hybrid";
		String fifthSubCategory = "Indica";
		String sixthSubCategory = "Sativa";
	}
}
