package kr.or.son.bookgle.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.son.bookgle.dto.User;

@Repository
public class UserDao {
	@Autowired
	UserMapper userMapper;

    public UserDao(DataSource dataSource) {}
    
    public List<User> select(int start, int limit) {
    	return userMapper.getUserList(start, limit);
    }
    
    public User select(long id) {
    	return userMapper.getUser(id);
    }
    
    public User select(String email, String password) {
    	return userMapper.login(email, password);
    }

	public long insert(User user) {
		userMapper.insert(user);
		return user.getUser_id();
	}
	
	public int deleteById(long id) {
		return userMapper.delete(id);
	}
	
	public void updateById(User user) {
		userMapper.updateById(user);
	}
	
	public void updateByAdmin(User user) {
		userMapper.updateByAdmin(user);
	}
	
	public int userCount() {
		return userMapper.userCount();
	}
}