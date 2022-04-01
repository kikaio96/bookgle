package kr.or.son.bookgle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.son.bookgle.dto.Lend;

public interface LendMapper {
	public List<Lend> getLendList(@Param("start") int start, @Param("limit") int limit);
	public int lendCount();
	public List<Lend> getLendListByUserId(@Param("start") int start, @Param("limit") int limit, @Param("user_id") int user_id);
	public int lendCountByUserId(@Param("user_id") int user_id);
	public long insert(Lend lend);
	public int delete(@Param("lend_id") long lend_id);
	public int updateById(Lend lend);
}
