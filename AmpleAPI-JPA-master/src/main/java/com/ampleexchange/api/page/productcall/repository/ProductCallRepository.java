package com.ampleexchange.api.page.productcall.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ampleexchange.api.page.common.model.Constant;
import com.ampleexchange.api.page.productcall.model.ProductCall;


public interface ProductCallRepository extends JpaRepository<ProductCall,UUID> {
	//List<ProductCall> getAllBpc(String org_id);	
	@Query(value = "SELECT * FROM productcall, category, subcategory WHERE category.category_id=productcall.category_id and subcategory.subcategory_id=productcall.subcategory_id and productcall.org_id= ?1", nativeQuery = true)
	List<ProductCall> getAllBpc(UUID orgId);
	
	@Query(value = "SELECT apiuser.user_name, category.category_longname, subcategory.subcategory_longname, productcall.productcall_desiredquantity, productcall.productcall_closingdate FROM productcall, apiuser, category, subcategory, vendorrelation WHERE category.category_id=productcall.category_id and subcategory.subcategory_id=productcall.subcategory_id and productcall.productcall_status='Published' and vendorrelation.vendorrelation_org_id=apiuser.org_id and productcall.org_id=vendorrelation.org_id and productcall.productcall_whocansubmitbids>=vendorrelation.vendorrelation_type and apiuser.user_id= ?1 and productcall.productcall_deleted = FALSE and productcall.productcall_openingdate < CURRENT_DATE and productcall.productcall_closingdate > CURRENT_DATE ", nativeQuery = true)
	List<ProductCall> getAllSellerPC(UUID userId );
	
	@Query(value = "SELECT * FROM productcall WHERE productcall_id= ?1", nativeQuery = true)
	ProductCall selectPC(UUID productcallId);
	
	@Query(value = "SELECT * FROM productcall WHERE productcall_status != 'Deleted' ", nativeQuery = true)
	List<ProductCall> selectPCAll();
}
