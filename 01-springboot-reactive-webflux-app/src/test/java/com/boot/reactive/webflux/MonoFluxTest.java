package com.boot.reactive.webflux;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
	
	/*
	@Test
	public void testMono() {
		Mono<?> monoString = Mono.just("reactive-programming")
				.then(Mono.error(new RuntimeException("Exception Occured")))
				.log();
		monoString.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
	}*/
	
	@Test
	public void testFlux() {
		Flux<String> fluxString = Flux.just("spring", "springboot", "hibernate", "microservices")
				.concatWithValues("aws")
				.concatWith(Flux.error(new RuntimeException("Exception occured in flux")))
				.concatWithValues("angular")
				.log();
		fluxString.subscribe(System.out::println, (e)-> System.out.println(e.getMessage()));
		
	}
}
