package kr.or.son.bookgle.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.son.bookgle.dto.BookState;

@Mapper
public interface BookStateMapper {
	public BookState getBookStateById(@Param("book_state_id") long book_state_id);
	public BookState getBookStateByBookId(@Param("book_id") long book_id);
	public long insert(BookState bookState);
	public int delete(@Param("book_state_id") long book_state_id);
	public void updateTotalCountById(@Param("book_state")BookState bookState, @Param("remain")int remain);
	public void updateRemainById(@Param("book_state")BookState bookState, @Param("remain")int remain);
}
