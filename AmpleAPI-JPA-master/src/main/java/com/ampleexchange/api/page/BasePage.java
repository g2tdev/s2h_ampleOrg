package com.ampleexchange.api.page;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import com.ampleexchange.api.authorization.model.ResultModel;
import com.ampleexchange.api.model.ApiInfo;

public interface BasePage {
	public abstract String processHttpRequest(HttpServletRequest request, HttpServletResponse response, String apiVersion, String apiId, String userId, Map<String, String> parametersMap);	
	public abstract List<ApiInfo> getApiList();	
}
