package com.zlw.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zlw.bookstore.dao.UserDAO;
import com.zlw.bookstore.dao.impl.UserDAOImpl;
import com.zlw.bookstore.domain.User;

public class UserDAOTest {

	private UserDAO userDAO = new UserDAOImpl();
	
	@Test
	public void testGetUser() {
		User user =  userDAO.getUser("Tom");
		System.out.println(user);
	}

}
