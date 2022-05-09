package org.nagarro.springhibimpl.dao;

import java.util.List;

import org.nagarro.springhibimpl.entity.User;


public interface UserDao {

	public List<User> getUsers();

	public void saveUser(User user);

	public boolean validate(String theId,String thePassword);


	
}