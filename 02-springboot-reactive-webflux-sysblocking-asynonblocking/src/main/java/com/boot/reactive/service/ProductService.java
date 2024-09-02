package com.boot.reactive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.reactive.dao.ProductDao;
import com.boot.reactive.model.Product;

import reactor.core.publisher.Flux;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public List<Product> getAllProducts(){
		long startTime = System.currentTimeMillis();
		List<Product> products = productDao.getAllProducts();
		long endTime = System.currentTimeMillis();
		System.out.println("Total Execution Time :: " + (endTime - startTime));
		return products;
	}
	
	public Flux<Product> getAllProductsStream(){
		long startTime = System.currentTimeMillis();
		Flux<Product> products = productDao.getAllProductsStream();
		long endTime = System.currentTimeMillis();
		System.out.println("Total Execution Time :: " + (endTime - startTime));
		return products;
	}
}
