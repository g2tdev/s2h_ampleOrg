package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.ProvinceState;
 
public interface ProvinceStateService {
	public ProvinceState getById(UUID provinceStateId);
}