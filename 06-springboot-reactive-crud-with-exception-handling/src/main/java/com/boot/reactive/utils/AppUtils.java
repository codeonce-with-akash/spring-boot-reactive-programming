package com.boot.reactive.utils;

import org.springframework.beans.BeanUtils;

import com.boot.reactive.dto.BookDto;
import com.boot.reactive.entity.Book;

public class AppUtils {
	
	//1. covert DTO to entity
	public static Book dtoToEntity(BookDto bookDto) {
		Book book = new Book();
		BeanUtils.copyProperties(bookDto, book);
		return book;
	}
	
	//2 convert entity to DTO
	public static BookDto entityToDto(Book book) {
		BookDto bookDto = new BookDto();
		BeanUtils.copyProperties(book, bookDto);
		return bookDto;
	}
}
