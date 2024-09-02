package com.boot.reactive.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.boot.reactive.model.Product;

import reactor.core.publisher.Flux;

@Component
public class ProductDao {
	
	private static void sleepExection(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getAllProducts(){
		return IntStream.rangeClosed(1, 50) //generate 1 to 50 numbers
		.peek(ProductDao::sleepExection) //sleep the thread for 1sec
		.peek(i -> System.out.println("processing count : "+i)) //print the process count
		.mapToObj(i -> new Product(i, "product : "+i)) //map this generated numbers with Product object
		.collect(Collectors.toList()); //convert the above Stream<Product> result into List<Product>
	}
	
	public Flux<Product> getAllProductsStream(){
		return Flux.range(1, 50) //generate 1 to 50 numbers
				.delayElements(Duration.ofSeconds(1)) //sleep the thread for 1sec
				.doOnNext(i -> System.out.println("processing count : " + i)) //print the process count
				.map(i -> new Product(i, "product : "+i)); //map this generated numbers with Product object
	}
}
