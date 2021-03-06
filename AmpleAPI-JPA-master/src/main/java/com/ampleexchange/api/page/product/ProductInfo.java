package com.ampleexchange.api.page.product;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class ProductInfo {

	private UUID product_id;

	private String product_shortname;
	private String product_longname;
	private UUID lot_id;
	private UUID org_id;
	private UUID category_id;
	private UUID subcategory_id;
	private UUID growingmethod_id;
	private UUID dryingmethod_id;
	private UUID trimmingmethod_id;
	private UUID producttier_id;
	private UUID strain_id;
	private UUID manufacturingfacility_id;
	private String viewproduct;
	private UUID brand_id;
	private Boolean product_organicallygrown;
	private String product_shortdescription;
	private String product_longdescription;
	private Boolean product_bulkorretail;
	private Date product_availableforsale;
	private UUID product_sourceproductid;
	private Boolean product_manufacturethisproduct;
	private Boolean product_buyfromexchange;
	private String product_vendor;
	private String product_manufacturingfacility;
	private String product_vendorlotnumber;
	private Boolean product_deleted;
	private Timestamp product_createdate;
	private Date product_updatedate;
	private UUID lastupdatedby;
	private Double product_targetminthcperc;
	private Double product_targetmaxthcperc;
	private Double product_actualthc;
	private Double product_targetmincbdperc;
	private Double product_targetmaxcbdperc;
	private Double product_actualcbd;
	private UUID terpene_id;
	private Double product_terpeneminperc;
	private Double product_terpenemaxperc;
	private Double product_actualterpeneperc;
	private UUID cannabinoid_id;
	private Double product_cannabinoidminperc;
	private Double product_cannabinoidmaxperc;
	private Double product_actualcannabinoidperc;
	private Boolean product_doesthisproductexpire;
	private Date product_expirydate;
	private String product_ingredientlist;
	private Double product_driedcannabisequiv;
	private UUID product_driedcannabisequivuomid;
	private String product_colourdescription;
	private Double product_skusize;
	private Double product_packheight;
	private UUID product_packheightuomid;
	private Double product_packlength;
	private UUID product_packlengthuomid;
	private Double product_packwidth;
	private UUID product_packwidthuomid;
	private Double product_packproductnumber;
	private Double product_netcontentweight;
	private UUID product_netcontentweightuomid;
	private Double product_retailpackagingweight;
	private String product_retailpackagingmaterial;
	private Boolean product_areretailunitsserialized;
	private Double product_masterpackquantity;
	private Double product_masterpackheight;
	private Double product_masterpacklength;
	private Double product_masterpackwidth;
	private Double product_masterpackweight;
	private UUID product_masterpackweightuomid;
	private Double product_masterpackproductnumber;
	private Double product_innerpackquantity;
	private Double product_innerpackheight;
	private Double product_innerpacklength;
	private Double product_innerpackwidth;
	private Double product_innerpackweight;
	private Double product_innerpackproductnumber;
	private Double product_layersperpallet;
	private Double product_casesperlayeronapallet;
	private Double product_itemsretailpack;
	private UUID product_itemsretailpackuomid;
	private Double product_totalproductpackweight;
	private UUID product_totalproductpackweightuomid;
	private UUID product_skusizeuomid;
	private Double product_minimumorderqty;
	private UUID product_minimumorderqtyuomid;
	private UUID product_retailpackagingweightuomid;
	private UUID product_masterpackheightuomid;
	private UUID product_masterpackwidthuomid;
	private UUID product_innerpackheightuomid;
	private UUID product_innerpacklengthuomid;
	private UUID product_innerpackwidthuomid;
	private UUID product_innerpackweightuomid;
	private UUID extractionprocess_id;
	
	private List<UUID> productxallergen;
	private List<UUID> productxcannabinoid;
	private List<UUID> productxcarrieroil;
	private List<UUID> productxterpene;
	
	
}
