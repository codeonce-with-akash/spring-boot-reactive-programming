package com.boot.reactive.service;

import com.boot.reactive.dto.BookDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBookService {
	public Mono<BookDto> createBook(Mono<BookDto> bookDto);
	public Flux<BookDto> readAllBooks();
	public Mono<BookDto> readOneBookById(String id);
	public Flux<BookDto> readBookBetweenRange(Double min, Double max);
	public Mono<BookDto> updateBook(Mono<BookDto> monoBookDto, String id);
	public Mono<Void> deleteBookById(String id);
}
