package com.exitTest.backendAngular.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exitTest.backendAngular.model.Product;
import com.exitTest.backendAngular.model.Status;
import com.exitTest.backendAngular.repository.ProductRepository;
import com.exitTest.backendAngular.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> fetchProductByProductNameOrBrandOrProductCode(String query) {
		if (query != null) {
			return productRepository.findByKeyword(query);
		}
		return productRepository.findAll();
	}

	public Product saveProduct(Product product) {
		System.out.println(product.toString());
		return this.productRepository.save(product);
	}

	public Product showSingleProduct(Long productId) {
		return this.productRepository.findById(productId).orElse(null);
	}

	public Product findByProductCode(String productCode) {
		return this.productRepository.findByProductCode(productCode);
	}

	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	public Status addProduct( Product product) {
		List<Product> products = productRepository.findAll();
		for (Product prod : products) {

			if (prod.equals(product)) {
				System.out.println("Product Already exists!");
				return Status.PRODUCT_ALREADY_EXISTS;
			}
		}
		productRepository.save(product);
		return Status.SUCCESS;
	}

}
