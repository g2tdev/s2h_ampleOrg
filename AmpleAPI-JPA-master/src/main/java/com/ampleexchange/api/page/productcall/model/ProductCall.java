package com.ampleexchange.api.page.productcall.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "productcall")  // Define Table name here
public class ProductCall {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@Expose
	private UUID productcall_id;		
	private UUID org_id;
	@Expose
	private String productcall_title;	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date productcall_openingdate;	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date productcall_closingdate;	
	@Column(nullable = true)
	private Integer productcall_whocansubmitbids;	//Preferred, Approved, All
	@Column(nullable = true)
	private Character productcall_bulk_retail;		//'B' or 'R'
	@Column(nullable = true)
	private Boolean productcall_ingredientlistrequired;
	@Column(nullable = true)
	private Boolean productcall_requestsuggestedretailprice;		
	//private Boolean productcall_requestsuggestedbulkprice;
	@Column(nullable = true)
	private Boolean productcall_requestsuggestedwholesaleprice;
	//@JSONField(ordinal=11)
	//private Integer productcall_requestsuggestedwholesaleprice;	
	//@JSONField(ordinal=12)
	//private String desiredProductTier; 	//Good, Better, Best
	@Column(nullable = true)
	private Boolean productcall_productphotorequired;
	@Column(nullable = true)
	private Boolean productcall_productpackagingphotorequired;
	@Column(nullable = true)
	private Boolean productcall_logoretailsalerequired;
	@Column(nullable = true)
	private Boolean productcall_productnumberrequired;	
	//private Boolean productcall_productassetsrequired;
	
	@Column(nullable = true)
	private Character productcall_medical_recreational;		//Not Null **All Above are Not Null As well
	@Column(nullable = true)
	private Double productcall_desiredquantity;
	@Column(nullable = true)
	private Boolean productcall_providemaxqty;				//Not Null
	@Column(nullable = true)
	private UUID uom_id;
	@Column(nullable = true)
	private Boolean productcall_nopreferredskusize;			//Not Null
	@Column(nullable = true)
	private String productcall_specifyskusize;
	//@Column(nullable = true)
	//private String productcall_sizeinfo;
	@Column(nullable = true)
	private Character productcall_pickup;					//'B' or 'S'	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date productcall_delivery_pickup_date;			//Not Null
	@Column(nullable = true)
	private UUID facility_id;								//Not Null
	@Column(nullable = true)
	private Boolean productcall_repeatingorder;				//Not Null	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date productcall_endrepeatdate;
	@Column(nullable = true)
	private Character productcall_repeatfrequency;
	@Column(nullable = true)
	@Expose
	private String productcall_additionalinfo;
	@Column(name = "category_id", nullable = true)
	@Expose
	private UUID categoryId;
	@Column(name = "subcategory_id", nullable = true)
	private UUID subcategoryId;
	
	//Optional Fields
	@Column(nullable = true)
	private UUID growingmethod_id;
	@Column(nullable = true)
	private UUID dryingmethod_id;
	@Column(nullable = true)
	private UUID trimmingmethod_id;
	@Column(nullable = true)
	private Double productcall_targetminthcperc;
	@Column(nullable = true)
	private Double productcall_targetmaxthcperc;
	@Column(nullable = true)
	private Boolean productcall_organicgrowing;
	@Column(nullable = true)
	private Double productcall_targetmincbdperc;
	@Column(nullable = true)
	private Double productcall_targetmaxcbdperc;	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date productcall_productlifeexpiry;
	@Column(nullable = true)
	private UUID provincestate_id;
	@Column(nullable = true)
	private Boolean productcall_mustmanufacture;
	//@Column(nullable = true)
	//private UUID terpene_id;
	//@Column(nullable = true)
	//private Double productcall_terpeneminperc;
	//@Column(nullable = true)
	//private Double productcall_terpenemaxperc;
	//@Column(nullable = true)
	//private UUID cannabinoid_id;
	//@Column(nullable = true)
	//private Double productcall_cannabinoidminperc;
	//@Column(nullable = true)
	//private Double productcall_cannabinoidmaxperc;
	@Column(nullable = true)
	private UUID producttier_id;	
	//@Column(nullable = true)
	//private UUID foodallergen_id;
	@Column(nullable = true)
	private Boolean productcall_qadoclabtests;
	@Column(nullable = true)
	private Boolean productcall_qadoccoa;
	@Column(nullable = true)
	private Boolean productcall_qadoccom;
	@Column(nullable = true)
	private Boolean productcall_qadocproofofirradiation;
	@Column(nullable = true)
	private String productcall_colourdescription;
	@Column(nullable = true)
	private String productcall_masterpackinfoprovider;
	@Column(nullable = true)
	private String productcall_innerpackinfoprovider;
	@Column(nullable = true)
	private String productcall_productpackinfoprovider;
	@Column(nullable = true)
	private String productcall_retailiteminfoprovider;
	@Column(nullable = true)
	private String productcall_palletinfoprovider;
	@Expose
	private String productcall_status;				//Not Null
	@Expose	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date productcall_createdate;		//Not Null
	@Column(nullable = true)	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date productcall_updatedate;
	@Column(nullable = true)
	private Boolean productcall_deleted;
	private UUID lastupdatedby;					//Not Null		
	private UUID extractionprocess_id;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "category_id", insertable=false, updatable=false)
	//@JoinColumn(name = "category_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "subcategory_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private SubCategory subcategory;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "org_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private Organization organization;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "uom_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private UoM uom;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "facility_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private Facility facility;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "growingmethod_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private GrowingMethod growingMethod;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "dryingmethod_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private DryingMethod dryingMethod;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "trimmingmethod_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private TrimmingMethod trimmingMethod;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "provincestate_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private ProvinceState provinceState;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "producttier_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private ProductTier productTier;
	
	@ManyToOne(fetch = FetchType.EAGER)//(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name = "extractionprocess_id", insertable=false, updatable=false)
	//@JoinColumn(name = "subcategory_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude private ExtractionProcess extractionProcess;
	
	@ManyToMany(cascade = { CascadeType.ALL })//(cascade= CascadeType.ALL, optional=false)
	@JoinTable(
	        name = "productcallxterpene", 
	        joinColumns = { @JoinColumn(name = "productcall_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "terpene_id") }
	    )
	@ToString.Exclude @EqualsAndHashCode.Exclude private  Set<Terpene> terpene;
	
	@ManyToMany(cascade = { CascadeType.ALL })//(cascade= CascadeType.ALL, optional=false)
	@JoinTable(
	        name = "productcallxcannabinoid", 
	        joinColumns = { @JoinColumn(name = "productcall_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "cannabinoid_id") }
	    )
	@ToString.Exclude @EqualsAndHashCode.Exclude private  Set<Cannabinoid> cannabinoid;
	
	@ManyToMany(cascade = { CascadeType.ALL })//(cascade= CascadeType.ALL, optional=false)
	@JoinTable(
	        name = "productcallxallergen", 
	        joinColumns = { @JoinColumn(name = "productcall_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "allergen_id") }
	    )
	@ToString.Exclude @EqualsAndHashCode.Exclude private  Set<Allergen> allergen;
	
	@ManyToMany(cascade = { CascadeType.ALL })//(cascade= CascadeType.ALL, optional=false)
	@JoinTable(
	        name = "productcallxcarrieroil", 
	        joinColumns = { @JoinColumn(name = "productcall_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "carrieroil_id") }
	    )
	@ToString.Exclude @EqualsAndHashCode.Exclude private  Set<Carrieroil> carrieroil;
	
	@Transient
	private Set<ProductcallXTerpene> productcallXTerpene;
	
	@Transient
	private Set<ProductcallXCannabinoid> productcallXCannabinoid;
	
	@Transient
	private Set<ProductcallXAllergen> productcallXAllergen;
	
	@Transient
	private Set<ProductcallXCarrieroil> productcallXCarrieroil;
	
	
	//private String organizationID;
	//private String organizationName;
}

