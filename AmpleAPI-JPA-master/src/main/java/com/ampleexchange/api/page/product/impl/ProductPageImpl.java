package com.ampleexchange.api.page.product.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.model.ApiInfo;
import com.ampleexchange.api.page.product.ProductInfo;
import com.ampleexchange.api.page.product.ProductPage;
import com.ampleexchange.api.page.product.dbservice.*;
import com.ampleexchange.api.page.product.dbservice.impl.ProductDBServiceImpl;
import com.ampleexchange.api.page.product.model.*;
import com.ampleexchange.api.util.HibernateProxyTypeAdapter;
import com.ampleexchange.api.util.RestfulApiHelper;

import com.ampleexchange.api.page.productcall.model.ApiUser;
import com.ampleexchange.api.page.productcall.model.ProductCall;
import com.ampleexchange.api.page.productcall.model.ProductcallXTerpene;
import com.ampleexchange.api.page.productcall.dbservice.impl.ApiUserDBServiceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service
public class ProductPageImpl implements ProductPage {

	@Autowired
	private ApiUserDBServiceImpl apiUserService;

	@Autowired
	private ProductDBServiceImpl productService;

	@Override
	public String processHttpRequest(HttpServletRequest request, HttpServletResponse response, String apiVersion,
			String apiId, String userId, Map<String, String> parametersMap) {
		// TODO Auto-generated method stub
		String retVal = "", cId = "";

		switch (apiId) {
		case "0": // create new Product
			retVal = createProduct(request, response, userId);
			break;
		case "1": // update Product information & misc
			retVal = updateProduct(request, response, userId);
			break;
		case "2": // update Product status to delete
			retVal = deleteProduct(request, response, userId);
			break;
		case "3": // get Strain names info
			retVal = retrieveProductStrain(request, response, userId);
			break;
		case "4": // get facility name info
			retVal = retrieveFacility(request, response, userId);
			break;
		case "5": // get product for editing
			retVal = editProduct(request, response, userId, parametersMap);
			break;
		case "6": // get product available for sale
			retVal = saleProducts(request, response, userId);
			break;
		case "7": // get products status is soft deleted / not available for sale
			retVal = removedProducts(request, response, userId);
			break;
		case "8": // get products that will be available in future / at a future date
			retVal = futureProducts(request, response, userId);
			break;
		case "9": // create new brand
			retVal = createBrand(request, response, userId);
			break;
		case "10": // update brand info
			retVal = updateBrand(request, response, userId);
			break;
		case "11": // create new brandfamily
			retVal = createBrandFamily(request, response, userId);
			break;
		case "12": // update brandfamily info
			retVal = updateBrandFamily(request, response, userId);
			break;
		case "13": // update brandfamily info
			retVal = getBrands(request, response, userId);
			break;
		case "14": // update brandfamily info
			retVal = getBrandFamily(request, response, userId);
			break;

		}
		return retVal;
	}

	private String createProduct(HttpServletRequest request, HttpServletResponse response, String userId) {

		// Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);

		Product prodTemp = new Product();
		prodTemp.setProduct_createdate(new Timestamp(System.currentTimeMillis()));
		prodTemp.setLastupdatedby(UUID.fromString(userId));

		UUID org_id = apiUserService.getOrgId(userId);
		prodTemp.setOrg_id(org_id);

		prodTemp.setProduct_deleted(false);
		prodTemp.setProduct_layersperpallet(0d);
		prodTemp.setCategory_id(UUID.fromString("80f8f55d-2194-4326-abf1-d95312d443a8"));
		prodTemp.setProduct_longname(" ");

		Product product = productService.createProduct(prodTemp);

		return new Gson().toJson(product);
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response, String userId) {

		Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);

		/*
		 * Product prodTemp = new Product();
		 * prodTemp.setProduct_id(UUID.fromString(jObj.get("product_id")));
		 * 
		 * prodTemp.setProduct_longname(jObj.get("product_longname"));
		 * prodTemp.setCategory_id(UUID.fromString(jObj.get("category_id")));
		 * prodTemp.setProduct_createdate(Timestamp.valueOf(jObj.get(
		 * "product_createdate")));
		 * prodTemp.setProduct_layersperpallet(Double.parseDouble(jObj.get(
		 * "product_layersperpallet")));
		 * 
		 * prodTemp.setProduct_updatedate(new Date(System.currentTimeMillis()));
		 * prodTemp.setLastupdatedby(UUID.fromString(userId));
		 * prodTemp.setProduct_deleted(true);
		 */

		Product product = productService.deleteProduct(jObj.get("product_id"));

		return new Gson().toJson(product);
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response, String userId) {

		// Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);

		String jObj = RestfulApiHelper.GetStringFromReqBody(request);

		Gson gson = new Gson();
		Product prodInfo = gson.fromJson(jObj, Product.class);

		Product prodData = productService.findProduct(prodInfo.getProduct_id());

		prodData.setProduct_updatedate(new Date(System.currentTimeMillis()));
		prodData.setLastupdatedby(UUID.fromString(userId));

		UUID org_id = apiUserService.getOrgId(userId);
		prodData.setOrg_id(org_id);

		if (prodInfo.getProduct_shortname() != null)
			prodData.setProduct_shortname(prodInfo.getProduct_shortname());

		// data is sent as LOT Number, please fetch LOT_ID from "lot" table
		// respectively.

//		if (prodInfo.getLot_id() != null)
		// prodData.setLot_id(getLotInfo(prodInfo.getLot_id()));
		/*
		 * if (prodInfo.getLot_id() != null) prodData.setLot_id(prodInfo.getLot_id());
		 * 
		 * if (prodInfo.getOrg_id() != null) prodData.setOrg_id(prodInfo.getOrg_id());
		 * 
		 * if (prodInfo.getCategory_id() != null)
		 * prodData.setCategory_id(prodInfo.getCategory_id());
		 * 
		 * if (prodInfo.getSubcategory_id() != null)
		 * prodData.setSubcategory_id(prodInfo.getSubcategory_id());
		 * 
		 * if (prodInfo.getGrowingmethod_id() != null)
		 * prodData.setGrowingmethod_id(prodInfo.getGrowingmethod_id());
		 * 
		 * if (prodInfo.getDryingmethod_id() != null)
		 * prodData.setDryingmethod_id(prodInfo.getDryingmethod_id());
		 * 
		 * if (prodInfo.getTrimmingmethod_id() != null)
		 * prodData.setTrimmingmethod_id(prodInfo.getTrimmingmethod_id());
		 * 
		 * if (prodInfo.getProducttier_id() != null)
		 * prodData.setProducttier_id(prodInfo.getProducttier_id());
		 * 
		 * if (prodInfo.getStrain_id() != null)
		 * prodData.setStrain_id(prodInfo.getStrain_id());
		 * 
		 * if (prodInfo.getManufacturingfacility_id() != null)
		 * prodData.setManufacturingfacility_id(prodInfo.getManufacturingfacility_id());
		 * 
		 * if (prodInfo.getViewproduct() != null)
		 * prodData.setViewproduct(prodInfo.getViewproduct());
		 * 
		 * if (prodInfo.getBrand_id() != null)
		 * prodData.setBrand_id(prodInfo.getBrand_id());
		 * 
		 * if (prodInfo.getProduct_organicallygrown() != null)
		 * prodData.setProduct_organicallygrown(prodInfo.getProduct_organicallygrown());
		 * 
		 * if (prodInfo.getProduct_shortdescription() != null)
		 * prodData.setProduct_shortdescription(prodInfo.getProduct_shortdescription());
		 * 
		 * if (prodInfo.getProduct_longdescription() != null)
		 * prodData.setProduct_longdescription(prodInfo.getProduct_longdescription());
		 * 
		 * if (prodInfo.getProduct_bulkorretail() != null)
		 * prodData.setProduct_bulkorretail(prodInfo.getProduct_bulkorretail());
		 * 
		 * if (prodInfo.getProduct_availableforsale() != null)
		 * prodData.setProduct_availableforsale(prodInfo.getProduct_availableforsale());
		 * 
		 * if (prodInfo.getProduct_sourceproductid() != null)
		 * prodData.setProduct_sourceproductid(prodInfo.getProduct_sourceproductid());
		 * 
		 * if (prodInfo.getProduct_manufacturethisproduct() != null)
		 * prodData.setProduct_manufacturethisproduct(prodInfo.
		 * getProduct_manufacturethisproduct());
		 * 
		 * if (prodInfo.getProduct_buyfromexchange() != null)
		 * prodData.setProduct_buyfromexchange(prodInfo.getProduct_buyfromexchange());
		 * 
		 * if (prodInfo.getProduct_vendor() != null)
		 * prodData.setProduct_vendor(prodInfo.getProduct_vendor());
		 * 
		 * if (prodInfo.getProduct_manufacturingfacility() != null)
		 * prodData.setProduct_manufacturingfacility(prodInfo.
		 * getProduct_manufacturingfacility());
		 * 
		 * if (prodInfo.getProduct_vendorlotnumber() != null)
		 * prodData.setProduct_vendorlotnumber(prodInfo.getProduct_vendorlotnumber());
		 * 
		 * // Product created date -- dont update, product delete status flag dont
		 * update
		 * 
		 * if (prodInfo.getProduct_updatedate() != null)
		 * prodData.setProduct_updatedate(prodInfo.getProduct_updatedate());
		 * 
		 * if (prodInfo.getLastupdatedby() != null)
		 * prodData.setLastupdatedby(prodInfo.getLastupdatedby());
		 * 
		 * if (prodInfo.getProduct_targetminthcperc() != null)
		 * prodData.setProduct_targetminthcperc(prodInfo.getProduct_targetminthcperc());
		 * 
		 * if (prodInfo.getProduct_targetmaxthcperc() != null)
		 * prodData.setProduct_targetmaxthcperc(prodInfo.getProduct_targetmaxthcperc());
		 * 
		 * if (prodInfo.getProduct_actualthc() != null)
		 * prodData.setProduct_actualthc(prodInfo.getProduct_actualthc());
		 * 
		 * if (prodInfo.getProduct_targetmincbdperc() != null)
		 * prodData.setProduct_targetmincbdperc(prodInfo.getProduct_targetmincbdperc());
		 * 
		 * if (prodInfo.getProduct_targetmaxcbdperc() != null)
		 * prodData.setProduct_targetmaxcbdperc(prodInfo.getProduct_targetmaxcbdperc());
		 * 
		 * if (prodInfo.getProduct_actualcbd() != null)
		 * prodData.setProduct_actualcbd(prodInfo.getProduct_actualcbd());
		 */
		/*
		 * if (prodInfo.getTerpene_id() != null)
		 * prodData.setTerpene_id(prodInfo.getTerpene_id());
		 * 
		 * if (prodInfo.getProduct_terpeneminperc() != null)
		 * prodData.setProduct_terpeneminperc(prodInfo.getProduct_terpeneminperc());
		 * 
		 * if (prodInfo.getProduct_terpenemaxperc() != null)
		 * prodData.setProduct_terpenemaxperc(prodInfo.getProduct_terpenemaxperc());
		 * 
		 * if (prodInfo.getProduct_actualterpeneperc() != null)
		 * prodData.setProduct_actualterpeneperc(prodInfo.getProduct_actualterpeneperc()
		 * );
		 */

		/*
		 * if (prodInfo.getCannabinoid_id() != null)
		 * prodData.setCannabinoid_id(prodInfo.getCannabinoid_id());
		 * 
		 * if (prodInfo.getProduct_cannabinoidminperc() != null)
		 * prodData.setProduct_cannabinoidminperc(prodInfo.getProduct_cannabinoidminperc
		 * ());
		 * 
		 * if (prodInfo.getProduct_cannabinoidmaxperc() != null)
		 * prodData.setProduct_cannabinoidmaxperc(prodInfo.getProduct_cannabinoidmaxperc
		 * ());
		 * 
		 * if (prodInfo.getProduct_actualcannabinoidperc() != null)
		 * prodData.setProduct_actualcannabinoidperc(prodInfo.
		 * getProduct_actualcannabinoidperc());
		 * 
		 * if (prodInfo.getProduct_actualcannabinoidperc() != null)
		 * prodData.setProduct_actualcannabinoidperc(prodInfo.
		 * getProduct_actualcannabinoidperc());
		 * 
		 * if (prodInfo.getProduct_doesthisproductexpire() != null)
		 * prodData.setProduct_doesthisproductexpire(prodInfo.
		 * getProduct_doesthisproductexpire());
		 */
		/*
		 * if (prodInfo.getProduct_expirydate() != null)
		 * prodData.setProduct_expirydate(prodInfo.getProduct_expirydate());
		 * 
		 * if (prodInfo.getProduct_driedcannabisequiv() != null)
		 * prodData.setProduct_driedcannabisequiv(prodInfo.getProduct_driedcannabisequiv
		 * ());
		 * 
		 * if (prodInfo.getProduct_driedcannabisequivuomid() != null)
		 * prodData.setProduct_driedcannabisequivuomid(prodInfo.
		 * getProduct_driedcannabisequivuomid());
		 * 
		 * if (prodInfo.getProduct_skusize() != null)
		 * prodData.setProduct_skusize(prodInfo.getProduct_skusize());
		 * 
		 * if (prodInfo.getProduct_packheight() != null)
		 * prodData.setProduct_packheight(prodInfo.getProduct_packheight());
		 * 
		 * if (prodInfo.getProduct_packheightuomid() != null)
		 * prodData.setProduct_packheightuomid(prodInfo.getProduct_packheightuomid());
		 * 
		 * if (prodInfo.getProduct_packlength() != null)
		 * prodData.setProduct_packlength(prodInfo.getProduct_packlength());
		 * 
		 * if (prodInfo.getProduct_packlengthuomid() != null)
		 * prodData.setProduct_packlengthuomid(prodInfo.getProduct_packlengthuomid());
		 * 
		 * if (prodInfo.getProduct_packwidth() != null)
		 * prodData.setProduct_packwidth(prodInfo.getProduct_packwidth());
		 * 
		 * if (prodInfo.getProduct_packwidthuomid() != null)
		 * prodData.setProduct_packwidthuomid(prodInfo.getProduct_packwidthuomid());
		 * 
		 * if (prodInfo.getProduct_packproductnumber() != null)
		 * prodData.setProduct_packproductnumber(prodInfo.getProduct_packproductnumber()
		 * );
		 * 
		 * if (prodInfo.getProduct_netcontentweight() != null)
		 * prodData.setProduct_netcontentweight(prodInfo.getProduct_netcontentweight());
		 * 
		 * if (prodInfo.getProduct_netcontentweightuomid() != null)
		 * prodData.setProduct_netcontentweightuomid(prodInfo.
		 * getProduct_netcontentweightuomid());
		 * 
		 * if (prodInfo.getProduct_retailpackagingweight() != null)
		 * prodData.setProduct_retailpackagingweight(prodInfo.
		 * getProduct_retailpackagingweight());
		 * 
		 * if (prodInfo.getProduct_retailpackagingmaterial() != null)
		 * prodData.setProduct_retailpackagingmaterial(prodInfo.
		 * getProduct_retailpackagingmaterial());
		 * 
		 * if (prodInfo.getProduct_areretailunitsserialized() != null)
		 * prodData.setProduct_areretailunitsserialized(prodInfo.
		 * getProduct_areretailunitsserialized());
		 * 
		 * if (prodInfo.getProduct_masterpackquantity() != null)
		 * prodData.setProduct_masterpackquantity(prodInfo.getProduct_masterpackquantity
		 * ());
		 * 
		 * if (prodInfo.getProduct_masterpackheight() != null)
		 * prodData.setProduct_masterpackheight(prodInfo.getProduct_masterpackheight());
		 * 
		 * if (prodInfo.getProduct_masterpacklength() != null)
		 * prodData.setProduct_masterpacklength(prodInfo.getProduct_masterpacklength());
		 * 
		 * if (prodInfo.getProduct_masterpackwidth() != null)
		 * prodData.setProduct_masterpackwidth(prodInfo.getProduct_masterpackwidth());
		 * 
		 * if (prodInfo.getProduct_masterpackweight() != null)
		 * prodData.setProduct_masterpackweight(prodInfo.getProduct_masterpackweight());
		 * 
		 * if (prodInfo.getProduct_masterpackweightuomid() != null)
		 * prodData.setProduct_masterpackweightuomid(prodInfo.
		 * getProduct_masterpackweightuomid());
		 * 
		 * if (prodInfo.getProduct_masterpackproductnumber() != null)
		 * prodData.setProduct_masterpackproductnumber(prodInfo.
		 * getProduct_masterpackproductnumber());
		 * 
		 * if (prodInfo.getProduct_innerpackquantity() != null)
		 * prodData.setProduct_innerpackquantity(prodInfo.getProduct_innerpackquantity()
		 * );
		 * 
		 * if (prodInfo.getProduct_innerpackheight() != null)
		 * prodData.setProduct_innerpackheight(prodInfo.getProduct_innerpackheight());
		 * 
		 * if (prodInfo.getProduct_innerpacklength() != null)
		 * prodData.setProduct_innerpacklength(prodInfo.getProduct_innerpacklength());
		 * 
		 * if (prodInfo.getProduct_innerpackwidth() != null)
		 * prodData.setProduct_innerpackwidth(prodInfo.getProduct_innerpackwidth());
		 * 
		 * if (prodInfo.getProduct_innerpackweight() != null)
		 * prodData.setProduct_innerpackweight(prodInfo.getProduct_innerpackweight());
		 * 
		 * if (prodInfo.getProduct_innerpackproductnumber() != null)
		 * prodData.setProduct_innerpackproductnumber(prodInfo.
		 * getProduct_innerpackproductnumber());
		 * 
		 * if (prodInfo.getProduct_layersperpallet() != null)
		 * prodData.setProduct_layersperpallet(prodInfo.getProduct_layersperpallet());
		 * 
		 * if (prodInfo.getProduct_casesperlayeronapallet() != null)
		 * prodData.setProduct_casesperlayeronapallet(prodInfo.
		 * getProduct_casesperlayeronapallet());
		 * 
		 * if (prodInfo.getProduct_itemsretailpack() != null)
		 * prodData.setProduct_itemsretailpack(prodInfo.getProduct_itemsretailpack());
		 * 
		 * if (prodInfo.getProduct_itemsretailpackuomid() != null)
		 * prodData.setProduct_itemsretailpackuomid(prodInfo.
		 * getProduct_itemsretailpackuomid());
		 * 
		 * if (prodInfo.getProduct_totalproductpackweightuomid() != null)
		 * prodData.setProduct_totalproductpackweightuomid(prodInfo.
		 * getProduct_totalproductpackweightuomid());
		 * 
		 * if (prodInfo.getProduct_skusizeuomid() != null)
		 * prodData.setProduct_skusizeuomid(prodInfo.getProduct_skusizeuomid());
		 * 
		 * if (prodInfo.getProduct_minimumorderqty() != null)
		 * prodData.setProduct_minimumorderqty(prodInfo.getProduct_minimumorderqty());
		 * 
		 * if (prodInfo.getProduct_minimumorderqtyuomid() != null)
		 * prodData.setProduct_minimumorderqtyuomid(prodInfo.
		 * getProduct_minimumorderqtyuomid());
		 * 
		 * if (prodInfo.getProduct_retailpackagingweightuomid() != null)
		 * prodData.setProduct_retailpackagingweightuomid(prodInfo.
		 * getProduct_retailpackagingweightuomid());
		 * 
		 * if (prodInfo.getProduct_masterpackheightuomid() != null)
		 * prodData.setProduct_masterpackheightuomid(prodInfo.
		 * getProduct_masterpackheightuomid());
		 * 
		 * if (prodInfo.getProduct_masterpackwidthuomid() != null)
		 * prodData.setProduct_masterpackwidthuomid(prodInfo.
		 * getProduct_masterpackwidthuomid());
		 * 
		 * if (prodInfo.getProduct_innerpackheightuomid() != null)
		 * prodData.setProduct_innerpackheightuomid(prodInfo.
		 * getProduct_innerpackheightuomid());
		 * 
		 * if (prodInfo.getProduct_innerpacklengthuomid() != null)
		 * prodData.setProduct_innerpacklengthuomid(prodInfo.
		 * getProduct_innerpacklengthuomid());
		 * 
		 * if (prodInfo.getProduct_innerpackwidthuomid() != null)
		 * prodData.setProduct_innerpackwidthuomid(prodInfo.
		 * getProduct_innerpackwidthuomid());
		 * 
		 * if (prodInfo.getProduct_innerpackweightuomid() != null)
		 * prodData.setProduct_innerpackweightuomid(prodInfo.
		 * getProduct_innerpackweightuomid());
		 * 
		 * if (prodInfo.getExtractionprocess_id() != null)
		 * prodData.setExtractionprocess_id(prodInfo.getExtractionprocess_id());
		 */
		// Allergen x product data
		if (prodInfo.getProductxallergen() != null) {

			Iterator<ProductXAllergen> itr = prodInfo.getProductxallergen().iterator();
			int rowPos = 0;
			while (itr.hasNext()) {
				ProductXAllergen prodallergenTemp = itr.next();

				ProductXAllergen prodAllerInsert = new ProductXAllergen();
				prodAllerInsert.setLastupdatedby(UUID.fromString(userId));
				prodAllerInsert.setProduct_id(prodInfo.getProduct_id());
				prodAllerInsert.setAllergen_id(prodallergenTemp.getAllergen_id());
				prodAllerInsert.setProductallergen_sort(rowPos);
				rowPos++;
				prodAllerInsert.setProductallergen_createdate(new Timestamp(System.currentTimeMillis()));
				prodAllerInsert.setProductallergen_updatedate(new Date(System.currentTimeMillis()));
				prodAllerInsert.setProductallergen_deleted(false);

				productService.insertProductallergen(prodAllerInsert);
			}
		}

		// carrieroil x product data
		if (prodInfo.getProductxcarrieroil() != null) {

			Iterator<ProductXCarrieroil> itr = prodInfo.getProductxcarrieroil().iterator();
			while (itr.hasNext()) {
				ProductXCarrieroil prodCarrieroilTemp = itr.next();

				ProductXCarrieroil prodCarrieroilInsert = new ProductXCarrieroil();
				prodCarrieroilInsert.setLastupdatedby(UUID.fromString(userId));
				prodCarrieroilInsert.setProduct_id(prodInfo.getProduct_id());
				prodCarrieroilInsert.setCarrieroil_id(prodCarrieroilTemp.getCarrieroil_id());
				prodCarrieroilInsert.setProductcarrieroil_createdate(new Timestamp(System.currentTimeMillis()));
				prodCarrieroilInsert.setProductcarrieroil_updatedate(new Date(System.currentTimeMillis()));
				prodCarrieroilInsert.setProductcarrieroil_deleted(false);

				productService.insertProductxcarrieroil(prodCarrieroilInsert);
			}
		}

		// cannabinoid x product data
		if (prodInfo.getProductxcannabinoid() != null) {

			Iterator<ProductXCannabinoid> itr = prodInfo.getProductxcannabinoid().iterator();
			int rowPos = 0;
			while (itr.hasNext()) {
				ProductXCannabinoid prodCannabinoidTemp = itr.next();

				ProductXCannabinoid prodCannabinoidInsert = new ProductXCannabinoid();

				prodCannabinoidInsert.setLastupdatedby(UUID.fromString(userId));
				prodCannabinoidInsert.setProduct_id(prodInfo.getProduct_id());

				prodCannabinoidInsert.setCannabinoid_id(prodCannabinoidTemp.getCannabinoid_id());
				prodCannabinoidInsert
						.setProductcannabinoid_actualperc(prodCannabinoidTemp.getProductcannabinoid_actualperc());
				prodCannabinoidInsert.setProductcannabinoid_deleted(false);
				prodCannabinoidInsert.setProductcannabinoid_createdate(new Timestamp(System.currentTimeMillis()));
				prodCannabinoidInsert
						.setProductcannabinoid_maxperc(prodCannabinoidTemp.getProductcannabinoid_maxperc());
				prodCannabinoidInsert
						.setProductcannabinoid_minperc(prodCannabinoidTemp.getProductcannabinoid_minperc());
				prodCannabinoidInsert.setProductcannabinoid_updatedate(new Date(System.currentTimeMillis()));

				productService.insertProductxcannabinoid(prodCannabinoidInsert);
			}
		}

		// terpene x product data

		if (prodInfo.getProductxterpene() != null) {

			Iterator<ProductXTerpene> itr = prodInfo.getProductxterpene().iterator();
			int rowPos = 0;
			while (itr.hasNext()) {

				ProductXTerpene prodTerpeneTemp = itr.next();

				ProductXTerpene prodTerpeneInsert = new ProductXTerpene();

				prodTerpeneInsert.setLastupdatedby(UUID.fromString(userId));
				prodTerpeneInsert.setProduct_id(prodInfo.getProduct_id());
				prodTerpeneInsert.setTerpene_id(prodTerpeneTemp.getTerpene_id());
				prodTerpeneInsert.setProductterpene_actualperc(prodTerpeneTemp.getProductterpene_actualperc());
				prodTerpeneInsert.setProductterpene_createdate(new Timestamp(System.currentTimeMillis()));
				prodTerpeneInsert.setProductterpene_deleted(false);
				prodTerpeneInsert.setProductterpene_maxperc(prodTerpeneTemp.getProductterpene_maxperc());
				prodTerpeneInsert.setProductterpene_minperc(prodTerpeneTemp.getProductterpene_minperc());
				prodTerpeneInsert.setProductterpene_updatedate(new Date(System.currentTimeMillis()));

				productService.insertProductxterpene(prodTerpeneInsert);
			}
		}

		Product product = productService.updateProduct(prodData);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gsonReturn = gsonBuilder.create();

		return gsonReturn.toJson(product);

	}

	private String retrieveProductStrain(HttpServletRequest request, HttpServletResponse response, String userId) {

		List<Strain> strain = productService.getStrain();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();
		return gson.toJson(strain);
	}

	private String retrieveFacility(HttpServletRequest request, HttpServletResponse response, String userId) {

		List<Facility> facility = productService.getFacility();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();
		return gson.toJson(facility);
	}

	private String editProduct(HttpServletRequest request, HttpServletResponse response, String userId,
			Map<String, String> parametersMap) {

		String productid = parametersMap.get("productid");

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();

		Product productData = productService.findProduct(UUID.fromString(productid));

		if (null != productData.getProduct_deleted() && !productData.getProduct_deleted()) {
			return gson.toJson(productData);
		}

		return new Gson().toJson("Product not valid or does not exist.");
	}

	private String saleProducts(HttpServletRequest request, HttpServletResponse response, String userId) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();

		List<Product> productData = productService.saleProducts();

		if (null != productData && productData.size() > 0) {
			return gson.toJson(productData);
		}

		return new Gson().toJson("No Products exist.");
	}

	private String removedProducts(HttpServletRequest request, HttpServletResponse response, String userId) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();

		List<Product> productData = productService.removedProducts();

		if (null != productData && productData.size() > 0) {
			return gson.toJson(productData);
		}

		return new Gson().toJson("No Products exist.");
	}

	private String futureProducts(HttpServletRequest request, HttpServletResponse response, String userId) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();

		List<Product> productData = productService.futureProducts();

		if (null != productData && productData.size() > 0) {
			return gson.toJson(productData);
		}

		return new Gson().toJson("No Products exist.");
	}

	private String createBrand(HttpServletRequest request, HttpServletResponse response, String userId) {

		// Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);

		Brand brand = new Brand();

		// family id dummy shud be left out
		brand.setBrandfamily_id(UUID.fromString("40369de6-34b3-4f65-9b70-349b6923c8c6"));

		brand.setBrand_createdate(new Timestamp(System.currentTimeMillis()));
		brand.setBrand_longname(" ");
		brand.setLastupdatedby(UUID.fromString(userId));
		brand.setBrand_deleted(false);

		Brand brandData = productService.createBrand(brand);

		return new Gson().toJson(brandData);
	}

	private String updateBrand(HttpServletRequest request, HttpServletResponse response, String userId) {

		// Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);

		String jObj = RestfulApiHelper.GetStringFromReqBody(request);

		Gson gson = new Gson();
		Brand brandInfo = gson.fromJson(jObj, Brand.class);

		Brand brandData = productService.findBrand(brandInfo.getBrand_id());

		if (brandInfo.getBrand_logo() != null)
			brandData.setBrand_logo(brandInfo.getBrand_logo());

		if (brandInfo.getBrand_longname() != null)
			brandData.setBrand_longname(brandInfo.getBrand_longname());

		if (brandInfo.getBrand_shortname() != null)
			brandData.setBrand_shortname(brandInfo.getBrand_shortname());

		if (brandInfo.getBrandfamily_id() != null)
			brandData.setBrandfamily_id(brandInfo.getBrandfamily_id());

		brandData.setBrand_deleted(false);
		brandData.setBrand_updatedate(new Date(System.currentTimeMillis()));
		brandData.setLastupdatedby(UUID.fromString(userId));

		Brand newBrand = productService.updateBrand(brandData);

		return new Gson().toJson(newBrand);
	}

	private String createBrandFamily(HttpServletRequest request, HttpServletResponse response, String userId) {

		// Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);

		Brandfamily brandfamily = new Brandfamily();

		brandfamily.setBrandfamily_createdate(new Timestamp(System.currentTimeMillis()));
		brandfamily.setBrandfamily_longname(" ");
		brandfamily.setLastupdatedby(UUID.fromString(userId));
		brandfamily.setBrandfamily_deleted(false);

		Brandfamily brandfamilyData = productService.createBrandfamily(brandfamily);

		return new Gson().toJson(brandfamilyData);
	}

	private String updateBrandFamily(HttpServletRequest request, HttpServletResponse response, String userId) {

		// Map<String, String> jObj = RestfulApiHelper.GetJsonFromReqBody(request);

		String jObj = RestfulApiHelper.GetStringFromReqBody(request);

		Gson gson = new Gson();
		Brandfamily brandfamilyInfo = gson.fromJson(jObj, Brandfamily.class);

		Brandfamily brandfamilyData = productService.findBrandfamily(brandfamilyInfo.getBrandfamily_id());

		if (brandfamilyInfo.getBrandfamily_longname() != null)
			brandfamilyData.setBrandfamily_longname(brandfamilyInfo.getBrandfamily_longname());

		if (brandfamilyInfo.getBrandfamily_shortname() != null)
			brandfamilyData.setBrandfamily_shortname(brandfamilyInfo.getBrandfamily_shortname());

		brandfamilyData.setBrandfamily_updatedate(new Date(System.currentTimeMillis()));
		brandfamilyData.setLastupdatedby(UUID.fromString(userId));

		Brandfamily newBrandfamily = productService.updateBrandfamily(brandfamilyData);

		return new Gson().toJson(newBrandfamily);
	}

	private String getBrands(HttpServletRequest request, HttpServletResponse response, String userId) {

		List<Brand> brand = productService.getBrands();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();
		return gson.toJson(brand);
	}

	private String getBrandFamily(HttpServletRequest request, HttpServletResponse response, String userId) {

		List<Brandfamily> brandfamily = productService.getBrandFamily();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.create();
		return gson.toJson(brandfamily);
	}

	private UUID getLotInfo(UUID lotnumber) {

		Lot lot = productService.getLotid(lotnumber);

		if (null != lot) {
			return lot.getLot_id();
		}

		return null;
	}

	@Override
	public List<ApiInfo> getApiList() {
		ArrayList<ApiInfo> myList = new ArrayList<ApiInfo>();
		myList.add(new ApiInfo("save", "To save new product", "product", "POST", "ProductPageImpl"));
		myList.add(new ApiInfo("deleteProduct", "To delete product", "product", "POST", "ProductPageImpl"));
		return myList;
	}

}
