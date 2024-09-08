package com.boot.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	private String id;
	private String bookName;
	private String bookAuthor;
	private Double bookPrice;
	private String bookEdition;
	private String bookPublisher;
	private Boolean isAvailable;
}
