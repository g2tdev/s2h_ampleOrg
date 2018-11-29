package com.ampleexchange.api.page.common.dbservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.common.dbservice.CommonDBService;
import com.ampleexchange.api.page.common.model.Constant;
import com.ampleexchange.api.page.common.repository.ConstantRepository;

@Service
@Transactional
public class CommonDBServiceImpl implements CommonDBService{
  @Autowired
  private ConstantRepository constantRepository;
  
	@Override
	public List<Constant> findAll() {
    return constantRepository.findAll();
	}

	@Override
	public Constant findById(String id) {
    return constantRepository.findById(UUID.fromString(id)).get();
	}

}
