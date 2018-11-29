package com.ampleexchange.api.page.product.dbservice.impl;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.product.dbservice.ProductDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ampleexchange.api.page.product.model.*;
import com.ampleexchange.api.page.product.repository.*;

@Service
@Transactional
public class ProductDBServiceImpl implements ProductDBService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StrainRepository strainRepository;

	@Autowired
	private ProductFacilityRepository facilityRepository;

	@Autowired
	private BrandRepository brandrepository;

	@Autowired
	private BrandFamilyRepository brandfamilyRepository;

	@Autowired
	private LotRepository lotRepository;

	@Autowired
	private ProductXAllergenRepository productxallergenRepository;

	@Autowired
	private ProductXCannabinoidRepository productxcannabinoidRepository;

	@Autowired
	private ProductXCarrieroilRepository productxcarrierRepository;

	@Autowired
	private ProductXTerpeneRepository productxterpeneRepository;

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product deleteProduct(String product_id) {
		productRepository.deleteProduct(UUID.fromString(product_id));
		return productRepository.findById(UUID.fromString(product_id)).get();
	}

	@Override
	public Product findProduct(UUID productid) {
		return productRepository.findById(productid).get();
	}

	@Override
	public List<Strain> getStrain() {
		List<Strain> allStrains = strainRepository.retrieveStrains();
		return allStrains;
	}

	@Override
	public List<Facility> getFacility() {
		List<Facility> allFacility = facilityRepository.retrieveFacilities();
		return allFacility;
	}

	@Override
	public List<Product> saleProducts() {
		return productRepository.saleProducts();
	}

	@Override
	public List<Product> removedProducts() {
		return productRepository.removedProducts();
	}

	@Override
	public List<Product> futureProducts() {
		return productRepository.futureProducts();
	}

	@Override
	public Brand createBrand(Brand brand) {
		return brandrepository.save(brand);
	}

	@Override
	public Brand updateBrand(Brand brand) {
		return brandrepository.save(brand);
	}

	@Override
	public Brand findBrand(UUID brandid) {
		return brandrepository.findById(brandid).get();
	}

	@Override
	public Brandfamily createBrandfamily(Brandfamily brandfamily) {
		return brandfamilyRepository.save(brandfamily);
	}

	@Override
	public Brandfamily updateBrandfamily(Brandfamily brandfamily) {
		return brandfamilyRepository.save(brandfamily);
	}

	@Override
	public Brandfamily findBrandfamily(UUID brandfamilyid) {
		return brandfamilyRepository.findById(brandfamilyid).get();
	}

	@Override
	public List<Brand> getBrands() {
		List<Brand> allBrands = brandrepository.getBrands();
		return allBrands;
	}

	@Override
	public List<Brandfamily> getBrandFamily() {
		List<Brandfamily> allBrandFamily = brandfamilyRepository.getBrandFamily();
		return allBrandFamily;
	}

	@Override
	public Lot getLotid(UUID lotnumber) {
		Lot lot = lotRepository.getLotid(lotnumber);
		return lot;
	}

	@Override
	public ProductXAllergen insertProductallergen(ProductXAllergen productxallergen) {
		return productxallergenRepository.save(productxallergen);
	}

	@Override
	public ProductXTerpene insertProductxterpene(ProductXTerpene productxterpene) {
		return productxterpeneRepository.save(productxterpene);
	}

	@Override
	public ProductXCarrieroil insertProductxcarrieroil(ProductXCarrieroil productxcarrieroil) {
		return productxcarrierRepository.save(productxcarrieroil);
	}

	@Override
	public ProductXCannabinoid insertProductxcannabinoid(ProductXCannabinoid productxcannabinoid) {
		return productxcannabinoidRepository.save(productxcannabinoid);
	}

}
