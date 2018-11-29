package com.ampleexchange.api.page.productcall.dbservice;


import java.util.UUID;

import com.ampleexchange.api.page.productcall.model.ApiUser;

public interface ApiUserDBService {
	public UUID getOrgId(String userId);
	
	public String getApiUserName(UUID userId);
}
