package com.exitTest.backendAngular.service;

import java.util.List;



import com.exitTest.backendAngular.model.Product;
import com.exitTest.backendAngular.model.Status;



public interface ProductService {

	public List<Product> fetchProductByProductNameOrBrandOrProductCode(String query);

	public Product saveProduct(Product product);

	public Product showSingleProduct(Long productId);

	public Product findByProductCode(String productCode);

	public List<Product> findAll();

	public Status addProduct( Product product);

}
