package kr.or.son.bookgle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.son.bookgle.dao.BookStateDao;
import kr.or.son.bookgle.dao.LendDao;
import kr.or.son.bookgle.dto.BookState;
import kr.or.son.bookgle.dto.Lend;
import kr.or.son.bookgle.service.LendService;

@Service
public class LendServiceImpl implements LendService {
	@Autowired
	LendDao lendDao;
	
	@Autowired
	BookStateDao bookStateDao;
	
	@Override
	@Transactional
	public List<Lend> getLendList(int start, int limit) {
		List<Lend> list = lendDao.select(start, limit);
		return list;
	}

	@Override
	@Transactional
	public int lendCount() {
		return lendDao.lendCount();
	}

	@Override
	@Transactional
	public List<Lend> getLendList(int start, int limit, long user_id) {
		List<Lend> list = lendDao.select(start, limit, user_id);
		return list;
	}

	@Override
	@Transactional
	public int lendCount(long user_id) {
		return lendDao.lendCount(user_id);
	}

	@Override
	@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
	public Lend addLend(Lend lend) throws Exception {
		BookState bookState = bookStateDao.selectByBookId(lend.getBook_id());
		int remain = bookState.getRemain();
		if(remain == 0) {
			throw new Exception("대여 가능 수량 부족");
		} else {
			bookStateDao.updateRemainById(bookState, -1);
			
			long id = lendDao.insert(lend);
			
			lend.setLend_id(id);
			return lend;
		}
	}

	@Override
	@Transactional(readOnly=false)
	public int deleteLend(long lendId, long bookId) {
		BookState bookState = bookStateDao.selectByBookId(bookId);
		
		bookStateDao.updateRemainById(bookState, +1);
		
		int deleteCount = lendDao.deleteById(lendId);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public void updateLend(Lend lend) {
		lendDao.updateById(lend);
	}

}
