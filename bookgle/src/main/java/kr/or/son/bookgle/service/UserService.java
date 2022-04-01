package kr.or.son.bookgle.service;

import java.util.List;

import kr.or.son.bookgle.dto.User;

public interface UserService {
	public List<User> getUserList(int start, int limit);
	public User getUser(int user_id);
	public User login(String email, String password);
	public User addUser(User user);
	public int deleteUser(long id);
	public void updateUser(User user);
	public void updateUserByAdmin(User user);
	public int userCount();
}
