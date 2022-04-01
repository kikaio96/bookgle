package kr.or.son.bookgle.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.son.bookgle.dto.Lend;

@Repository
public class LendDao {
	@Autowired
	LendMapper lendMapper;
	
	public LendDao(DataSource dataSource) {}
	
	public long insert(Lend lend) {
		lendMapper.insert(lend);
		return lend.getLend_id();
	}
	
	public List<Lend> select(int start, int limit) {
    	return lendMapper.getLendList(start, limit);
	}
	
	public int lendCount() {
		return lendMapper.lendCount();
	}
	
	public List<Lend> select(int start, int limit, long user_id) {
		return lendMapper.getLendListByUserId(start, limit, (int)user_id);
	}
	
	public int lendCount(long user_id) {
		return lendMapper.lendCountByUserId((int)user_id);
	}
	
	public int deleteById(long id) {
		return lendMapper.delete(id);
	}
	
	public void updateById(Lend lend) {
		lendMapper.updateById(lend);
	}
}