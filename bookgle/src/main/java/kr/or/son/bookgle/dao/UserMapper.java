package kr.or.son.bookgle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.son.bookgle.dto.User;

@Mapper
public interface UserMapper {
	public List<User> getUserList(@Param("start") int start, @Param("limit") int param);
	public User getUser(@Param("user_id") long userId);
	public User login(@Param("email")String email, @Param("password")String password);
	public long insert(User user);
	public int delete(@Param("user_id") long userId);
	public int updateById(User user);
	public int updateByAdmin(User user);
	public int userCount();
}
