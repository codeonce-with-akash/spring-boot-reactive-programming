package com.boot.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book-info")
public class Book {
	@Id
	private String id;
	private String bookName;
	private String bookAuthor;
	private Double bookPrice;
	private String bookEdition;
	private String bookPublisher;
	private Boolean isAvailable;
}
