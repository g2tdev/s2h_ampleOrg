package com.ampleexchange.api.page.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.ampleexchange.api.page.guidedsetup.model.ContactComm;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
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

	/*
	 * private UUID terpene_id; private Double product_terpeneminperc; private
	 * Double product_terpenemaxperc; private Double product_actualterpeneperc;
	 */

	/*
	 * private UUID cannabinoid_id; private Double product_cannabinoidminperc;
	 * private Double product_cannabinoidmaxperc; private Double
	 * product_actualcannabinoidperc;
	 */
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

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "productxallergen", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "allergen_id") })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Allergen> allergen;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "productxterpene", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "terpene_id") })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Terpene> terpene;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "productxcannabinoid", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "cannabinoid_id") })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Cannabinoid> cannabinoid;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "productxcarrieroil", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "carrieroil_id") })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Carrieroil> carrieroil;

	@OneToMany(mappedBy = "document_id")
	private Set<Document> document;

	@OneToMany(mappedBy = "productphoto_id")
	private Set<Productphotos> productphotos;

	@Transient
	private Set<ProductXAllergen> productxallergen;

	@Transient
	private Set<ProductXTerpene> productxterpene;

	@Transient
	private Set<ProductXCannabinoid> productxcannabinoid;

	@Transient
	private Set<ProductXCarrieroil> productxcarrieroil;

	public UUID getProduct_id() {
		return product_id;
	}

	public void setProduct_id(UUID product_id) {
		this.product_id = product_id;
	}

	public String getProduct_shortname() {
		return product_shortname;
	}

	public void setProduct_shortname(String product_shortname) {
		this.product_shortname = product_shortname;
	}

	public String getProduct_longname() {
		return product_longname;
	}

	public void setProduct_longname(String product_longname) {
		this.product_longname = product_longname;
	}

	public UUID getLot_id() {
		return lot_id;
	}

	public void setLot_id(UUID lot_id) {
		this.lot_id = lot_id;
	}

	public UUID getOrg_id() {
		return org_id;
	}

	public void setOrg_id(UUID org_id) {
		this.org_id = org_id;
	}

	public UUID getCategory_id() {
		return category_id;
	}

	public void setCategory_id(UUID category_id) {
		this.category_id = category_id;
	}

	public UUID getSubcategory_id() {
		return subcategory_id;
	}

	public void setSubcategory_id(UUID subcategory_id) {
		this.subcategory_id = subcategory_id;
	}

	public UUID getGrowingmethod_id() {
		return growingmethod_id;
	}

	public void setGrowingmethod_id(UUID growingmethod_id) {
		this.growingmethod_id = growingmethod_id;
	}

	public UUID getDryingmethod_id() {
		return dryingmethod_id;
	}

	public void setDryingmethod_id(UUID dryingmethod_id) {
		this.dryingmethod_id = dryingmethod_id;
	}

	public UUID getTrimmingmethod_id() {
		return trimmingmethod_id;
	}

	public void setTrimmingmethod_id(UUID trimmingmethod_id) {
		this.trimmingmethod_id = trimmingmethod_id;
	}

	public UUID getProducttier_id() {
		return producttier_id;
	}

	public void setProducttier_id(UUID producttier_id) {
		this.producttier_id = producttier_id;
	}

	public UUID getStrain_id() {
		return strain_id;
	}

	public void setStrain_id(UUID strain_id) {
		this.strain_id = strain_id;
	}

	public UUID getManufacturingfacility_id() {
		return manufacturingfacility_id;
	}

	public void setManufacturingfacility_id(UUID manufacturingfacility_id) {
		this.manufacturingfacility_id = manufacturingfacility_id;
	}

	public String getViewproduct() {
		return viewproduct;
	}

	public void setViewproduct(String viewproduct) {
		this.viewproduct = viewproduct;
	}

	public UUID getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(UUID brand_id) {
		this.brand_id = brand_id;
	}

	public Boolean getProduct_organicallygrown() {
		return product_organicallygrown;
	}

	public void setProduct_organicallygrown(Boolean product_organicallygrown) {
		this.product_organicallygrown = product_organicallygrown;
	}

	public String getProduct_shortdescription() {
		return product_shortdescription;
	}

	public void setProduct_shortdescription(String product_shortdescription) {
		this.product_shortdescription = product_shortdescription;
	}

	public String getProduct_longdescription() {
		return product_longdescription;
	}

	public void setProduct_longdescription(String product_longdescription) {
		this.product_longdescription = product_longdescription;
	}

	public Boolean getProduct_bulkorretail() {
		return product_bulkorretail;
	}

	public void setProduct_bulkorretail(Boolean product_bulkorretail) {
		this.product_bulkorretail = product_bulkorretail;
	}

	public Date getProduct_availableforsale() {
		return product_availableforsale;
	}

	public void setProduct_availableforsale(Date product_availableforsale) {
		this.product_availableforsale = product_availableforsale;
	}

	public UUID getProduct_sourceproductid() {
		return product_sourceproductid;
	}

	public void setProduct_sourceproductid(UUID product_sourceproductid) {
		this.product_sourceproductid = product_sourceproductid;
	}

	public Boolean getProduct_manufacturethisproduct() {
		return product_manufacturethisproduct;
	}

	public void setProduct_manufacturethisproduct(Boolean product_manufacturethisproduct) {
		this.product_manufacturethisproduct = product_manufacturethisproduct;
	}

	public Boolean getProduct_buyfromexchange() {
		return product_buyfromexchange;
	}

	public void setProduct_buyfromexchange(Boolean product_buyfromexchange) {
		this.product_buyfromexchange = product_buyfromexchange;
	}

	public String getProduct_vendor() {
		return product_vendor;
	}

	public void setProduct_vendor(String product_vendor) {
		this.product_vendor = product_vendor;
	}

	public String getProduct_manufacturingfacility() {
		return product_manufacturingfacility;
	}

	public void setProduct_manufacturingfacility(String product_manufacturingfacility) {
		this.product_manufacturingfacility = product_manufacturingfacility;
	}

	public String getProduct_vendorlotnumber() {
		return product_vendorlotnumber;
	}

	public void setProduct_vendorlotnumber(String product_vendorlotnumber) {
		this.product_vendorlotnumber = product_vendorlotnumber;
	}

	public Boolean getProduct_deleted() {
		return product_deleted;
	}

	public void setProduct_deleted(Boolean product_deleted) {
		this.product_deleted = product_deleted;
	}

	public Timestamp getProduct_createdate() {
		return product_createdate;
	}

	public void setProduct_createdate(Timestamp product_createdate) {
		this.product_createdate = product_createdate;
	}

	public Date getProduct_updatedate() {
		return product_updatedate;
	}

	public void setProduct_updatedate(Date product_updatedate) {
		this.product_updatedate = product_updatedate;
	}

	public UUID getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(UUID lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Double getProduct_targetminthcperc() {
		return product_targetminthcperc;
	}

	public void setProduct_targetminthcperc(Double product_targetminthcperc) {
		this.product_targetminthcperc = product_targetminthcperc;
	}

	public Double getProduct_targetmaxthcperc() {
		return product_targetmaxthcperc;
	}

	public void setProduct_targetmaxthcperc(Double product_targetmaxthcperc) {
		this.product_targetmaxthcperc = product_targetmaxthcperc;
	}

	public Double getProduct_actualthc() {
		return product_actualthc;
	}

	public void setProduct_actualthc(Double product_actualthc) {
		this.product_actualthc = product_actualthc;
	}

	public Double getProduct_targetmincbdperc() {
		return product_targetmincbdperc;
	}

	public void setProduct_targetmincbdperc(Double product_targetmincbdperc) {
		this.product_targetmincbdperc = product_targetmincbdperc;
	}

	public Double getProduct_targetmaxcbdperc() {
		return product_targetmaxcbdperc;
	}

	public void setProduct_targetmaxcbdperc(Double product_targetmaxcbdperc) {
		this.product_targetmaxcbdperc = product_targetmaxcbdperc;
	}

	public Double getProduct_actualcbd() {
		return product_actualcbd;
	}

	public void setProduct_actualcbd(Double product_actualcbd) {
		this.product_actualcbd = product_actualcbd;
	}

	public Boolean getProduct_doesthisproductexpire() {
		return product_doesthisproductexpire;
	}

	public void setProduct_doesthisproductexpire(Boolean product_doesthisproductexpire) {
		this.product_doesthisproductexpire = product_doesthisproductexpire;
	}

	public Date getProduct_expirydate() {
		return product_expirydate;
	}

	public void setProduct_expirydate(Date product_expirydate) {
		this.product_expirydate = product_expirydate;
	}

	public String getProduct_ingredientlist() {
		return product_ingredientlist;
	}

	public void setProduct_ingredientlist(String product_ingredientlist) {
		this.product_ingredientlist = product_ingredientlist;
	}

	public Double getProduct_driedcannabisequiv() {
		return product_driedcannabisequiv;
	}

	public void setProduct_driedcannabisequiv(Double product_driedcannabisequiv) {
		this.product_driedcannabisequiv = product_driedcannabisequiv;
	}

	public UUID getProduct_driedcannabisequivuomid() {
		return product_driedcannabisequivuomid;
	}

	public void setProduct_driedcannabisequivuomid(UUID product_driedcannabisequivuomid) {
		this.product_driedcannabisequivuomid = product_driedcannabisequivuomid;
	}

	public String getProduct_colourdescription() {
		return product_colourdescription;
	}

	public void setProduct_colourdescription(String product_colourdescription) {
		this.product_colourdescription = product_colourdescription;
	}

	public Double getProduct_skusize() {
		return product_skusize;
	}

	public void setProduct_skusize(Double product_skusize) {
		this.product_skusize = product_skusize;
	}

	public Double getProduct_packheight() {
		return product_packheight;
	}

	public void setProduct_packheight(Double product_packheight) {
		this.product_packheight = product_packheight;
	}

	public UUID getProduct_packheightuomid() {
		return product_packheightuomid;
	}

	public void setProduct_packheightuomid(UUID product_packheightuomid) {
		this.product_packheightuomid = product_packheightuomid;
	}

	public Double getProduct_packlength() {
		return product_packlength;
	}

	public void setProduct_packlength(Double product_packlength) {
		this.product_packlength = product_packlength;
	}

	public UUID getProduct_packlengthuomid() {
		return product_packlengthuomid;
	}

	public void setProduct_packlengthuomid(UUID product_packlengthuomid) {
		this.product_packlengthuomid = product_packlengthuomid;
	}

	public Double getProduct_packwidth() {
		return product_packwidth;
	}

	public void setProduct_packwidth(Double product_packwidth) {
		this.product_packwidth = product_packwidth;
	}

	public UUID getProduct_packwidthuomid() {
		return product_packwidthuomid;
	}

	public void setProduct_packwidthuomid(UUID product_packwidthuomid) {
		this.product_packwidthuomid = product_packwidthuomid;
	}

	public Double getProduct_packproductnumber() {
		return product_packproductnumber;
	}

	public void setProduct_packproductnumber(Double product_packproductnumber) {
		this.product_packproductnumber = product_packproductnumber;
	}

	public Double getProduct_netcontentweight() {
		return product_netcontentweight;
	}

	public void setProduct_netcontentweight(Double product_netcontentweight) {
		this.product_netcontentweight = product_netcontentweight;
	}

	public UUID getProduct_netcontentweightuomid() {
		return product_netcontentweightuomid;
	}

	public void setProduct_netcontentweightuomid(UUID product_netcontentweightuomid) {
		this.product_netcontentweightuomid = product_netcontentweightuomid;
	}

	public Double getProduct_retailpackagingweight() {
		return product_retailpackagingweight;
	}

	public void setProduct_retailpackagingweight(Double product_retailpackagingweight) {
		this.product_retailpackagingweight = product_retailpackagingweight;
	}

	public String getProduct_retailpackagingmaterial() {
		return product_retailpackagingmaterial;
	}

	public void setProduct_retailpackagingmaterial(String product_retailpackagingmaterial) {
		this.product_retailpackagingmaterial = product_retailpackagingmaterial;
	}

	public Boolean getProduct_areretailunitsserialized() {
		return product_areretailunitsserialized;
	}

	public void setProduct_areretailunitsserialized(Boolean product_areretailunitsserialized) {
		this.product_areretailunitsserialized = product_areretailunitsserialized;
	}

	public Double getProduct_masterpackquantity() {
		return product_masterpackquantity;
	}

	public void setProduct_masterpackquantity(Double product_masterpackquantity) {
		this.product_masterpackquantity = product_masterpackquantity;
	}

	public Double getProduct_masterpackheight() {
		return product_masterpackheight;
	}

	public void setProduct_masterpackheight(Double product_masterpackheight) {
		this.product_masterpackheight = product_masterpackheight;
	}

	public Double getProduct_masterpacklength() {
		return product_masterpacklength;
	}

	public void setProduct_masterpacklength(Double product_masterpacklength) {
		this.product_masterpacklength = product_masterpacklength;
	}

	public Double getProduct_masterpackwidth() {
		return product_masterpackwidth;
	}

	public void setProduct_masterpackwidth(Double product_masterpackwidth) {
		this.product_masterpackwidth = product_masterpackwidth;
	}

	public Double getProduct_masterpackweight() {
		return product_masterpackweight;
	}

	public void setProduct_masterpackweight(Double product_masterpackweight) {
		this.product_masterpackweight = product_masterpackweight;
	}

	public UUID getProduct_masterpackweightuomid() {
		return product_masterpackweightuomid;
	}

	public void setProduct_masterpackweightuomid(UUID product_masterpackweightuomid) {
		this.product_masterpackweightuomid = product_masterpackweightuomid;
	}

	public Double getProduct_masterpackproductnumber() {
		return product_masterpackproductnumber;
	}

	public void setProduct_masterpackproductnumber(Double product_masterpackproductnumber) {
		this.product_masterpackproductnumber = product_masterpackproductnumber;
	}

	public Double getProduct_innerpackquantity() {
		return product_innerpackquantity;
	}

	public void setProduct_innerpackquantity(Double product_innerpackquantity) {
		this.product_innerpackquantity = product_innerpackquantity;
	}

	public Double getProduct_innerpackheight() {
		return product_innerpackheight;
	}

	public void setProduct_innerpackheight(Double product_innerpackheight) {
		this.product_innerpackheight = product_innerpackheight;
	}

	public Double getProduct_innerpacklength() {
		return product_innerpacklength;
	}

	public void setProduct_innerpacklength(Double product_innerpacklength) {
		this.product_innerpacklength = product_innerpacklength;
	}

	public Double getProduct_innerpackwidth() {
		return product_innerpackwidth;
	}

	public void setProduct_innerpackwidth(Double product_innerpackwidth) {
		this.product_innerpackwidth = product_innerpackwidth;
	}

	public Double getProduct_innerpackweight() {
		return product_innerpackweight;
	}

	public void setProduct_innerpackweight(Double product_innerpackweight) {
		this.product_innerpackweight = product_innerpackweight;
	}

	public Double getProduct_innerpackproductnumber() {
		return product_innerpackproductnumber;
	}

	public void setProduct_innerpackproductnumber(Double product_innerpackproductnumber) {
		this.product_innerpackproductnumber = product_innerpackproductnumber;
	}

	public Double getProduct_layersperpallet() {
		return product_layersperpallet;
	}

	public void setProduct_layersperpallet(Double product_layersperpallet) {
		this.product_layersperpallet = product_layersperpallet;
	}

	public Double getProduct_casesperlayeronapallet() {
		return product_casesperlayeronapallet;
	}

	public void setProduct_casesperlayeronapallet(Double product_casesperlayeronapallet) {
		this.product_casesperlayeronapallet = product_casesperlayeronapallet;
	}

	public Double getProduct_itemsretailpack() {
		return product_itemsretailpack;
	}

	public void setProduct_itemsretailpack(Double product_itemsretailpack) {
		this.product_itemsretailpack = product_itemsretailpack;
	}

	public UUID getProduct_itemsretailpackuomid() {
		return product_itemsretailpackuomid;
	}

	public void setProduct_itemsretailpackuomid(UUID product_itemsretailpackuomid) {
		this.product_itemsretailpackuomid = product_itemsretailpackuomid;
	}

	public Double getProduct_totalproductpackweight() {
		return product_totalproductpackweight;
	}

	public void setProduct_totalproductpackweight(Double product_totalproductpackweight) {
		this.product_totalproductpackweight = product_totalproductpackweight;
	}

	public UUID getProduct_totalproductpackweightuomid() {
		return product_totalproductpackweightuomid;
	}

	public void setProduct_totalproductpackweightuomid(UUID product_totalproductpackweightuomid) {
		this.product_totalproductpackweightuomid = product_totalproductpackweightuomid;
	}

	public UUID getProduct_skusizeuomid() {
		return product_skusizeuomid;
	}

	public void setProduct_skusizeuomid(UUID product_skusizeuomid) {
		this.product_skusizeuomid = product_skusizeuomid;
	}

	public Double getProduct_minimumorderqty() {
		return product_minimumorderqty;
	}

	public void setProduct_minimumorderqty(Double product_minimumorderqty) {
		this.product_minimumorderqty = product_minimumorderqty;
	}

	public UUID getProduct_minimumorderqtyuomid() {
		return product_minimumorderqtyuomid;
	}

	public void setProduct_minimumorderqtyuomid(UUID product_minimumorderqtyuomid) {
		this.product_minimumorderqtyuomid = product_minimumorderqtyuomid;
	}

	public UUID getProduct_retailpackagingweightuomid() {
		return product_retailpackagingweightuomid;
	}

	public void setProduct_retailpackagingweightuomid(UUID product_retailpackagingweightuomid) {
		this.product_retailpackagingweightuomid = product_retailpackagingweightuomid;
	}

	public UUID getProduct_masterpackheightuomid() {
		return product_masterpackheightuomid;
	}

	public void setProduct_masterpackheightuomid(UUID product_masterpackheightuomid) {
		this.product_masterpackheightuomid = product_masterpackheightuomid;
	}

	public UUID getProduct_masterpackwidthuomid() {
		return product_masterpackwidthuomid;
	}

	public void setProduct_masterpackwidthuomid(UUID product_masterpackwidthuomid) {
		this.product_masterpackwidthuomid = product_masterpackwidthuomid;
	}

	public UUID getProduct_innerpackheightuomid() {
		return product_innerpackheightuomid;
	}

	public void setProduct_innerpackheightuomid(UUID product_innerpackheightuomid) {
		this.product_innerpackheightuomid = product_innerpackheightuomid;
	}

	public UUID getProduct_innerpacklengthuomid() {
		return product_innerpacklengthuomid;
	}

	public void setProduct_innerpacklengthuomid(UUID product_innerpacklengthuomid) {
		this.product_innerpacklengthuomid = product_innerpacklengthuomid;
	}

	public UUID getProduct_innerpackwidthuomid() {
		return product_innerpackwidthuomid;
	}

	public void setProduct_innerpackwidthuomid(UUID product_innerpackwidthuomid) {
		this.product_innerpackwidthuomid = product_innerpackwidthuomid;
	}

	public UUID getProduct_innerpackweightuomid() {
		return product_innerpackweightuomid;
	}

	public void setProduct_innerpackweightuomid(UUID product_innerpackweightuomid) {
		this.product_innerpackweightuomid = product_innerpackweightuomid;
	}

	public UUID getExtractionprocess_id() {
		return extractionprocess_id;
	}

	public void setExtractionprocess_id(UUID extractionprocess_id) {
		this.extractionprocess_id = extractionprocess_id;
	}

	public Set<Allergen> getAllergen() {
		return allergen;
	}

	public void setAllergen(Set<Allergen> allergen) {
		this.allergen = allergen;
	}

	public Set<ProductXAllergen> getProductxallergen() {
		return productxallergen;
	}

	public void setProductxallergen(Set<ProductXAllergen> productxallergen) {
		this.productxallergen = productxallergen;
	}

	public Set<Terpene> getTerpene() {
		return terpene;
	}

	public void setTerpene(Set<Terpene> terpene) {
		this.terpene = terpene;
	}

	public Set<Cannabinoid> getCannabinoid() {
		return cannabinoid;
	}

	public void setCannabinoid(Set<Cannabinoid> cannabinoid) {
		this.cannabinoid = cannabinoid;
	}

	public Set<Carrieroil> getCarrieroil() {
		return carrieroil;
	}

	public void setCarrieroil(Set<Carrieroil> carrieroil) {
		this.carrieroil = carrieroil;
	}

	public Set<ProductXTerpene> getProductxterpene() {
		return productxterpene;
	}

	public void setProductxterpene(Set<ProductXTerpene> productxterpene) {
		this.productxterpene = productxterpene;
	}

	public Set<ProductXCannabinoid> getProductxcannabinoid() {
		return productxcannabinoid;
	}

	public void setProductxcannabinoid(Set<ProductXCannabinoid> productxcannabinoid) {
		this.productxcannabinoid = productxcannabinoid;
	}

	public Set<ProductXCarrieroil> getProductxcarrieroil() {
		return productxcarrieroil;
	}

	public void setProductxcarrieroil(Set<ProductXCarrieroil> productxcarrieroil) {
		this.productxcarrieroil = productxcarrieroil;
	}

	public Set<Document> getDocument() {
		return document;
	}

	public void setDocument(Set<Document> document) {
		this.document = document;
	}

	public Set<Productphotos> getProductphotos() {
		return productphotos;
	}

	public void setProductphotos(Set<Productphotos> productphotos) {
		this.productphotos = productphotos;
	}

}
