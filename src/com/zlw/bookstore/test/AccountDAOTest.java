package com.zlw.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zlw.bookstore.dao.AccountDAO;
import com.zlw.bookstore.dao.impl.AccountDAOImpl;
import com.zlw.bookstore.domain.Account;

public class AccountDAOTest {

	AccountDAO accountDao = new AccountDAOImpl();
	
	@Test
	public void testGet() {
		Account account = accountDao.get(1);
		System.out.println(account.getBalance());	
		
	}

	@Test
	public void testUpdateBalance() {
		accountDao.updateBalance(1, 300);
	}

}
