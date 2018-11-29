package com.ampleexchange.api.page.upload.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.util.StringHelper;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.authorization.model.ResultModel;
import com.ampleexchange.api.common.MyConstants;
import com.ampleexchange.api.model.ApiInfo;
import com.ampleexchange.api.page.upload.UploadFiles;
import com.ampleexchange.api.util.FileHelper;
import com.google.gson.JsonObject;

@Service
public class UploadFilesImpl implements UploadFiles {

	@Override
	public String processHttpRequest(HttpServletRequest request,
			HttpServletResponse response, String apiVersion, String apiId,
			String userId, Map<String, String> parametersMap) {
		String retVal = FileHelper.uploadFiles(request, userId);
		if (StringHelper.isEmpty(retVal)) {
			return ResultModel.ok().getMessage();
		} else {
			JsonObject jObject = new JsonObject();
			jObject.addProperty(MyConstants.RETURN_MESSAGE_TITLE, retVal);
			return jObject.toString();
		}
	}

	@Override
	public List<ApiInfo> getApiList() {
		ArrayList<ApiInfo> myList = new ArrayList<ApiInfo>();
		myList.add(new ApiInfo(
				"UploadFilesImpl",
				"To select and upload local files as MultipartContent to server",
				"/v*/file", "POST", "Generic"));
		return myList;
	}

}
