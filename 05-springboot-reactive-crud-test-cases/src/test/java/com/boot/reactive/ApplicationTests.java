package com.boot.reactive;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.boot.reactive.controller.BookController;
import com.boot.reactive.dto.BookDto;
import com.boot.reactive.service.IBookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(BookController.class)
class ApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private IBookService service;
	
	@Test
	public void createBookTest() {
		Mono<BookDto> bookDtoMono = Mono.just(new BookDto("101", "Effective Java", "Bloch", 900.0, "Third Edition", "US Publication", true));
		when(service.createBook(bookDtoMono)).thenReturn(bookDtoMono);
		
		webTestClient.post().uri("/book/create")
		.body(Mono.just(bookDtoMono), BookDto.class)
		.exchange()
		.expectStatus().isOk();
	}
	
	@Test
	public void readAllBooksTest() {
		Flux<BookDto> bookDtoFlux = Flux.just(new BookDto("101", "Effective Java", "Bloch", 900.0, "Third Edition", "US Publication", true),
				new BookDto("102", "SBMS", "Raghu", 500.0, "Second Edition", "NIT Publication", true)
				);
		
		when(service.readAllBooks()).thenReturn(bookDtoFlux);
		
		Flux<BookDto> responseBody = webTestClient.get().uri("/book/read/all")
		.exchange()
		.expectStatus().isOk()
		.returnResult(BookDto.class)
		.getResponseBody();
		
		StepVerifier.create(responseBody)
		.expectSubscription()
		.expectNext(new BookDto("101", "Effective Java", "Bloch", 900.0, "Third Edition", "US Publication", true))
		.expectNext(new BookDto("102", "SBMS", "Raghu", 500.0, "Second Edition", "NIT Publication", true))
		.verifyComplete();
	}
	
	@Test
	public void readOneBookByIdTest() {
		Mono<BookDto> bookDtoMono = Mono.just(new BookDto("101", "Effective Java", "Bloch", 900.0, "Third Edition", "US Publication", true));
		when(service.readOneBookById(any())).thenReturn(bookDtoMono);
		
		Flux<BookDto> responseBody = webTestClient.get().uri("/book/read/one/102")
		.exchange()
		.expectStatus().isOk()
		.returnResult(BookDto.class)
		.getResponseBody();
		
		StepVerifier.create(responseBody)
		.expectSubscription()
		.expectNextMatches(p -> p.getBookName().equalsIgnoreCase("Effective Java"))
		.verifyComplete();
	}
	
	@Test
	public void updateBookTest() {
		Mono<BookDto> bookDtoMono = Mono.just(new BookDto("102", "SBMS", "Raghu", 500.0, "Second Edition", "NIT Publication", true));
		when(service.updateBook(bookDtoMono, "102")).thenReturn(bookDtoMono);
		
		webTestClient.put().uri("/book/update/102")
		.body(bookDtoMono, BookDto.class)
		.exchange()
		.expectStatus().isOk();
	}
	
	@Test
	public void deleteProductTest() {
		BDDMockito.given(service.deleteBookById(any())).willReturn(Mono.empty());
		webTestClient.delete().uri("/book/delete/102")
		.exchange()
		.expectStatus().isOk();
	} 

}
