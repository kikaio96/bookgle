package kr.or.son.bookgle.service;

import java.util.List;

import kr.or.son.bookgle.dto.Lend;

public interface LendService {
	public List<Lend> getLendList(int start, int limit);
	public int lendCount();
	public List<Lend> getLendList(int start, int limit, long user_id);
	public int lendCount(long user_id);
	public Lend addLend(Lend lend) throws Exception;
	public int deleteLend(long lendId, long bookId);
	public void updateLend(Lend lend);
}
