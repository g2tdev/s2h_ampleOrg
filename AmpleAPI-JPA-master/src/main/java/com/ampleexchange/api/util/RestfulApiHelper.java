package com.ampleexchange.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestfulApiHelper {

	public static String GetDataFromReqBody(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/*
	 * public static JSONObject GetJsonFromReqBody(HttpServletRequest request){
	 * StringBuilder sb = new StringBuilder(); try { BufferedReader br = new
	 * BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
	 * String line = null; while ((line = br.readLine()) != null) {
	 * sb.append(line); } } catch (IOException e) { e.printStackTrace(); }
	 * JSONObject jsonObj = JSON.parseObject(sb.toString()); return jsonObj; }
	 */
	public static Map<String, String> GetJsonFromReqBody( HttpServletRequest request) {
		Gson gson = new Gson();
		Map<String, String> myMap = gson.fromJson(GetStringFromReqBody(request),
				new TypeToken<Map<String, String>>() {
				}.getType());

		return myMap;
	}
	public static Map<String, String> GetMapFromJson(String jsonStr) {
		Gson gson = new Gson();
		Map<String, String> myMap = gson.fromJson(jsonStr, new TypeToken<Map<String, String>>() {}.getType());
		return myMap;
	}
	/* Return the JASON String back */
	public static String GetStringFromReqBody(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

}
