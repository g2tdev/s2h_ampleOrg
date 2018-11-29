package com.ampleexchange.api.page.productcall.impl;


import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.common.MyConstants;
import com.ampleexchange.api.model.ApiInfo;
import com.ampleexchange.api.page.productcall.ProductCallPage;
import com.ampleexchange.api.page.productcall.dbservice.ProductCallDBService;

import com.ampleexchange.api.page.productcall.dbservice.impl.ApiUserDBServiceImpl;
import com.ampleexchange.api.page.productcall.dbservice.impl.ProductCallDBServiceImpl;
import com.ampleexchange.api.page.productcall.dbservice.impl.ProductcallXAllergenDBServiceImpl;
import com.ampleexchange.api.page.productcall.dbservice.impl.ProductcallXCannabinoidDBServiceImpl;
import com.ampleexchange.api.page.productcall.dbservice.impl.ProductcallXCarrieroilDBServiceImpl;
import com.ampleexchange.api.page.productcall.dbservice.impl.ProductcallXTerpeneDBServiceImpl;
import com.ampleexchange.api.page.productcall.model.ApiUser;
import com.ampleexchange.api.page.productcall.model.BpcReturn;
import com.ampleexchange.api.page.productcall.model.Category;
import com.ampleexchange.api.page.productcall.model.Constants;

import com.ampleexchange.api.page.productcall.model.ProductCall;
import com.ampleexchange.api.page.productcall.model.ProductcallXAllergen;
import com.ampleexchange.api.page.productcall.model.ProductcallXCannabinoid;
import com.ampleexchange.api.page.productcall.model.ProductcallXCarrieroil;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;
import com.ampleexchange.api.page.productcall.model.SpcReturn;
import com.ampleexchange.api.page.productcall.model.Terpene;
import com.ampleexchange.api.util.HibernateProxyTypeAdapter;
import com.ampleexchange.api.util.RestfulApiHelper;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@Service
public class ProductCallPageImpl implements ProductCallPage {
	//@Autowired
	//private UserService userService;
	//@Autowired
	//private CategoryService categoryService;
	
	@Autowired
	private ProductCallDBServiceImpl productCallService;
	
	@Autowired
	private ApiUserDBServiceImpl apiUserService;
	
	@Autowired 
	private ProductcallXTerpeneDBServiceImpl productcallXTerpeneService;
	
	@Autowired 
	private ProductcallXCannabinoidDBServiceImpl productcallXCannabinoidService;
	
	@Autowired 
	private ProductcallXAllergenDBServiceImpl productcallXAllergenService;
	
	@Autowired 
	private ProductcallXCarrieroilDBServiceImpl productcallXCarrieroilService;
	
	/* replaced by apiId = 8
	@Override
	public String processHttpRequest(HttpServletRequest request, HttpServletResponse response, String apiVersion, String apiId, String userId, PCQueryParameters queryParameters) {
		String retVal="";
		String productCallId = queryParameters.id;
		retVal = selectProductCallSingle(request, response, userId, productCallId);
		return retVal;
	}

	//Function to select specified product call with passed product_id from the URI	
	private String selectProductCallSingle(HttpServletRequest request, HttpServletResponse response, String userId, String productCallId){
		//JSONObject jObj = RestfulApiHelper.GetJsonFromReqBody(request);	
				
		//String productcall_id = productCallService.getProductCallId(userId);
					
		//String id = jObj.get("productcall_id");	
					
		ProductCall pc = productCallService.selectProductCallById(productCallId);
				  
		return new Gson().toJson(pc);  
	}
	*/
	
	@Override
	 public String processHttpRequest(HttpServletRequest request, HttpServletResponse response, String apiVersion, String apiId, String userId, Map<String, String> parametersMap){        		
		// TODO Auto-generated method stub 
		String retVal="";
		
		switch(apiId){	//case 0: POST to create product call
			case "0":
				retVal = insertNewProductCall(request,response, userId);
				break;	
			case "1":	//case 1: GET to get specific product call from passed product call ID in the URI
				retVal = selectProductCall(request,response, userId, parametersMap);
				break;
			case "2":	//case 2: DELETE to set specified product call to deleted 
				retVal = deleteProductCall(request,response, userId);
				break;
			case "3":	//case 3: PUT to update the specified product call record with passed data
				retVal = updateProductCall(request,response, userId);
				break;
			case "4":	//case 4: PATCH to set the specified product call record to 'Published' status
				retVal = publishProductCall(request,response, userId);
				break;
		//	case "5":	//case 5: Get the list of product calls that specified seller can see.
				//retVal = getSPCProductCalls(request,response, userId);
		//		break;	
		//	case "6":	//case 6: Get the list of buyer's product calls
		//		retVal = getBPCProductCalls(request,response, userId);
		//		break;	
		//	case "7":
		//		retVal = selectProductCallAll(request,response, userId);
		//		break;			
			case "8":	//Added by James Lee
				retVal = getProductCallsModeAndFilter(request,response, userId, parametersMap);
				break;				
		}	
	
		return retVal;
	}
	
	
	//Function to insert and create the product call record with the mandatory fields
	private String insertNewProductCall(HttpServletRequest request, HttpServletResponse response, String userId){
		Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);					
	
		
		ProductCall pc = new ProductCall();
		//pc.setProductcall_id(UUID.randomUUID());
		
		//pc.setProductcall_createdate(new Timestamp(System.currentTimeMillis()));
		pc.setProductcall_createdate(Calendar.getInstance().getTime());
		pc.setLastupdatedby(UUID.fromString(userId));	
		UUID org_id = apiUserService.getOrgId(userId);
		//UUID org_id =  apiUser.getOrg_id();//"487bdfc2-a255-4e9b-23c0-461bac59e64b";   //productCallService.getOrgId(userId);  
		pc.setOrg_id(org_id);		
		pc.setProductcall_status(MyConstants.BPCDRAFTSTATUS);	//Draft Status
		pc.setProductcall_title(MyConstants.BPCCREATIONTITLE);	//Untitled			
		
		pc.setProductcall_deleted(false);						//TODO: Added, confirm if it should be kept, or removed.
		
		ProductCall newIdPC = productCallService.insertProductCall(pc);
		
			
		return new Gson().toJson(newIdPC);  
	}
		
	//Function to select specified product call with passed product_id from the URI
	private String selectProductCall(HttpServletRequest request, HttpServletResponse response, String userId, Map<String, String> parametersMap){
		//Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);	
			
		//String productcall_id = productCallService.getProductCallId(userId);
				
		//String id = jObj.get("productcall_id");	
		
		String productCallId = parametersMap.get(MyConstants.URL_QUERY);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();
		//Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();	
			
		try {
			ProductCall pc = productCallService.selectProductCallById(UUID.fromString(productCallId));
			if(!pc.getProductcall_status().equals("Deleted")) {		
				return gson.toJson(pc);
				//return new Gson().toJson(pc);  
			}else {
				return gson.toJson("Row is deleted, unable to retrieve");
			//return new Gson().toJson("Row is deleted, unable to retrieve");
			}
		}catch(NoSuchElementException e) {
			return new Gson().toJson("productcall_id provided is incorrect or does not exist");  
		}
	}
	
	private String getProductCallsModeAndFilter(HttpServletRequest request, HttpServletResponse response, String userId, Map<String, String> parametersMap){
		//String productCallId = parametersMap.get(MyConstants.URL_QUERY);
		
		String mode = "";
		String filter ="";
		
		if(parametersMap.get("MODE") !=null)
			mode = parametersMap.get("MODE");
		
		if(parametersMap.get("FILTER") !=null)
			filter = parametersMap.get("FILTER");
		
		if(mode.toLowerCase().equals("buying") && filter.isEmpty()) {	//get list of buyer product calls
			UUID org_id = apiUserService.getOrgId(userId);
			//UUID org_id =  apiUser.getOrg_id();
			List<ProductCall> buyerProductCalls = productCallService.getAllBpcProductCalls(org_id.toString());
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder
					.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			Gson gson = gsonBuilder.create();
			//Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
			return gson.toJson(buyerProductCalls);
		}else if (mode.toLowerCase().equals("selling") && filter.isEmpty()) {
			List<ProductCall> sellerProductCalls = productCallService.getAllSpcProductCalls(userId);
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder
					.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			Gson gson = gsonBuilder.create();
			//Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
			return gson.toJson(sellerProductCalls);
		}else { //return ALL product Calls
			List<ProductCall> allProductCalls = productCallService.getAllProductCalls();
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder
					.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			Gson gson = gsonBuilder.create();
			//Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();	
			return gson.toJson(allProductCalls);
		}
		
		/*ProductCall pc = productCallService.selectProductCallById(UUID.fromString(productCallId));				  
		return new Gson().toJson(pc);  */
	}
	
	/*//Function to retrieve ALL product calls
	private String selectProductCallAll(HttpServletRequest request, HttpServletResponse response, String userId){
		
		List<ProductCall> allProductCalls = productCallService.getAllProductCalls();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();
		//Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();	
		return gson.toJson(allProductCalls);
	}*/

	/*//Function to select specified product call with passed product_id from the URI
	private String selectProductCallSingle(HttpServletRequest request, HttpServletResponse response, String userId, String productCallId){
		//JSONObject jObj = RestfulApiHelper.GetJsonFromReqBody(request);	
				
		//String productcall_id = productCallService.getProductCallId(userId);
					
		//String id = jObj.get("productcall_id");	
					
		ProductCall pc = productCallService.selectProductCallById(UUID.fromString(productCallId));
		if(!pc.getProductcall_status().equals("Deleted")) {			  
			return new Gson().toJson(pc);  
		}else {
			return new Gson().toJson("Row is deleted, unable to retrieve");
		}	
	}			*/
			
	//Function to set the specified product call to deleted
	private String deleteProductCall(HttpServletRequest request, HttpServletResponse response, String userId){
		Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);	
			
		//String productcall_id = productCallService.getProductCallId(userId);
		
				
		String id = jObj.get("productcall_id");	
		try {
			ProductCall pc = productCallService.selectProductCallById(UUID.fromString(id));					
			pc.setProductcall_id(UUID.fromString(id));
			pc.setProductcall_status(Constants.BPCDELETEDSTATUS);
			pc.setProductcall_deleted(true);
			pc.setLastupdatedby(UUID.fromString(userId));
			pc.setProductcall_updatedate(new Timestamp(System.currentTimeMillis()));
			
			//Set corresponding Allergen Xref table row to deleted true 
			//First check if record exists
			List<ProductcallXAllergen> pXaList = productcallXAllergenService.selectRows(UUID.fromString(id));
			
			//Second delete existing rows
			if(!pXaList.isEmpty())
				productcallXAllergenService.setRowsDeleted(UUID.fromString(id));
			
			//Set corresponding Cannabinoid Xref table row to deleted true 
			//First check if record exists
			List<ProductcallXCannabinoid> pXcList = productcallXCannabinoidService.selectRows(UUID.fromString(id));
			
			//Second delete existing rows
			if(!pXcList.isEmpty())
				productcallXCannabinoidService.setRowsDeleted(UUID.fromString(id));
			
			//Set corresponding Carrieroil Xref table row to deleted true 
			//First check if record exists
			List<ProductcallXCarrieroil> pXoList = productcallXCarrieroilService.selectRows(UUID.fromString(id));
			
			//Second delete existing rows
			if(!pXoList.isEmpty())
				productcallXCarrieroilService.setRowsDeleted(UUID.fromString(id));
			
			//Set corresponding Terpene Xref table row to deleted true 
			//First check if record exists
			List<ProductcallXTerpene> pXtList = productcallXTerpeneService.selectRows(UUID.fromString(id));
			
			//Second delete existing rows
			if(!pXtList.isEmpty())
				productcallXTerpeneService.setRowsDeleted(UUID.fromString(id));
			
			ProductCall pcDelete = productCallService.deleteProductCall(pc);
			
			//ArrayList<ProductCall> pcList= (ArrayList<ProductCall>)productCallService.getProductCall();			  
			return new Gson().toJson(pcDelete);  
		}catch(NoSuchElementException e) {
			return new Gson().toJson("productcall_id provided is incorrect or does not exist");  
		}	
	
		
	}
			
	//Function to update the specified product call with passed parameters
	private String updateProductCall(HttpServletRequest request, HttpServletResponse response, String userId){
		
		String jObj = RestfulApiHelper.GetStringFromReqBody(request);		
		
		Gson gson = new Gson();		
		ProductCall pcUpdated = gson.fromJson(jObj, ProductCall.class);
		//UUID pxtUpdated = gson.fromJson(jObj, ProductcallXTerpene.class).getProductcallterpene_id();
						
		
		UUID id = pcUpdated.getProductcall_id();	
		try {
			ProductCall pc = productCallService.selectProductCallById(id);					
			//List<String> productCallFields = new ArrayList<>(); TODO: implement lists for respective field types to iterate through and check null conditions prior to parsing
			
			if(!pc.getProductcall_status().equals("Deleted")) {		
			
			//	String terpene_id = jObj.get("terpene_id");		
			//	String productcall_terpeneminperc = jObj.get("productcall_terpeneminperc");		
			//	String productcall_terpenemaxperc = jObj.get("productcall_terpenemaxperc");		
			//	String cannabinoid_id = jObj.get("cannabinoid_id");		
			//	String productcall_cannabinoidminperc = jObj.get("productcall_cannabinoidminperc");		
			//	String productcall_cannabinoidmaxperc = jObj.get("productcall_cannabinoidmaxperc");		
			//	String foodallergen_id = jObj.get("foodallergen_id");		
				
				String lastupdatedby = userId; // jObj.get("lastupdatedby");		//TODO: Implement from DB tables				
				
				//**MANDATORY FIELDS
				if(pcUpdated.getProductcall_title() != null && !pcUpdated.getProductcall_title().isEmpty())
					pc.setProductcall_title(pcUpdated.getProductcall_title());			
				if(pcUpdated.getProductcall_openingdate() != null) 
					pc.setProductcall_openingdate(pcUpdated.getProductcall_openingdate());			
				if(pcUpdated.getProductcall_closingdate() != null)
					pc.setProductcall_closingdate(pcUpdated.getProductcall_closingdate());			
				if( pcUpdated.getProductcall_whocansubmitbids() != null)
					pc.setProductcall_whocansubmitbids(Integer.parseInt( pcUpdated.getProductcall_whocansubmitbids().toString()));			
				if(pcUpdated.getProductcall_bulk_retail() != null)
					pc.setProductcall_bulk_retail(pcUpdated.getProductcall_bulk_retail().toString().charAt(0));	//"B" => bulk  "R" => retail			
				if(pcUpdated.getProductcall_ingredientlistrequired() != null)
					pc.setProductcall_ingredientlistrequired(Boolean.parseBoolean(pcUpdated.getProductcall_ingredientlistrequired().toString()));			
				if(pcUpdated.getProductcall_requestsuggestedretailprice() != null)
					pc.setProductcall_requestsuggestedretailprice(Boolean.parseBoolean(pcUpdated.getProductcall_requestsuggestedretailprice().toString()));				
				if(pcUpdated.getProductcall_requestsuggestedwholesaleprice() != null)
					pc.setProductcall_requestsuggestedwholesaleprice(Boolean.parseBoolean(pcUpdated.getProductcall_requestsuggestedwholesaleprice().toString()));			
				if(pcUpdated.getProductcall_productphotorequired() != null)
					pc.setProductcall_productphotorequired(Boolean.parseBoolean(pcUpdated.getProductcall_productphotorequired().toString()));			
				if(pcUpdated.getProductcall_productpackagingphotorequired() != null)
					pc.setProductcall_productpackagingphotorequired(Boolean.parseBoolean(pcUpdated.getProductcall_productpackagingphotorequired().toString()));			
				if(pcUpdated.getProductcall_logoretailsalerequired() != null)
					pc.setProductcall_logoretailsalerequired(Boolean.parseBoolean(pcUpdated.getProductcall_logoretailsalerequired().toString()));			
				if(pcUpdated.getProductcall_productnumberrequired() != null)
					pc.setProductcall_productnumberrequired(Boolean.parseBoolean(pcUpdated.getProductcall_productnumberrequired().toString()));				
				if(pcUpdated.getCategoryId() != null)
					pc.setCategoryId(pcUpdated.getCategoryId());			
				if(pcUpdated.getSubcategoryId() != null)
					pc.setSubcategoryId(pcUpdated.getSubcategoryId());
				//pc.getCategory().setCategory_id(UUID.fromString(category_id));
				//pc.getSubcategory().setSubcategory_id(UUID.fromString(subcategory_id));			
				if(pcUpdated.getProductcall_medical_recreational() != null)
					pc.setProductcall_medical_recreational(pcUpdated.getProductcall_medical_recreational().toString().charAt(0));				
				if(pcUpdated.getProductcall_desiredquantity() != null)
					pc.setProductcall_desiredquantity(Double.parseDouble(pcUpdated.getProductcall_desiredquantity().toString()));			
				if(pcUpdated.getProductcall_providemaxqty() != null)
					pc.setProductcall_providemaxqty(Boolean.parseBoolean(pcUpdated.getProductcall_providemaxqty().toString()));			
				if(pcUpdated.getUom_id() != null)
					pc.setUom_id(pcUpdated.getUom_id());			
				if(pcUpdated.getProductcall_nopreferredskusize() != null)
					pc.setProductcall_nopreferredskusize(Boolean.parseBoolean(pcUpdated.getProductcall_nopreferredskusize().toString()));				
				if(pcUpdated.getProductcall_specifyskusize() != null && !pcUpdated.getProductcall_specifyskusize().isEmpty())
					pc.setProductcall_specifyskusize(pcUpdated.getProductcall_specifyskusize());			
				if(pcUpdated.getProductcall_pickup() != null)
					pc.setProductcall_pickup(pcUpdated.getProductcall_pickup().toString().charAt(0));			
				if(pcUpdated.getProductcall_delivery_pickup_date() != null)
					pc.setProductcall_delivery_pickup_date(pcUpdated.getProductcall_delivery_pickup_date());			
				if(pcUpdated.getFacility_id() != null)
					pc.setFacility_id(pcUpdated.getFacility_id());  //TODO: Implement Retrieval of Facility ID from Tables			
				if(pcUpdated.getProductcall_repeatingorder() != null)
					pc.setProductcall_repeatingorder(Boolean.parseBoolean(pcUpdated.getProductcall_repeatingorder().toString()));			
				if(pcUpdated.getProductcall_endrepeatdate() != null)
					pc.setProductcall_endrepeatdate(pcUpdated.getProductcall_endrepeatdate());			
				if(pcUpdated.getProductcall_repeatfrequency() != null)
					pc.setProductcall_repeatfrequency(pcUpdated.getProductcall_repeatfrequency().toString().charAt(0)); //M => Monthly, W=> Weekly			
				if(pcUpdated.getProductcall_additionalinfo() != null && !pcUpdated.getProductcall_additionalinfo().isEmpty())
					pc.setProductcall_additionalinfo(pcUpdated.getProductcall_additionalinfo());				
				
				pc.setProductcall_status(MyConstants.BPCDRAFTSTATUS);
				pc.setLastupdatedby(UUID.fromString(lastupdatedby));
				pc.setProductcall_deleted(false);
				//pc.setProductcall_createdate(new Timestamp(System.currentTimeMillis()));				
				
				//Optional Fields
				if(pcUpdated.getGrowingmethod_id() != null)
					pc.setGrowingmethod_id(pcUpdated.getGrowingmethod_id());			
				if(pcUpdated.getDryingmethod_id() != null)
					pc.setDryingmethod_id(pcUpdated.getDryingmethod_id());			
				if(pcUpdated.getTrimmingmethod_id() != null)
					pc.setTrimmingmethod_id(pcUpdated.getTrimmingmethod_id());			
				if(pcUpdated.getProductcall_targetminthcperc() != null)
					pc.setProductcall_targetminthcperc(Double.parseDouble(pcUpdated.getProductcall_targetminthcperc().toString()));			
				if(pcUpdated.getProductcall_targetmaxthcperc() != null)
					pc.setProductcall_targetmaxthcperc(Double.parseDouble(pcUpdated.getProductcall_targetmaxthcperc().toString()));			
				if(pcUpdated.getProductcall_organicgrowing() != null)
					pc.setProductcall_organicgrowing(Boolean.parseBoolean(pcUpdated.getProductcall_organicgrowing().toString()));			
				if(pcUpdated.getProductcall_targetmincbdperc() != null)
					pc.setProductcall_targetmincbdperc(Double.parseDouble(pcUpdated.getProductcall_targetmincbdperc().toString()));			
				if(pcUpdated.getProductcall_targetmaxcbdperc() != null)
					pc.setProductcall_targetmaxcbdperc(Double.parseDouble(pcUpdated.getProductcall_targetmaxcbdperc().toString()));			
				if(pcUpdated.getProductcall_productlifeexpiry() != null)
					pc.setProductcall_productlifeexpiry(pcUpdated.getProductcall_productlifeexpiry());			
				if(pcUpdated.getProvincestate_id() != null)
					pc.setProvincestate_id(pcUpdated.getProvincestate_id());	//TODO: implement from DB			
				if(pcUpdated.getProductcall_mustmanufacture() != null)
					pc.setProductcall_mustmanufacture(Boolean.parseBoolean(pcUpdated.getProductcall_mustmanufacture().toString())); // 0=> false 1=>true			
				if(pcUpdated.getProducttier_id() != null)
					pc.setProducttier_id(pcUpdated.getProducttier_id());	
				
				//pc.setTerpene_id(UUID.fromString(terpene_id));
			//	pc.setProductcall_terpeneminperc(Double.parseDouble(productcall_terpeneminperc));
			//	pc.setProductcall_terpenemaxperc(Double.parseDouble(productcall_terpenemaxperc));
				//pc.setCannabinoid_id(UUID.fromString(cannabinoid_id));
			//	pc.setProductcall_cannabinoidminperc(Double.parseDouble(productcall_cannabinoidminperc));
			//	pc.setProductcall_cannabinoidmaxperc(Double.parseDouble(productcall_cannabinoidmaxperc));
			//	pc.setFoodallergen_id(UUID.fromString(foodallergen_id));		//TODO: implement from DB
				
				if(pcUpdated.getProductcall_qadoclabtests() != null)
					pc.setProductcall_qadoclabtests(Boolean.parseBoolean(pcUpdated.getProductcall_qadoclabtests().toString())); // 0=> false 1=>true			
				if(pcUpdated.getProductcall_qadoccoa() != null)
					pc.setProductcall_qadoccoa(Boolean.parseBoolean(pcUpdated.getProductcall_qadoccoa().toString()));	// 0=> false 1=>true			
				if(pcUpdated.getProductcall_qadoccom() != null)
					pc.setProductcall_qadoccom(Boolean.parseBoolean(pcUpdated.getProductcall_qadoccom().toString()));	// 0=> false 1=>true			
				if(pcUpdated.getProductcall_qadocproofofirradiation() != null)
					pc.setProductcall_qadocproofofirradiation(Boolean.parseBoolean(pcUpdated.getProductcall_qadocproofofirradiation().toString()));	// 0=> false 1=>true
				if(pcUpdated.getProductcall_colourdescription() != null && !pcUpdated.getProductcall_colourdescription().isEmpty())
					pc.setProductcall_colourdescription(pcUpdated.getProductcall_colourdescription());			
				if(pcUpdated.getProductcall_masterpackinfoprovider() != null && !pcUpdated.getProductcall_masterpackinfoprovider().isEmpty())
					pc.setProductcall_masterpackinfoprovider(pcUpdated.getProductcall_masterpackinfoprovider());			
				if(pcUpdated.getProductcall_innerpackinfoprovider() != null && !pcUpdated.getProductcall_innerpackinfoprovider().isEmpty())
					pc.setProductcall_innerpackinfoprovider(pcUpdated.getProductcall_innerpackinfoprovider());			
				if(pcUpdated.getProductcall_productpackinfoprovider() != null && !pcUpdated.getProductcall_productpackinfoprovider().isEmpty())
					pc.setProductcall_productpackinfoprovider(pcUpdated.getProductcall_productpackinfoprovider());			
				if(pcUpdated.getProductcall_retailiteminfoprovider() != null && !pcUpdated.getProductcall_retailiteminfoprovider().isEmpty())
					pc.setProductcall_retailiteminfoprovider(pcUpdated.getProductcall_retailiteminfoprovider());			
				if(pcUpdated.getProductcall_palletinfoprovider() != null && !pcUpdated.getProductcall_palletinfoprovider().isEmpty())
					pc.setProductcall_palletinfoprovider(pcUpdated.getProductcall_palletinfoprovider());
				
				pc.setProductcall_status(Constants.BPCDRAFTSTATUS);
				pc.setProductcall_updatedate(new Timestamp(System.currentTimeMillis()));
				
				if(lastupdatedby != null && !lastupdatedby.isEmpty())
					pc.setLastupdatedby(UUID.fromString(lastupdatedby));					
				if(pcUpdated.getExtractionprocess_id() != null)
					pc.setExtractionprocess_id(pcUpdated.getExtractionprocess_id());
				
				if(pcUpdated.getProductcallXTerpene() != null) {	//Retrieve and insert into corresponding productcallxterpene table
					
					//First check if record exists
					List<ProductcallXTerpene> pXtList = productcallXTerpeneService.selectRows(id);
					
					//Second delete existing rows
					if(!pXtList.isEmpty())
						productcallXTerpeneService.deleteRowsBatch(pXtList);
					
					Iterator<ProductcallXTerpene> it = pcUpdated.getProductcallXTerpene().iterator();
					while(it.hasNext()) {
						ProductcallXTerpene pxT = it.next();
						UUID terpeneID = pxT.getTerpene_id();
						Double productcallterpene_minperc = pxT.getProductcallterpene_minperc();
						Double productcallterpene_maxperc = pxT.getProductcallterpene_maxperc();
						
						ProductcallXTerpene pxTInsert = new ProductcallXTerpene();
						pxTInsert.setProductcall_id(pcUpdated.getProductcall_id());
						pxTInsert.setTerpene_id(terpeneID);
						pxTInsert.setProductcallterpene_minperc(productcallterpene_minperc);
						pxTInsert.setProductcallterpene_maxperc(productcallterpene_maxperc);
						pxTInsert.setProductcallterpene_deleted(false);
						pxTInsert.setLastupdatedby(UUID.fromString(userId));
						pxTInsert.setProductcallterpene_createdate(new Timestamp(System.currentTimeMillis()));
						pxTInsert.setProductcallterpene_updatedate(new Timestamp(System.currentTimeMillis()));
						
						productcallXTerpeneService.insertProductcallTerpene(pxTInsert);
					}
				}
				
				if(pcUpdated.getProductcallXCannabinoid() != null) {	//Retrieve and insert into corresponding productcallxcannabinoid table
					
					//First check if record exists
					List<ProductcallXCannabinoid> pXcList = productcallXCannabinoidService.selectRows(id);
					
					//Second delete existing rows
					if(!pXcList.isEmpty())
						productcallXCannabinoidService.deleteRowsBatch(pXcList);
					
					//Then update with new rows.
					Iterator<ProductcallXCannabinoid> it = pcUpdated.getProductcallXCannabinoid().iterator();
					while(it.hasNext()) {
						ProductcallXCannabinoid pxC = it.next();
						UUID cannabinoidID = pxC.getCannabinoid_id();
						Double productcallcannabinoid_minperc = pxC.getProductcallcannabinoid_minperc();
						Double productcallcannabinoid_maxperc = pxC.getProductcallcannabinoid_maxperc();
						
						ProductcallXCannabinoid pxCInsert = new ProductcallXCannabinoid();
						pxCInsert.setProductcall_id(pcUpdated.getProductcall_id());
						pxCInsert.setCannabinoid_id(cannabinoidID);
						pxCInsert.setProductcallcannabinoid_minperc(productcallcannabinoid_minperc);
						pxCInsert.setProductcallcannabinoid_maxperc(productcallcannabinoid_maxperc);
						pxCInsert.setProductcallcannabinoid_deleted(false);
						pxCInsert.setLastupdatedby(UUID.fromString(userId));
						pxCInsert.setProductcallcannabinoid_createdate(new Timestamp(System.currentTimeMillis()));
						pxCInsert.setProductcallcannabinoid_updatedate(new Timestamp(System.currentTimeMillis()));
						
						productcallXCannabinoidService.insertProductcallCannabinoid(pxCInsert);
					}
				}
				
				if(pcUpdated.getProductcallXAllergen() != null) {	//Retrieve and insert into corresponding productcallxcannabinoid table
					
					//First check if record exists
					List<ProductcallXAllergen> pXaList = productcallXAllergenService.selectRows(id);
					
					//Second delete existing rows
					if(!pXaList.isEmpty())
						productcallXAllergenService.deleteRowsBatch(pXaList);
					
					Iterator<ProductcallXAllergen> it = pcUpdated.getProductcallXAllergen().iterator();
					while(it.hasNext()) {
						ProductcallXAllergen pxA = it.next();
						UUID allergenID = pxA.getAllergen_id();					
						
						ProductcallXAllergen pxAInsert = new ProductcallXAllergen();
						pxAInsert.setProductcall_id(pcUpdated.getProductcall_id());
						pxAInsert.setAllergen_id(allergenID);					
						pxAInsert.setProductcallallergen_deleted(false);
						pxAInsert.setLastupdatedby(UUID.fromString(userId));
						pxAInsert.setProductcallallergen_createdate(new Timestamp(System.currentTimeMillis()));
						pxAInsert.setProductcallallergen_updatedate(new Timestamp(System.currentTimeMillis()));
						
						productcallXAllergenService.insertProductcallAllergen(pxAInsert);
					}
				}
				
				if(pcUpdated.getProductcallXCarrieroil() != null) {	//Retrieve and insert into corresponding productcallxcarrieroil table
					
					//First check if record exists
					List<ProductcallXCarrieroil> pXoList = productcallXCarrieroilService.selectRows(id);
					
					//Second delete existing rows
					if(!pXoList.isEmpty())
						productcallXCarrieroilService.deleteRowsBatch(pXoList);
					
					Iterator<ProductcallXCarrieroil> it = pcUpdated.getProductcallXCarrieroil().iterator();
					while(it.hasNext()) {
						ProductcallXCarrieroil pxC = it.next();
						UUID carrieroil_id = pxC.getCarrieroil_id();					
						
						ProductcallXCarrieroil pxOInsert = new ProductcallXCarrieroil();
						pxOInsert.setProductcall_id(pcUpdated.getProductcall_id());
						pxOInsert.setCarrieroil_id(carrieroil_id);					
						pxOInsert.setProductcarrieroil_deleted(false);
						pxOInsert.setLastupdatedby(UUID.fromString(userId));
						pxOInsert.setProductcarrieroil_createdate(new Timestamp(System.currentTimeMillis()));
						pxOInsert.setProductcarrieroil_updatedate(new Timestamp(System.currentTimeMillis()));
						
						productcallXCarrieroilService.insertProductcallCarrieroil(pxOInsert);				
					}
				}
							
				ProductCall updatedRow = productCallService.updateProductCall(pc);
				
				//ProductCall pcUpdated = productCallService.selectProductCallById(id);
						
				//ArrayList<ProductCall> pcList= (ArrayList<ProductCall>)productCallService.getProductCall();			  
				return new Gson().toJson(updatedRow);  
			}else {
				return new Gson().toJson("Row is deleted, unable to update");
			}
		}catch(NoSuchElementException e) {
			return new Gson().toJson("No such element please check the passed id");  
		}
	}
		
	private String publishProductCall(HttpServletRequest request, HttpServletResponse response, String userId){
			
		Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);			
			
		String id = jObj.get("productcall_id");		
		
		try {
			ProductCall pc = productCallService.selectProductCallById(UUID.fromString(id));	
			
			if(!pc.getProductcall_status().equals("Deleted")) {		
				//return gson.toJson(pc);
				//pc.setProductcall_id(UUID.fromString(id));
				pc.setProductcall_status(Constants.BPCPUBLISHEDSTATUS);
				pc.setLastupdatedby(UUID.fromString(userId));
				pc.setProductcall_updatedate(new Timestamp(System.currentTimeMillis()));
				
				ProductCall publishRows = productCallService.publishProductCall(pc);
				return new Gson().toJson(publishRows);  
			}else {
				//return gson.toJson("Row is deleted, unable to retrieve");
				return new Gson().toJson("Product Call is deleted, unable to publish.");
			}
			
			//return new Gson().toJson(publishRows);  
		}catch(NoSuchElementException e) {
			return new Gson().toJson("productcall_id provided is incorrect or does not exist");  
		}
	}
		
/*	//Current Assumption that front-end is passing us the id (To be changed to uuid)
	private String getSPCProductCalls(HttpServletRequest request, HttpServletResponse response, String userId){
		//UUID org_id = apiUserService.getOrgId(userId);
		//UUID org_id =  apiUser.getOrg_id();
		List<ProductCall> sellerProductCalls = productCallService.getAllSpcProductCalls(userId);
		
	////	return new Gson().toJson(sellerProductCalls.get(0)[1]);
		return null;
		//return new Gson().toJson(sellerProductCalls);  
	}*/
		
	/*//Function to get the List of all the Buyer's Product Calls
	private String getBPCProductCalls(HttpServletRequest request, HttpServletResponse response, String userId){
				  
		//ArrayList<BpcReturn> buyerProductCalls = (ArrayList<BpcReturn>) productCallService.getAllBpcProductCalls(userId);
		UUID org_id = apiUserService.getOrgId(userId);
		//UUID org_id =  apiUser.getOrg_id();
		List<ProductCall> buyerProductCalls = productCallService.getAllBpcProductCalls(org_id.toString());
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(buyerProductCalls);
		
		//return new Gson().toJson(buyerProductCalls);  			
			
	}*/
		
	/*private String getProductCallConstants(HttpServletRequest request, HttpServletResponse response){
		//JSONObject jObj = RestfulApiHelper.GetJsonFromReqBody(request);					  
		return new Gson().toJson(Constants.CATEGORIES) + new Gson().toJson(Constants.SUBCATEGORIES);  
	}*/
		
		
	@Override
	public List<ApiInfo> getApiList() {
		ArrayList<ApiInfo> myList = new ArrayList<ApiInfo>(); 
		myList.add(new ApiInfo("NewProductCall","To insert a brand new ProductCall record into db","/v*/ProductCall","POST","ProductCallPageImpl"));
		return myList;
	}

}