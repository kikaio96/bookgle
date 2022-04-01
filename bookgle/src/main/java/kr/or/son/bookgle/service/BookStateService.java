package kr.or.son.bookgle.service;

import kr.or.son.bookgle.dto.BookState;

public interface BookStateService {
	public BookState getBookState(int book_state_id);
	public BookState addBookState(BookState bookState);
	public int deleteBookState(long id);
	public void updateTotalCount(BookState bookState, int remain);
	public void updateRemain(BookState bookState, int remain);
}
