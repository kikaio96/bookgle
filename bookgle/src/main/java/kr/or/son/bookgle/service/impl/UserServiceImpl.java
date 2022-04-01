package kr.or.son.bookgle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.son.bookgle.dao.UserDao;
import kr.or.son.bookgle.dto.User;
import kr.or.son.bookgle.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public List<User> getUserList(int start, int limit) {
		List<User> list = userDao.select(start, limit);
		return list;
	}

	@Override
	@Transactional
	public User getUser(int user_id) {
		User user = userDao.select(user_id);
		return user;
	}

	@Override
	@Transactional
	public User login(String email, String password) {
		User user = userDao.select(email, password);
		return user;
	}

	@Override
	@Transactional(readOnly=false)
	public User addUser(User user) {
		long id = userDao.insert(user);
		user.setUser_id(id);
		return user;
	}

	@Override
	@Transactional(readOnly=false)
	public int deleteUser(long id) {
		int deleteCount = userDao.deleteById(id);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly=false)
	public void updateUser(User user) {
		userDao.updateById(user);
	}

	@Override
	public void updateUserByAdmin(User user) {
		userDao.updateByAdmin(user);
	}

	@Override
	@Transactional
	public int userCount() {
		return userDao.userCount();
	}

}
