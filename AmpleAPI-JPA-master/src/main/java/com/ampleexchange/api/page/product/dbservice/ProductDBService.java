package com.ampleexchange.api.page.product.dbservice;

import java.util.List;
import java.util.UUID;

import com.ampleexchange.api.page.product.model.Brand;
import com.ampleexchange.api.page.product.model.Brandfamily;
import com.ampleexchange.api.page.product.model.Facility;
import com.ampleexchange.api.page.product.model.Lot;
import com.ampleexchange.api.page.product.model.Product;
import com.ampleexchange.api.page.product.model.ProductXAllergen;
import com.ampleexchange.api.page.product.model.ProductXCannabinoid;
import com.ampleexchange.api.page.product.model.ProductXCarrieroil;
import com.ampleexchange.api.page.product.model.ProductXTerpene;
import com.ampleexchange.api.page.product.model.Strain;

public interface ProductDBService {

	public Product createProduct(Product product);

	public Product deleteProduct(String product_id);

	public Product updateProduct(Product product);

	public Product findProduct(UUID productid);

	public List<Strain> getStrain();

	public List<Facility> getFacility();

	public List<Product> saleProducts();

	public List<Product> removedProducts();

	public List<Product> futureProducts();

	public Brand createBrand(Brand brand);

	public Brand updateBrand(Brand brand);

	public Brand findBrand(UUID brandid);

	public Brandfamily createBrandfamily(Brandfamily brandfamily);

	public Brandfamily updateBrandfamily(Brandfamily brandfamily);

	public Brandfamily findBrandfamily(UUID brandfamilyid);

	public List<Brand> getBrands();

	public List<Brandfamily> getBrandFamily();

	public Lot getLotid(UUID lotnumber);

	public ProductXAllergen insertProductallergen(ProductXAllergen productxallergen);

	public ProductXTerpene insertProductxterpene(ProductXTerpene productxterpene);

	public ProductXCarrieroil insertProductxcarrieroil(ProductXCarrieroil productxcarrieroil);

	public ProductXCannabinoid insertProductxcannabinoid(ProductXCannabinoid productxcannabinoid);

	public void deleteProductxallergen(UUID product_id);

	public void deleteProductxterpene(UUID product_id);

	public void deleteProductxcannabinoid(UUID product_id);

	public void deleteProductxcarrieroil(UUID product_id);

}
