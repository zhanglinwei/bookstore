package com.zlw.bookstore.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import com.zlw.bookstore.dao.TradeDAO;
import com.zlw.bookstore.domain.Trade;

public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO {

	@Override
	public void insert(Trade trade) {
		String sql = "INSERT INTO trade (userid, tradeTime) "
				+ "VALUES (? ,?)";
		long tradeId = insert(sql, trade.getUserId(), trade.getTradeTime());
		trade.setTradeId((int)tradeId);
	}

	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {
		String sql = "SELECT tradeId, userId, tradeTime "
				+ "FROM trade "
				+ "WHERE userId = ? "
				+ "ORDER BY tradeTime DESC";
		return new LinkedHashSet<Trade>(queryForList(sql, userId));
	}

}
