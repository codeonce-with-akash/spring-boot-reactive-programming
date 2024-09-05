package com.boot.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.reactive.dto.BookDto;
import com.boot.reactive.service.IBookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private IBookService bookService;
	
	@PostMapping("/create")
	public Mono<BookDto> createBook(@RequestBody Mono<BookDto> monoBook){
		return bookService.createBook(monoBook);
	}
	
	@GetMapping("/read/all")
	public Flux<BookDto> readAllBooks(){
		return bookService.readAllBooks();
	}
	
	@GetMapping("/read/one/{id}")
	public Mono<BookDto> readOneBookById(@PathVariable("id") String id){
		return bookService.readOneBookById(id);
	}
	
	@GetMapping("/read/books/range")
	public Flux<BookDto> readBookInRange(@RequestParam(required = true) Double min, @RequestParam(required = true) Double max){
		return bookService.readBookBetweenRange(min, max);
	}
	
	@PutMapping("/update/{id}")
	public Mono<BookDto> updateBook(@PathVariable("id") String id, @RequestBody Mono<BookDto> bookDto){
		return bookService.updateBook(bookDto, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteBook(@PathVariable("id") String id){
		return bookService.deleteBookById(id);
	}
}
