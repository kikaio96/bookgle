package kr.or.son.bookgle.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.son.bookgle.dto.Book;

@Repository
public class BookDao {
	@Autowired
	BookMapper bookMapper;

    public BookDao(DataSource dataSource) {}
    
    public List<Book> select(int start, int limit) {
    	List<Book> bookList = bookMapper.getBookList(start, limit);
    	return bookList;
    }
    
    public Book select(int book_id) {
    	Book book = bookMapper.getBook(book_id);
    	return book;
    }

	public long insert(Book book) {
		bookMapper.insert(book);
		return book.getBook_id();
	}
	
	public int deleteById(long id) {
		return bookMapper.delete(id);
	}
	
	public void updateById(Book book) {
		bookMapper.updateById(book);
	}
	
	public int bookCount() {
		return bookMapper.bookCount();
	}
	
}
