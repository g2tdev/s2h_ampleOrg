package com.ampleexchange.api.page.guidedsetup.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampleexchange.api.common.MyConstants;
import com.ampleexchange.api.model.ApiInfo;
import com.ampleexchange.api.page.common.modelhelper.ContactHelper;
import com.ampleexchange.api.page.common.modelhelper.FacilityHelper;
import com.ampleexchange.api.page.common.modelhelper.OrganizationHelper;
import com.ampleexchange.api.page.common.modelhelper.UserHelper;
import com.ampleexchange.api.page.guidedsetup.GuidedSetupPage;
import com.ampleexchange.api.page.guidedsetup.dbservice.ContactCommService;
import com.ampleexchange.api.page.guidedsetup.dbservice.ContactService;
import com.ampleexchange.api.page.guidedsetup.dbservice.FacilityService;
import com.ampleexchange.api.page.guidedsetup.dbservice.FacilityxContactService;
import com.ampleexchange.api.page.guidedsetup.dbservice.FacilityxHealthlicenseService;
import com.ampleexchange.api.page.guidedsetup.dbservice.HealthLicenseTypeService;
import com.ampleexchange.api.page.guidedsetup.dbservice.OrganizationService;
import com.ampleexchange.api.page.guidedsetup.dbservice.OrganizationStatusService;
import com.ampleexchange.api.page.guidedsetup.dbservice.ProvinceStateService;
import com.ampleexchange.api.page.guidedsetup.dbservice.UserService;
import com.ampleexchange.api.page.guidedsetup.model.Contact;
import com.ampleexchange.api.page.guidedsetup.model.ContactComm;
import com.ampleexchange.api.page.guidedsetup.model.Facility;
import com.ampleexchange.api.page.guidedsetup.model.FacilityxContact;
import com.ampleexchange.api.page.guidedsetup.model.FacilityxHealthlicense;
import com.ampleexchange.api.page.guidedsetup.model.Organization;
import com.ampleexchange.api.page.guidedsetup.model.User;
import com.ampleexchange.api.util.HibernateProxyTypeAdapter;
import com.ampleexchange.api.util.RestfulApiHelper;
import com.ampleexchange.api.util.StringHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class GuidedSetupPageImpl implements GuidedSetupPage {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactCommService contactCommService;

	@Autowired
	private UserService userService;

	@Autowired
	private FacilityxContactService facilityxContactService;
	
	@Autowired
	private FacilityxHealthlicenseService facilityxHealthlicenseService;
	
	@Autowired
	private OrganizationStatusService organizationStatusService;
	
	@Autowired
	private HealthLicenseTypeService healthLicenseTypeService;
	
	@Autowired
	private ProvinceStateService provinceStateService;

	@Override
	public String processHttpRequest(HttpServletRequest request,
			HttpServletResponse response, String apiVersion, String apiId,
			String userId, Map<String, String> parametersMap) {
		String retVal = "";

		switch (apiId) {
		case "0": // '0' means to insert/retrieve user at sign in into the DB
			retVal = insertRetrieveSigninUser(request, userId);
			break;
		case "1": // '1' means to update and organization into the DB
			retVal = updateOrganization(request, userId);
			break;
		case "2": // '2' means to get organization/s (by id) from the DB
			retVal = getOrganizations(parametersMap);
			break;
		case "3": // '3' means to insert/update facility and/or contact into the
					// DB
					// retVal = insertUpdateFacilityContact(request, response,
					// userId);
			retVal = insertUpdateFacilityContact(request, userId);
			break;
		case "4": // '4' means to insert/update contact into the DB
			retVal = insertUpdateContact(request, response, userId);
			break;
		case "5": // '5' means to get facility/s (by id / organization id) from
					// the DB
			retVal = getFacilities(parametersMap);
			break;
		case "6": // '6' means to get contacts by facility id from the DB
			retVal = getContacts(parametersMap);
			break;
		}
		return retVal;
	}

	private String insertRetrieveSigninUser(HttpServletRequest request, String userId) {
		String strRequest = RestfulApiHelper.GetStringFromReqBody(request);

		UserHelper userHelper = new Gson().fromJson(strRequest,
				UserHelper.class);

		String email = userHelper.getEmail();
		String role = userHelper.getRole();

		User existingUser = userService.getByEmail(email);
		String newUserId = "";
		String orgId = "";
		String orgStatus = "";
		if (existingUser == null) {
			Organization organization = new Organization();
			User newUser = new User();
			if ("admin".equalsIgnoreCase(role)) {
				orgId = UUID.randomUUID().toString();
				organization.setOrg_id(UUID.fromString(orgId));
				organization.setOrg_type(userHelper.getStatus());
				organization.setOrg_shortname(userHelper.getOrganizationName());
				organization.setOrg_longname(userHelper.getOrganizationName());
				organization
						.setOrg_createdate(Calendar.getInstance().getTime());
				organization.setOrg_deleted(false);
				organization.setLastupdatedby(userService.getById(UUID
						.fromString(userId)));
				organization = organizationService.save(organization);
				orgStatus = "New Organization";
			} else {
				orgId = "";
				orgStatus = "Organization has not been setup, contact your administrator";
			}

			newUserId = UUID.randomUUID().toString();
			newUser.setUser_id(UUID.fromString(newUserId));
			newUser.setUser_password(userHelper.getPassword());
			newUser.setUser_role(role);
			newUser.setUser_email(email);
			newUser.setOrg_id(organization);
			newUser.setUser_createdate(Calendar.getInstance().getTime());
			newUser.setUser_deleted(false);
			newUser.setLastupdatedby(UUID.fromString(userId));
			userService.save(newUser);
		} else {
			newUserId = existingUser.getUser_id().toString();
			if (existingUser.getOrg_id() != null) {
				orgId = existingUser.getOrg_id().getOrg_id().toString();
				orgStatus = existingUser.getOrg_id().getOrg_type();
			}
		}

		Map<String, String> mapResponse = new HashMap<String, String>();
		mapResponse.put("User_ID", newUserId);
		mapResponse.put("Organization_ID", orgId);
		mapResponse.put("Status", orgStatus);

		return new Gson().toJson(mapResponse);
	}

	private String updateOrganization(HttpServletRequest request, String userId) {
		String strRequest = RestfulApiHelper.GetStringFromReqBody(request);

		OrganizationHelper organizationHelper = new Gson().fromJson(strRequest,
				OrganizationHelper.class);

		if (organizationHelper.getOrg_id() != null
				&& !"".equals(organizationHelper.getOrg_id())) {
			Organization organization = organizationService.getById(UUID
					.fromString(organizationHelper.getOrg_id()));
			organization.setOrganizationstatus_id(organizationStatusService.getById(UUID.fromString(organizationHelper
					.getSelectCompanyStatus())));
			organization.setOrg_buysell(organizationHelper.getBuySellOrBoth());
			organization.setOrg_buydomesticinternational(organizationHelper
					.getBuyDomesticInternational());
			organization.setOrg_selldomesticinternational(organizationHelper
					.getSellDomesticInternational());
			organization.setOrg_updatedate(Calendar.getInstance().getTime());
			organization.setLastupdatedby(userService.getById(UUID
					.fromString(userId)));
			organizationService.save(organization);

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder
					.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
			return gson.toJson(organization);
		} else {
			return "{}";
		}
	}

	private String getOrganizations(Map<String, String> parametersMap) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
		if (parametersMap.containsKey(MyConstants.URL_QUERY)) {
			Organization organization = organizationService.getById(UUID
					.fromString(parametersMap.get(MyConstants.URL_QUERY)));
			return gson.toJson(organization);
		} else {
			List<Organization> listOrganizations = organizationService.getAll();
			return gson.toJson(listOrganizations);
		}
	}

	private String insertUpdateFacilityContact(HttpServletRequest request, String userId) {
		User user = userService.getById(UUID.fromString(userId));

		String strRequest = RestfulApiHelper.GetStringFromReqBody(request);

		FacilityHelper facilityHelper = new Gson().fromJson(strRequest,
				FacilityHelper.class);

		Facility facility = insertUpdateFacility(facilityHelper, user);

		if (facilityHelper.getContacts() != null
				&& !facilityHelper.getContacts().isEmpty()) {
			Set<Contact> setContacts = new HashSet<Contact>();

			try {
				List<ContactHelper> listContactHelpers = facilityHelper
						.getContacts();
				for (ContactHelper contactHelper : listContactHelpers) {
					contactHelper.setFacility_id(facility.getFacility_id()
							.toString());
					contactHelper.setOrg_id(facility.getOrg_id().getOrg_id().toString());
					setContacts.add(insertUpdateContact(contactHelper, user));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			facility.setContacts(setContacts);
		}

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(facility);
	}

	private Facility insertUpdateFacility(FacilityHelper facilityHelper,
			User user) {
		Facility facility;

		if (facilityHelper.getFacility_id() != null
				&& !"".equals(facilityHelper.getFacility_id())) {
			facility = facilityService.getById(UUID.fromString(facilityHelper
					.getFacility_id()));
			facility.setFacility_updatedate(Calendar.getInstance().getTime());
		} else {
			facility = new Facility();
			facility.setFacility_id(UUID.randomUUID());
			facility.setFacility_createdate(Calendar.getInstance().getTime());
			facility.setFacility_deleted(false);
		}

		facility.setLastupdatedby(user);
		facility.setOrg_id(organizationService.getById(UUID
				.fromString(facilityHelper.getOrg_id())));
		facility.setFacility_shortname(facilityHelper.getFacilityName());
		facility.setFacility_longname(facilityHelper.getFacilityName());
		facility.setFacility_apartment(facilityHelper.getAptOrSuite());
		facility.setFacility_street(facilityHelper.getAddress());
		facility.setFacility_city(facilityHelper.getCity());
		facility.setProvincestate_id(provinceStateService.getById(UUID.fromString(facilityHelper
				.getProvinceOrState())));
		facility.setFacility_postalzip(facilityHelper.getPostalCode());

		boolean billTo = facilityHelper.isBillto();
		boolean shipTo = facilityHelper.isShipto();
		String billToShipTo = "";

		if (billTo && shipTo) {
			billToShipTo = "A";
		} else if (billTo) {
			billToShipTo = "B";
		} else if (shipTo) {
			billToShipTo = "S";
		}

		facility.setFacility_billtoshipto(billToShipTo);
		facility.setFacility_buyingselling("B");
		facilityService.save(facility);
		
		FacilityxHealthlicense facilityxHealthlicense = facilityxHealthlicenseService.getByFacilityType(facility.getFacility_id(), UUID.fromString(facilityHelper
				.getHealthCanadaLicenceType()));
		
		if(facilityxHealthlicense != null){
			facilityxHealthlicense .setFacilityhealthlicense_updatedate(Calendar.getInstance().getTime());
		} else {
			facilityxHealthlicense = new FacilityxHealthlicense();
			facilityxHealthlicense.setFacilityhealthlicense_id(UUID.randomUUID());
			facilityxHealthlicense.setFacilityhealthlicense_createdate(Calendar.getInstance().getTime());
			facilityxHealthlicense.setFacilityhealthlicense_deleted(false);
		}
		
		facilityxHealthlicense.setLastupdatedby(user);
		facilityxHealthlicense.setFacility_id(facilityService.getById(facility.getFacility_id()));
		facilityxHealthlicense.setFacilityhealthlicense_number(facilityHelper
				.getHealthCanadaLicenceNumber());
		facilityxHealthlicense.setHealthlicensetype_id(healthLicenseTypeService.getById(UUID.fromString(facilityHelper
				.getHealthCanadaLicenceType())));
		facilityxHealthlicense.setFacilityhealthlicense_expirydate(StringHelper
				.convertStringtoSqlDate(facilityHelper
						.getHealthCanadaLicenceExpiryDate()));
		facilityxHealthlicense.setFacilityhealthlicense_status("A");
		
		facilityxHealthlicenseService.save(facilityxHealthlicense);
		
		facility.setFacilityhealthlicense_number(facilityxHealthlicense.getFacilityhealthlicense_number());
		facility.setFacilityhealthlicense_type(facilityxHealthlicense.getHealthlicensetype_id());
		facility.setFacilityhealthlicense_expirydate(facilityxHealthlicense.getFacilityhealthlicense_expirydate());
		
		return facility;
	}
	
	private String insertUpdateContact(HttpServletRequest request,
			HttpServletResponse response, String userId) {
		String strRequest = RestfulApiHelper.GetStringFromReqBody(request);

		ContactHelper contactHelper = new Gson().fromJson(strRequest,
				ContactHelper.class);

		Contact contact = insertUpdateContact(contactHelper, userService.getById(UUID.fromString(userId)));

		if (contact != null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder
					.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
			return gson.toJson(contact);
		} else {
			return "{}";
		}
	}

	private Contact insertUpdateContact(ContactHelper contactHelper, User user) {
		Contact contact;

		if (contactHelper.getContact_id() != null
				&& !"".equals(contactHelper.getContact_id())) {
			contact = contactService.getById(UUID.fromString(contactHelper
					.getContact_id()));
			contact.setContact_updatedate(Calendar.getInstance().getTime());
		} else {
			contact = new Contact();
			contact.setContact_id(UUID.randomUUID());
			contact.setContact_createdate(Calendar.getInstance().getTime());
			contact.setContact_deleted(false);
		}

		contact.setLastupdatedby(user);
		contact.setOrg_id(organizationService.getById(UUID
				.fromString(contactHelper.getOrg_id())));
		contact.setContact_firstname(contactHelper.getFirstName());
		contact.setContact_lastname(contactHelper.getLastName());
		contact.setContact_role(contactHelper.getContactRole());
		contact.setContact_jobtitle(contactHelper.getJobTitle());
		contactService.save(contact);

		if (contactHelper.getFacility_id() != null
				&& !"".equals(contactHelper.getFacility_id())) {
			FacilityxContact facilityxContact = facilityxContactService
					.getByFacilityContact(
							UUID.fromString(contactHelper.getFacility_id()),
							contact.getContact_id());

			if (facilityxContact == null) {
				facilityxContact = new FacilityxContact();
				facilityxContact.setFacilityxcontact_id(UUID.randomUUID());
				facilityxContact.setFacilitycontact_createdate(Calendar
						.getInstance().getTime());
				facilityxContact.setFacilitycontact_deleted(false);
				facilityxContact.setLastupdatedby(user);
				facilityxContact.setContact_id(contact);
				facilityxContact.setFacility_id(facilityService.getById(UUID
						.fromString(contactHelper.getFacility_id())));
				facilityxContactService.save(facilityxContact);
			}
		}

		if (contactHelper.getContact_id() == null
				|| "".equals(contactHelper.getContact_id())) {
			contactHelper.setContact_id(contact.getContact_id().toString());
		}

		if (contactHelper.getEmail() != null
				&& !"".equals(contactHelper.getEmail())) {
			if (insertUpdateContactComm(contactHelper, user, "Email",
					contactHelper.getEmail()) != null) {
				contact.setContact_email(contactHelper.getEmail());
			}
		}

		if (contactHelper.getPhoneNumber() != null
				&& !"".equals(contactHelper.getPhoneNumber())) {
			if (insertUpdateContactComm(contactHelper, user, "Phone Number",
					contactHelper.getPhoneNumber()) != null) {
				contact.setContact_phonenumber(contactHelper.getPhoneNumber());
			}
		}

		if (contactHelper.getAltPhoneNumber() != null
				&& !"".equals(contactHelper.getAltPhoneNumber())) {
			if (insertUpdateContactComm(contactHelper, user,
					"Alt Phone Number", contactHelper.getAltPhoneNumber()) != null) {
				contact.setContact_altphonenumber(contactHelper.getAltPhoneNumber());
			}
		}

		if (contactHelper.getFax() != null
				&& !"".equals(contactHelper.getFax())) {
			if (insertUpdateContactComm(contactHelper, user, "Fax",
					contactHelper.getFax()) != null) {
				contact.setContact_fax(contactHelper.getFax());
			}
		}

		return contact;
	}

	private ContactComm insertUpdateContactComm(ContactHelper contactHelper,
			User user, String contactType, String contactCommDetail) {
		ContactComm contactComm = contactCommService.getByContactIdType(
				UUID.fromString(contactHelper.getContact_id()), contactType);

		if (contactComm == null) {
			contactComm = new ContactComm();
			contactComm.setContactcomm_id(UUID.randomUUID());
			contactComm.setContact_id(contactService.getById(UUID
					.fromString(contactHelper.getContact_id())));
			contactComm.setContactcomm_type(contactType);
			contactComm.setContact_createdate(Calendar.getInstance().getTime());
			contactComm.setContact_deleted(false);
		} else {
			contactComm.setContact_updatedate(Calendar.getInstance().getTime());
		}

		contactComm.setLastupdatedby(user);
		contactComm.setContactcomm_detail(contactCommDetail);
		return contactCommService.save(contactComm);
	}
	
	private String getFacilities(Map<String, String> parametersMap) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

		if(parametersMap.containsKey(MyConstants.QUERY_TYPE) && parametersMap.containsKey(MyConstants.URL_QUERY)){
			if ("1".equals(parametersMap.get(MyConstants.QUERY_TYPE))) {
				Facility facility = facilityService.getById(UUID.fromString(parametersMap.get(MyConstants.URL_QUERY)));
				Set<FacilityxContact> setFacilityxContacts = facility.getFacilityxcontacts();
				Set<Contact> setContacts = new HashSet<Contact>();
				for (FacilityxContact facilityxContact : setFacilityxContacts) {
					setContacts.add(setContactComms(facilityxContact.getContact_id()));
				}
				facility.setContacts(setContacts);
				
				Set<FacilityxHealthlicense> setFacilityHealthLicenses = facility.getFacilityxhealthlicenses();
				if(!setFacilityHealthLicenses.isEmpty()){
					FacilityxHealthlicense facilityxHealthlicense = setFacilityHealthLicenses.iterator().next();
					facility.setFacilityhealthlicense_number(facilityxHealthlicense.getFacilityhealthlicense_number());
					facility.setFacilityhealthlicense_type(facilityxHealthlicense.getHealthlicensetype_id());
					facility.setFacilityhealthlicense_expirydate(facilityxHealthlicense.getFacilityhealthlicense_expirydate());
				}
				
				return gson.toJson(facility);
			} else if ("2".equals(parametersMap.get(MyConstants.QUERY_TYPE)) && parametersMap.containsKey(MyConstants.URL_QUERY)) {
				Set<Facility> setFacilities = facilityService.getByOrgId(UUID.fromString(parametersMap.get(MyConstants.URL_QUERY)));
				for (Facility facility : setFacilities) {
					Set<FacilityxContact> setFacilityxContacts = facility.getFacilityxcontacts();
					Set<Contact> setContacts = new HashSet<Contact>();
					for (FacilityxContact facilityxContact : setFacilityxContacts) {
						setContacts.add(setContactComms(facilityxContact.getContact_id()));
					}
					facility.setContacts(setContacts);
					
					Set<FacilityxHealthlicense> setFacilityHealthLicenses = facility.getFacilityxhealthlicenses();
					if(!setFacilityHealthLicenses.isEmpty()){
						FacilityxHealthlicense facilityxHealthlicense = setFacilityHealthLicenses.iterator().next();
						facility.setFacilityhealthlicense_number(facilityxHealthlicense.getFacilityhealthlicense_number());
						facility.setFacilityhealthlicense_type(facilityxHealthlicense.getHealthlicensetype_id());
						facility.setFacilityhealthlicense_expirydate(facilityxHealthlicense.getFacilityhealthlicense_expirydate());
					}
				}
				return gson.toJson(setFacilities);
			}		
		} 
		
		return "[]";
	}
	
	private Contact setContactComms(Contact contact) {
		for (ContactComm contactComm : contact.getContactComms()) {
			if ("Email".equals(contactComm.getContactcomm_type())) {
				contact.setContact_email(contactComm.getContactcomm_detail());
			}
			
			if ("Phone Number".equals(contactComm.getContactcomm_type())) {
				contact.setContact_phonenumber(contactComm.getContactcomm_detail());
			}

			if ("Alt Phone Number".equals(contactComm.getContactcomm_type())) {
				contact.setContact_altphonenumber(contactComm.getContactcomm_detail());
			}

			if ("Fax".equals(contactComm.getContactcomm_type())) {
				contact.setContact_fax(contactComm.getContactcomm_detail());
			}			
		}
		return contact;
	}

	private String getContacts(Map<String, String> parametersMap) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
		
		if(parametersMap.containsKey(MyConstants.QUERY_TYPE) && parametersMap.containsKey(MyConstants.URL_QUERY)){
			if("1".equals(parametersMap.get(MyConstants.QUERY_TYPE))){
				Set<FacilityxContact> setFacilityxContacts = facilityxContactService.getByFacility(UUID.fromString(parametersMap.get(MyConstants.URL_QUERY)));
				Set<Contact> setContacts = new HashSet<Contact>();
				for (FacilityxContact facilityxContact : setFacilityxContacts) {
					setContacts.add(setContactComms(facilityxContact.getContact_id()));
				}			
				return gson.toJson(setContacts);
			}else if("2".equals(parametersMap.get(MyConstants.QUERY_TYPE))){
				return gson.toJson(contactService.getAll());
			}
		}
		
		return gson.toJson(contactService.getAll());
	}

	@Override
	public List<ApiInfo> getApiList() {
		ArrayList<ApiInfo> myList = new ArrayList<ApiInfo>();
		myList.add(new ApiInfo("GuidedSetupCall",
				"To insert/retrieve user at sign in into db", "/v*/gs/users",
				"GET", "GuidedSetupPageImpl"));
		myList.add(new ApiInfo("GuidedSetupCall",
				"To insert/update organization into db 1.2-1",
				"/v*/gs/organizations", "PUT", "GuidedSetupPageImpl"));
		myList.add(new ApiInfo("GuidedSetupCall",
				"To get organization/s (by id) from db",
				"/v*/gs/organizations", "GET", "GuidedSetupPageImpl"));
		myList.add(new ApiInfo("GuidedSetupCall",
				"To insert/update facility and/or contact into db",
				"/v*/gs/facilities", "POST", "GuidedSetupPageImpl"));
		myList.add(new ApiInfo("GuidedSetupCall",
				"To insert/update contact into db", "/v*/gs/contacts", "POST",
				"GuidedSetupPageImpl"));
		myList.add(new ApiInfo("GuidedSetupCall",
				"To get facility/s (by id / organization id) from db",
				"/v*/gs/facilities", "GET", "GuidedSetupPageImpl"));
		myList.add(new ApiInfo("GuidedSetupCall",
				"To get contacts by facility id from db", "/v*/gs/contacts",
				"GET", "GuidedSetupPageImpl"));
		return myList;
	}
}