package com.boot.reactive.exception;

public class BooksNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BooksNotFoundException() {
		super();
	}
	
	public BooksNotFoundException(String message) {
		super(message);
	}

}
