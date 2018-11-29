package com.ampleexchange.api.controller;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampleexchange.api.authorization.annotation.CurrentUser;
import com.ampleexchange.api.authorization.config.ResultStatus;
import com.ampleexchange.api.authorization.manager.TokenManager;
import com.ampleexchange.api.authorization.model.ApiUser;
import com.ampleexchange.api.authorization.model.ResultModel;
import com.ampleexchange.api.authorization.model.TokenModel;
import com.ampleexchange.api.authorization.repository.AuthUserRepository;
import com.ampleexchange.api.common.MyConstants;
import com.ampleexchange.api.model.LoginData;
import com.ampleexchange.api.page.common.CommonPage;
import com.ampleexchange.api.page.download.DownloadFile;
import com.ampleexchange.api.page.guidedsetup.GuidedSetupPage;
import com.ampleexchange.api.page.guidedsetup.dbservice.UserService;
import com.ampleexchange.api.page.product.ProductPage;
import com.ampleexchange.api.page.product.model.Product;
import com.ampleexchange.api.page.productcall.ProductCallPage;
import com.ampleexchange.api.page.upload.UploadFiles;
//import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
//import com.alibaba.fastjson.JSONObject;

/**
 * @author JL
 * @date 2018/10/31.
 */
@Slf4j
@RestController
//@Controller
@RequestMapping(value = "/v1.0")
public class GeneralController {

	static Gson myGson = new Gson();
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private AuthUserRepository userRepository;
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private DownloadFile downloadFile;
	@Autowired
	private UploadFiles uploadFiles;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductCallPage productCallPage;
	@Autowired
	private CommonPage commonPage;
	@Autowired
	private GuidedSetupPage guidedSetupPage;
	@Autowired
	private ProductPage productPage;

	String apiVersion = "1.0";

	public static String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID

	/*
	 * If Login parameters,{"UserName":"User1","Password":"abcd1234"}, are passed in
	 * as JSON in HTTPRequest Body
	 * 
	 * @RequestMapping(value="/loginBody",method = RequestMethod.POST) public
	 * ResponseEntity<ResultModel> loginFromBody(HttpServletRequest request,
	 * HttpServletResponse response){ JSONObject jsonObj =
	 * RestfulApiHelper.GetJsonFromReqBody(request); String username =
	 * jsonObj.getString("UserName"); String password =
	 * jsonObj.getString("Password"); return loginUser(username, password); }
	 */

	// If Login parameters, loginData, are passed in as JSON HTTPRequest Body
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResultModel> loginFromBody(@RequestBody LoginData loginData) {
		return loginUser(loginData.getUserEmail(), loginData.getUserPassword());
	}

	private ResponseEntity<ResultModel> loginUser(String username, String password) {
		if (username == null || password == null) {
			return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR),
					HttpStatus.NOT_FOUND);
		}
		ApiUser user = userRepository.findByUsername(username);
		if (user == null || !user.getPassword().equals(password)) {
			return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR),
					HttpStatus.NOT_FOUND);
		}
		TokenModel model = tokenManager.duplicateLogin(user.getId());
		if (model == null) {
			// If not a duplicate login, then create token
			model = tokenManager.createToken(user.getId());
		}
		return new ResponseEntity<>(ResultModel.ok(model), HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	// @Authorization
	public ResponseEntity<ResultModel> logout(@CurrentUser ApiUser user) {
		tokenManager.deleteToken(user.getId());
		return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	// @Authorization
	public String apiList(@CurrentUser ApiUser user) {
		return myGson.toJson(productCallPage.getApiList());
	}

//**********************NEW BPC APIS*************************

	// BuyerProductCall API to insert a brand new record
	// @Authorization
	@RequestMapping(value = "/productcalls", method = RequestMethod.POST)
	public String bpcPageInsert(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "0"; // Insert = '0'
		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/*
	 * // BuyerProductCall API to get list of ALL product calls
	 * 
	 * @RequestMapping(value="/productcalls",method = RequestMethod.GET) public
	 * String bpcPageSelect(HttpServletRequest request, HttpServletResponse
	 * response){ String apiId = "7"; return
	 * productCallPage.processHttpRequest(request,response,apiVersion,apiId,userId,
	 * null);
	 * 
	 * }
	 */

	// BuyerProductCall API to get specific product call with id passed in URI
	@RequestMapping(value = "/productcalls/{id}", method = RequestMethod.GET)
	public String bpcPageSelectSingle(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> parametersMap = new HashMap();
		parametersMap.put(MyConstants.URL_QUERY, id);
		String apiId = "1"; // Insert = '0'
		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, parametersMap);
	}

	@RequestMapping(value = "/productcalls", method = RequestMethod.GET)
	public String bpcPageSelectSingle(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String mode, @RequestParam(required = false) String filter) {
		Map<String, String> parametersMap = new HashMap();
		// parametersMap.put(MyConstants.URL_QUERY, id);
		parametersMap.put("MODE", mode);
		parametersMap.put("FILTER", filter);

		String apiId = "8";
		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, parametersMap);
	}

	// BuyerProductCall API to delete a record
	// @Authorization
	@RequestMapping(value = "/productcalls", method = RequestMethod.DELETE)
	public String bpcPageDelete(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "2";
		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	// BuyerProductCall API to update a record
	// @Authorization
	@RequestMapping(value = "/productcalls", method = RequestMethod.PUT)
	public String bpcPageUpdate(HttpServletRequest request, HttpServletResponse response) {
		// apiId will be determinated by URI,then pass it to ProductCall class, so
		// ProductCall class will know which API implementation it should execute
		String apiId = "3";

		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	// BuyerProductCall API to finalize record to PUBLISHED STATE
	// @Authorization
	@RequestMapping(value = "/productcalls", method = RequestMethod.PATCH)
	public String bpcPagePublish(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "4";

		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);

	}

//    @RequestMapping(value="/bpc/constants",method = RequestMethod.GET)  
//    public String bpcConstants(HttpServletRequest request, HttpServletResponse response){
	// apiId will be determinated by URI,then pass it to ProductCall class, so
	// ProductCall class will know which API implementation it should execute
//    	String apiId = "7";  // Insert = '0' 
//        return productCallPage.processHttpRequest(request,response,apiVersion,apiId,userId);    	   	
//    }

	// Get all ProductCalls for Organization
	// @Authorization
	@RequestMapping(value = "/productcalls/seller", method = RequestMethod.GET)
	public String spcPageGetAllPcs(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "5";

		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	@RequestMapping(value = "/productcalls/buyer", method = RequestMethod.GET)
	public String bpcPageGetAllPcs(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "6";

		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	// **********************/END NEW BPC APIS********************

	// Sample code to call CommonPage API to get a full list of constants
	// @Authorization
	@RequestMapping(value = "/constants", method = RequestMethod.GET, produces = "application/vnd.api+json")
//    @RequestMapping(value="/constants",method = RequestMethod.GET,produces = "application/vnd.api+json; charset=utf-8")  
	public String commonPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// apiId will be determinated by URI,then pass it to Common class, so Common
		// class will know which API implementation it should execute
		String apiId = "0"; // Insert = '0'
		log.info("Start to process Common Page request... ");

		return commonPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);

	}

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "0";
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		return uploadFiles.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * String apiId = "0"; String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b";
		 * // Dummy ID return downloadFile.processHttpRequest(request, response,
		 * apiVersion, apiId, userId);
		 */
		/*
		 * ResponseEntity.ok()
		 * .contentType(MediaType.parseMediaType("application/octet-stream"))
		 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "\"")
		 * .body();
		 */
		return "";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String signinUser(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "0";
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		return guidedSetupPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	@RequestMapping(value = "/orgs", method = RequestMethod.PUT)
	public String updateOrganization(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "1";
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		return guidedSetupPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	@RequestMapping(value = "/orgs/{id}", method = RequestMethod.GET)
	public String getOrganizations(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		String apiId = "2";
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		Map<String, String> parametersMap = new HashMap();
		parametersMap.put(MyConstants.URL_QUERY, id);
		return guidedSetupPage.processHttpRequest(request, response, apiVersion, apiId, userId, parametersMap);
	}

	@RequestMapping(value = "/facilities", method = RequestMethod.POST)
	public String saveFacilityContactQA(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "3"; // Insert = '0'
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		return guidedSetupPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public String saveContact(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "4"; // Insert = '0'
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		return guidedSetupPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	@RequestMapping(value = "/facilities/{q}", method = RequestMethod.GET)
	public String getFacilities(@PathVariable("q") String query, @RequestParam(name = "paramtype") String queryType,
			HttpServletRequest request, HttpServletResponse response) {
		String apiId = "5"; // Insert = '0'
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		Map<String, String> parametersMap = new HashMap();
		parametersMap.put(MyConstants.URL_QUERY, query);
		parametersMap.put(MyConstants.QUERY_TYPE, queryType);
		return guidedSetupPage.processHttpRequest(request, response, apiVersion, apiId, userId, parametersMap);
	}

	@RequestMapping(value = "/contacts/{q}", method = RequestMethod.GET)
	public String getContacts(@PathVariable("q") String query, HttpServletRequest request,
			HttpServletResponse response) {
		String apiId = "6"; // Insert = '0'
		String userId = "679bdfc2-a255-4e9b-89c0-461bac28e64b"; // Dummy ID
		Map<String, String> parametersMap = new HashMap();
		parametersMap.put(MyConstants.URL_QUERY, query);
		parametersMap.put(MyConstants.QUERY_TYPE, "1");
		return guidedSetupPage.processHttpRequest(request, response, apiVersion, apiId, userId, parametersMap);
	}

	// @Authorization
	@RequestMapping(value = "/test/{pcId}", method = RequestMethod.POST)
	public String test(@PathVariable("pcId") String pcId, HttpServletRequest request, HttpServletResponse response,
			@RequestParam String mode, @RequestParam String filter) {
		Map<String, String> parametersMap = new HashMap();
		parametersMap.put(MyConstants.URL_QUERY, pcId);
		parametersMap.put("MODE", mode);
		parametersMap.put("FILTER", filter);
		// apiId will be determinated by URI,then pass it to ProductCall class, so
		// ProductCall class will know which API implementation it should execute
		String apiId = "8"; // Insert = '0'
		return productCallPage.processHttpRequest(request, response, apiVersion, apiId, userId, parametersMap);
	}

	/**
	 * SPP Pending : SPP1.0A-1 - upload files , SPP1.0A-2 - delete Image , SPP1.0C-1
	 * - upload file (documents) , SPP1.0E-3 - upload document files , SPP1.0E-4 -
	 * Delete image
	 */

	/**
	 * Product creation SPP1.0A-3
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/vnd.api+json")
	public String createProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Object product will be provided in postman Body as JSON for test purposes
		String apiId = "0"; // Insert = '0'
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * Product update : SPP1.0A-5, SPP1.0B-1, SPP1.0A-3, SPP1.0D-3 <this one is
	 * final> , SPP1.0E-2 - save product information
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/products", method = RequestMethod.PUT, produces = "application/vnd.api+json")
	public String updateProduct(HttpServletRequest request, HttpServletResponse response) {

		String apiId = "1";

		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * Product deletion : SPP1.0A-4 , SPP1.0B-2, SPP1.0C-2, SPP1.0D-2, SPP1.2-1 ,
	 * SPP1.0E-6 , SPP1.2-1 : Delete Delete flag to true
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/products", method = RequestMethod.DELETE, produces = "application/vnd.api+json")
	public String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "2";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * Product strain names : SPP1.0A-6 get All Strain names
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/products/strain", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String retrieveProductStrain(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "3";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * Product facility names : SPP1.0B-3
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/products/facility", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String retrieveFacilities(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "4";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * Fetch Product for editing : SPP1.0E-1 - get existing product info, SPP1.2-1
	 * Fetch Product Information with respect to Product id requested : SPC 1.1-2,
	 * SPP1.2-1
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String editProduct(@PathVariable("id") String productid, HttpServletRequest request,
			HttpServletResponse response) {
		String apiId = "5";

		Map<String, String> parametersMap = new HashMap();
		parametersMap.put("productid", productid);

		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, parametersMap);
	}

	/**
	 * SPP1.1-1 : products available in market for sale
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/products/onsale", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String saleProducts(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "6";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPC1.1-3 : deleted products
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/products/removed", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String removedProducts(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "7";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPC 1.1-4 : yet to release products
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/products/future", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String futureProducts(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "8";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPP1.0E-5 : Create New Brand
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/products/brand", method = RequestMethod.POST, produces = "application/vnd.api+json")
	public String createBrand(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Object product will be provided in postman Body as JSON for test purposes
		String apiId = "9"; // Insert = '0'
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPP1.0E-5 : Update Brand information
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/products/brand", method = RequestMethod.PUT, produces = "application/vnd.api+json")
	public String updateBrand(HttpServletRequest request, HttpServletResponse response) {

		String apiId = "10";

		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPP1.0E-5 : Create New Brand family
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/products/brandfamily", method = RequestMethod.POST, produces = "application/vnd.api+json")
	public String createBrandFamily(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Object product will be provided in postman Body as JSON for test purposes
		String apiId = "11"; // Insert = '0'
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPP1.0E-5 : Update Brand family information
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/products/brandfamily", method = RequestMethod.PUT, produces = "application/vnd.api+json")
	public String updateBrandFamily(HttpServletRequest request, HttpServletResponse response) {

		String apiId = "12";

		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPP1.0E-5 : Get all brands information - active
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/products/brand", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String retrieveBrands(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "13";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

	/**
	 * SPP1.0E-5 : Get all brandfamily information - active
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/products/brandfamily", method = RequestMethod.GET, produces = "application/vnd.api+json")
	public String retrieveBrandfamily(HttpServletRequest request, HttpServletResponse response) {
		String apiId = "14";
		return productPage.processHttpRequest(request, response, apiVersion, apiId, userId, null);
	}

}
