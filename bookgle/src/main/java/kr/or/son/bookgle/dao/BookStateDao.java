package kr.or.son.bookgle.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.son.bookgle.dto.BookState;

@Repository
public class BookStateDao {
	@Autowired
	BookStateMapper bookStateMapper;

	public BookStateDao(DataSource dataSource) {}

	public BookState selectById(int book_state_id) {
		return bookStateMapper.getBookStateById(book_state_id);
	}

	public BookState selectByBookId(long book_id) {
		return bookStateMapper.getBookStateByBookId(book_id);
	}

	public long insert(BookState bookState) {
		bookStateMapper.insert(bookState);
		return bookState.getBook_state_id();
	}

	public int deleteById(long id) {
		return bookStateMapper.delete(id);
	}

	public void updateTotalCountById(BookState bookState, int remain) {
		bookStateMapper.updateTotalCountById(bookState, remain);
	}

	public void updateRemainById(BookState bookState, int remain) {
		bookStateMapper.updateRemainById(bookState, remain);
	}

}
