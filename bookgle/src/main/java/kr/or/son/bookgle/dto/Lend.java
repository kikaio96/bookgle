package kr.or.son.bookgle.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Lend {
	private long lend_id;
	private long user_id;
	private long book_id;
	private Date lend_date;
	private Date return_date;

	private User user;
	private Book book;
}
