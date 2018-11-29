package com.ampleexchange.api.page.common.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.model.ApiInfo;
import com.ampleexchange.api.page.common.CommonPage;
import com.ampleexchange.api.page.common.dbservice.impl.CommonDBServiceImpl;
import com.ampleexchange.api.util.RestfulApiHelper;
import com.google.gson.Gson;

@Service
public class CommonPageImpl implements CommonPage {
	@Autowired
	private CommonDBServiceImpl commonDBServiceImpl;
	
	@Override
	public String processHttpRequest(HttpServletRequest request, HttpServletResponse response, String apiVersion, String apiId, String userId, Map<String, String> parametersMap){		
		// TODO Auto-generated method stub 
		String retVal="", cId="";
		Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);		
		if(jObj != null && jObj.containsKey("constant_id")){
			cId = jObj.get("constant_id");
		}
		
		switch(apiId){
		case "0": // '0' means to get all constants or by Id
			if (StringHelper.isEmpty(cId))
				retVal = getConstants(request,response);
			else
				retVal = getConstantById(cId);				
			break;
		}
		return retVal;
	}

	private String getConstants(HttpServletRequest request, HttpServletResponse response){		
    // return JSONObject.toJSONString(constantsService.getConstants());
    Gson gson = new Gson();
    return gson.toJson(commonDBServiceImpl.findAll());
	}
	

	private String getConstantById(String cId){		
    // return JSONObject.toJSONString(constantsService.getConstants());
    Gson gson = new Gson();
    return gson.toJson(commonDBServiceImpl.findById(cId));
	}
	
	@Override
	public List<ApiInfo> getApiList() {
		ArrayList<ApiInfo> myList = new ArrayList<ApiInfo>(); 
		myList.add(new ApiInfo("ConstantsCall","To get a full list of constants from db","/v*/constants","GET","CommonPageImpl"));
		return myList;
	}

}
