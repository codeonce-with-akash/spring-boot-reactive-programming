package com.boot.reactive.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.boot.reactive.dto.BookDto;
import com.boot.reactive.entity.Book;

import reactor.core.publisher.Flux;

public interface IBookRepository extends ReactiveMongoRepository<Book, String> {
	public Flux<BookDto> findByBookPriceBetween(Range<Double> priceRange);
}
