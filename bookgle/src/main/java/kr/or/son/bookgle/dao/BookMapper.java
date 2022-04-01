package kr.or.son.bookgle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.son.bookgle.dto.Book;

public interface BookMapper {
	public List<Book> getBookList(@Param("start") int start, @Param("limit") int param);
	public Book getBook(@Param("book_id") long book_id);
	public long insert(Book book);
	public int delete(@Param("book_id") long book_id);
	public int updateById(Book book);
	public int bookCount();
}
