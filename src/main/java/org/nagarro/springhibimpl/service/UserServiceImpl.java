package org.nagarro.springhibimpl.service;


import java.util.List;

import org.nagarro.springhibimpl.dao.UserDao;
import org.nagarro.springhibimpl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {
		userDao.saveUser(theUser);
	}

	@Override
	@Transactional
	public boolean validate(User user) {
		return userDao.validate(user.getUsername(),user.getPassword());
	}

}




