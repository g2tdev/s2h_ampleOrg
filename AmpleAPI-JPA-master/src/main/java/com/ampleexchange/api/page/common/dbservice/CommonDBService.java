package com.ampleexchange.api.page.common.dbservice;

import java.util.List;

import com.ampleexchange.api.page.common.model.Constant;

public interface CommonDBService {
  public List<Constant> findAll();
  public Constant findById(String id);
}
