package com.zlw.bookstore.service;

import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.zlw.bookstore.dao.AccountDAO;
import com.zlw.bookstore.dao.BookDAO;
import com.zlw.bookstore.dao.TradeDAO;
import com.zlw.bookstore.dao.TradeItemDAO;
import com.zlw.bookstore.dao.UserDAO;
import com.zlw.bookstore.dao.impl.AccountDAOImpl;
import com.zlw.bookstore.dao.impl.BookDAOImpl;
import com.zlw.bookstore.dao.impl.TradeDAOImpl;
import com.zlw.bookstore.dao.impl.TradeItemDAOImpl;
import com.zlw.bookstore.dao.impl.UserDAOImpl;
import com.zlw.bookstore.domain.Book;
import com.zlw.bookstore.domain.ShoppingCart;
import com.zlw.bookstore.domain.ShoppingCartItem;
import com.zlw.bookstore.domain.Trade;
import com.zlw.bookstore.domain.TradeItem;
import com.zlw.bookstore.web.CriteriaBook;
import com.zlw.bookstore.web.Page;

public class BookService {

	private BookDAO bookDAO = new BookDAOImpl();

	public Page<Book> getPage(CriteriaBook criteriaBook) {
		return bookDAO.getPage(criteriaBook);
	}

	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}

	public boolean addToCart(int id, ShoppingCart shoppingCart) {
		Book book = bookDAO.getBook(id);
		if (book != null) {
			shoppingCart.addBook(book);
			return true;
		}
		return false;

	}

	public void removeItemFromShoppingCart(ShoppingCart shoppingCart, int id) {
		shoppingCart.removeItem(id);
	}

	public void clearShoppingCart(ShoppingCart shoppingCart) {
		shoppingCart.clear();
	}

	public void updateItemQuantity(ShoppingCart shoppingCart, int id, int quantity) {
		shoppingCart.updateItemQuantity(id, quantity);
	}

	
	private AccountDAO accountDAO = new AccountDAOImpl();
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	
	// 业务方法。
	public void cash(ShoppingCart shoppingCart, String username, String accountId) {
		//1. 更新 mybooks 数据表相关记录的 salesamount 和 storenumber
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		
		//2. 更新 account 数据表的 balance
		accountDAO.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());
		
		//3. 向 trade 数据表插入一条记录
		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDAO.getUser(username).getUserId());
		tradeDAO.insert(trade);
		
		//4. 向 tradeitme 数据表插入 n 条记录
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci : shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			tradeItem.setBookId(sci.getBook().getId());
			tradeItem.setQuantity(sci.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		
		//5. 清空购物车
		shoppingCart.clear();
		
	}

}
