package com.zlw.bookstore.service;

import java.util.Set;

import com.zlw.bookstore.dao.BookDAO;
import com.zlw.bookstore.dao.TradeDAO;
import com.zlw.bookstore.dao.TradeItemDAO;
import com.zlw.bookstore.dao.UserDAO;
import com.zlw.bookstore.dao.impl.BookDAOImpl;
import com.zlw.bookstore.dao.impl.TradeDAOImpl;
import com.zlw.bookstore.dao.impl.TradeItemDAOImpl;
import com.zlw.bookstore.dao.impl.UserDAOImpl;
import com.zlw.bookstore.domain.Trade;
import com.zlw.bookstore.domain.TradeItem;
import com.zlw.bookstore.domain.User;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();

	public User getUserByUserName(String username) {
		return userDAO.getUser(username);
	}

	private TradeDAO tradeDAO = new TradeDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	private BookDAO bookDAO = new BookDAOImpl();
	
	public User getUserWithTrades(String username) {

		// 调用 UserDAO 的方法获取 User 对象
		User user = userDAO.getUser(username);
		if (user == null) {
			return null;
		}

		// 调用 TradeDAO 的方法获取 Trade 的集合，把其装配为 User 的属性
		int userId = user.getUserId();
		
		// 调用 TradeDAO 的方法获取 Trade 的集合，把其装配为 User 的属性
		Set<Trade> trades = tradeDAO.getTradesWithUserId(userId);
		if (trades != null) {
			for(Trade trade: trades) {
				int tradeId = trade.getTradeId();
				Set<TradeItem> items = tradeItemDAO.getTradeItemsWithTradeId(tradeId);
				
				if (items != null) {
					for (TradeItem tradeItem : items) {
						tradeItem.setBook(bookDAO.getBook(tradeItem.getBookId()));
					}
				}
				
				trade.setItems(items);
			}
		}
		
		user.setTrades(trades);
	
		return user;
	}

}
