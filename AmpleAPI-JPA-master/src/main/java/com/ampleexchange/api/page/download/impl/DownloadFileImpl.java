package com.ampleexchange.api.page.download.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ampleexchange.api.model.ApiInfo;
import com.ampleexchange.api.page.download.DownloadFile;
import com.ampleexchange.api.util.FileHelper;

@Service
public class DownloadFileImpl implements DownloadFile{

	@Override
	public String processHttpRequest(HttpServletRequest request, HttpServletResponse response, String apiVersion, String apiId, String userId, Map<String, String> parametersMap) {
		// TODO Auto-generated method stub
		String savePath = "D:\\temp\\downloads\\";
  	String fileName = request.getParameter("FileName");
  	String retVal = FileHelper.downloadFile(request, savePath, fileName,response);
  	return retVal;
	}

	@Override
	public List<ApiInfo> getApiList() {
		ArrayList<ApiInfo> myList = new ArrayList<ApiInfo>(); 
		myList.add(new ApiInfo("DownloadFileImpl","To select and download file from server","/v*/file?FileName=filenamewithoutfolder","GET","Generic"));
		return myList;
	}

}
