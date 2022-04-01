package kr.or.son.bookgle.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long book_id;
	private String name;
	private String info;
	private String isbn;
	private String genre;
	private String author;
	private String plait;
	private String publisher;
	
	private BookState bookState;
	
}
