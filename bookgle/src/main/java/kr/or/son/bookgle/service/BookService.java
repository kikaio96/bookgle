package kr.or.son.bookgle.service;

import java.util.List;

import kr.or.son.bookgle.dto.Book;

public interface BookService {
	public List<Book> getBookList(int start, int end);
	public Book getBook(int book_id);
	public Book addBook(Book book);
	public int deleteBook(long id);
	public void updateBook(Book book);
	public int bookCount();
}
