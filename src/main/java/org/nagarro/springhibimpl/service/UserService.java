package org.nagarro.springhibimpl.service;

import java.util.List;

import org.nagarro.springhibimpl.entity.User;

public interface UserService {
	public List<User> getUsers();

	public void saveUser(User theUser);

	public boolean validate(User user);

}
