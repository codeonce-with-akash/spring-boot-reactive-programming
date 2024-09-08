package com.boot.reactive.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.boot.reactive.dto.ErrorType;
import com.boot.reactive.exception.BookNotFoundException;
import com.boot.reactive.exception.BooksNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class BookGlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorType> handleBookNotFoundException(BookNotFoundException bnfe){
		return new ResponseEntity<>(new ErrorType(bnfe.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BooksNotFoundException.class)
	public ResponseEntity<ErrorType> handleBooksNotFoundException(BooksNotFoundException bnfe){
		return new ResponseEntity<>(new ErrorType(bnfe.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
	}
}
