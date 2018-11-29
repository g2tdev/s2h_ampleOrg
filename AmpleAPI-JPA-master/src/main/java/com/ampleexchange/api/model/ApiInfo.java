package com.ampleexchange.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiInfo {
	private String apiName;
	private String apiDescription;
	private String apiUri;
	private String httpType;
	private String pageName;
	
	public ApiInfo(String aName,String aDescription,String aUri,String hType,String pName){
		apiName = aName;
		apiDescription = aDescription;
		apiUri = aUri;
		httpType = hType;
		pageName = pName;
	}
}
