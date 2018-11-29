package com.ampleexchange.api.page.guidedsetup.dbservice;

import java.util.UUID;

import com.ampleexchange.api.page.guidedsetup.model.User;
 
public interface UserService {	
	public User save(User user);
	
	public User getById(UUID userId);
	
	public User getByEmail(String email);
 
}
