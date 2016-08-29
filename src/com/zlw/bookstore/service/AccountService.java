package com.zlw.bookstore.service;

import com.zlw.bookstore.dao.AccountDAO;
import com.zlw.bookstore.dao.impl.AccountDAOImpl;
import com.zlw.bookstore.domain.Account;

public class AccountService {

	private AccountDAO accountDAO = new AccountDAOImpl();
	
	public Account getAccount(int accountId){
		return accountDAO.get(accountId);
	}
	
}
