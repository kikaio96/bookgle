package kr.or.son.bookgle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.son.bookgle.dao.BookDao;
import kr.or.son.bookgle.dto.Book;
import kr.or.son.bookgle.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDao bookDao;

	@Override
	@Transactional
	public List<Book> getBookList(int start, int end) {
		List<Book> list = bookDao.select(start, end);
		return list;
	}

	@Override
	@Transactional(readOnly=false)
	public Book addBook(Book book) {
		long id = bookDao.insert(book);
		book.setBook_id(id);
		return book;
	}

	@Override
	@Transactional
	public Book getBook(int book_id) {
		Book book = bookDao.select(book_id);
		return book;
	}

	@Override
	@Transactional(readOnly=false)
	public int deleteBook(long id) {
		int deleteCount = bookDao.deleteById(id);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public void updateBook(Book book) {
		bookDao.updateById(book);
	}

	@Override
	@Transactional
	public int bookCount() {
		return bookDao.bookCount();
	}
}
