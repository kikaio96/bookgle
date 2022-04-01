package kr.or.son.bookgle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.son.bookgle.dao.BookStateDao;
import kr.or.son.bookgle.dto.BookState;
import kr.or.son.bookgle.service.BookStateService;

@Service
public class BookStateServiceImpl implements BookStateService {
	@Autowired
	BookStateDao bookStateDao;

	@Override
	@Transactional
	public BookState getBookState(int book_state_id) {
		BookState bookState = bookStateDao.selectById(book_state_id);
		return bookState;
	}

	@Override
	@Transactional(readOnly=false)
	public BookState addBookState(BookState bookState) {
		long id = bookStateDao.insert(bookState);
		bookState.setBook_id(id);
		return bookState;
	}

	@Override
	@Transactional(readOnly=false)
	public int deleteBookState(long id) {
		int deleteCount = bookStateDao.deleteById(id);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public void updateTotalCount(BookState bookState, int remain) {
		bookStateDao.updateTotalCountById(bookState, remain);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateRemain(BookState bookState, int remain) {
		bookStateDao.updateRemainById(bookState, remain);
	}

}
