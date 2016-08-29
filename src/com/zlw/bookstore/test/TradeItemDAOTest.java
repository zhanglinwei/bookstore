package com.zlw.bookstore.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import com.zlw.bookstore.dao.TradeItemDAO;
import com.zlw.bookstore.dao.impl.TradeDAOImpl;
import com.zlw.bookstore.dao.impl.TradeItemDAOImpl;
import com.zlw.bookstore.domain.TradeItem;

public class TradeItemDAOTest {

	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	
	@Test
	public void testBatchSave() {
		Collection<TradeItem> items = new ArrayList<>();
		
		items.add(new TradeItem(null, 1, 10, 15));
		items.add(new TradeItem(null, 2, 20, 15));
		items.add(new TradeItem(null, 3, 30, 15));
		items.add(new TradeItem(null, 4, 40, 15));
		
		tradeItemDAO.batchSave(items);
	}

	@Test
	public void testGetTradeItemsWithTradeId() {
		Set<TradeItem> items = tradeItemDAO.getTradeItemsWithTradeId(12);
		System.out.println(items);
	}

}
