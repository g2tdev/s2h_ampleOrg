package com.ampleexchange.api.page.guidedsetup.dbservice.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.page.guidedsetup.dbservice.UserService;
import com.ampleexchange.api.page.guidedsetup.model.User;
import com.ampleexchange.api.page.guidedsetup.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getById(UUID userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public User getByEmail(String email) { 
		Set<User> setUsers = userRepository.findByEmail(email);
		if(!setUsers.isEmpty()){
			return setUsers.iterator().next();
		}
		
		return null;
	}

}
