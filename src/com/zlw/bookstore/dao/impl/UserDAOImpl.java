package com.zlw.bookstore.dao.impl;

import com.zlw.bookstore.dao.UserDAO;
import com.zlw.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sql = "SELECT userId, username, accountId "
				+ "FROM userinfo WHERE username = ?";
		
		return query(sql, username);
	}

}
