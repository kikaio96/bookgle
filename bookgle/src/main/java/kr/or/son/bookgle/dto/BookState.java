package kr.or.son.bookgle.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookState implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long book_state_id;
	private long book_id;
	private int total_count;
	private int remain;
}
