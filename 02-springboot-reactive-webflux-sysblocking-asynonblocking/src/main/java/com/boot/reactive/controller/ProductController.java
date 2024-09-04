package com.boot.reactive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.reactive.model.Product;
import com.boot.reactive.service.ProductService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	/** 
	 1. In traditional Rest API until it processed 50 records, 
	 our consumer/subscriber(browser) will not get the response.
	 this mechanism is called as Synchronous and Blocking mechanism 
	 
	 2. In middle as subscriber I don't want to get a response, if I stop the request
	 still my back end processing the data, where already subscriber cancel the request.
	 **/
	
	@GetMapping
	public List<Product> getAllProducts(){
		return service.getAllProducts();
	}
	
	
	/** 
	 1. In Reactive programming my request is not wait to complete the 50 product object,
	 once it completed one product object then there is do onNext() method call from the 
	 subscriber and my subscriber(browser) can received it.
	 NOTE: PUBLISHER: database | SUBSCRIBER: browser 
	 
	 2. In stream if I will cancel the request at 5th then immediately after 5th there is 
	 no another request flow.
	 **/
	
	@GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //TEXT_EVENT_STREAM_VALUE = "text/event-stream"
	public Flux<Product> getAllProductsStream(){
		return service.getAllProductsStream();
	}
}
