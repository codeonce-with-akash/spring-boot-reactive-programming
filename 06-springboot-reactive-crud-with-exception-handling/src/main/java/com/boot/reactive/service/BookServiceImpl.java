package com.boot.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.boot.reactive.dto.BookDto;
import com.boot.reactive.entity.Book;
import com.boot.reactive.repository.IBookRepository;
import com.boot.reactive.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookRepository bookRepository;

	@Override
	public Mono<BookDto> createBook(Mono<BookDto> bookDto) {
		return bookDto.map(AppUtils::dtoToEntity)
				// .map(bookRepository::insert) //if we use map(-) here then we will get Mono<Mono<Book>>.
				.flatMap(bookRepository::insert) // so to get Mono<Book> we are using flatMap(-) here.
				.map(AppUtils::entityToDto);
	}

	@Override
	public Flux<BookDto> readAllBooks() {
		return bookRepository.findAll().map(AppUtils::entityToDto);
	}

	@Override
	public Mono<BookDto> readOneBookById(String id) {
		return bookRepository.findById(id).map(AppUtils::entityToDto);
	}

	@Override
	public Flux<BookDto> readBookBetweenRange(Double min, Double max) {
		return bookRepository.findByBookPriceBetween(Range.closed(min, max));
	}

	@Override
	public Mono<BookDto> updateBook(Mono<BookDto> monoBookDto, String id) {
		  return bookRepository.findById(id)
				.flatMap(m -> monoBookDto
						.map(AppUtils::dtoToEntity)
						.doOnNext(b -> b.setId(id)))
				.flatMap(bookRepository::save)
				.map(AppUtils::entityToDto);
	}
	
	@Override
	public Mono<Void> deleteBookById(String id) {
		//Mono<Void> deleteById = bookRepository.deleteById(id);
		Mono<Void> deleteByBook = bookRepository.findById(id).flatMap(bookRepository::delete);
		return deleteByBook;
	}

}
