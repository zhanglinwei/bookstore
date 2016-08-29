package com.zlw.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Set;

import org.junit.Test;

import com.zlw.bookstore.dao.TradeDAO;
import com.zlw.bookstore.dao.impl.TradeDAOImpl;
import com.zlw.bookstore.domain.Trade;

public class TradeDAOTest {

	private TradeDAO tradeDAO = new TradeDAOImpl();
	
	@Test
	public void testInsertTrade() {
		Trade trade = new Trade();
		trade.setUserId(3);
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		tradeDAO.insert(trade);
	}

	@Test
	public void testGetTradesWithUserId() {
		Set<Trade> trades = tradeDAO.getTradesWithUserId(3);
		System.out.println(trades);
	}

}
