package com.zlw.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

	private Map<Integer, ShoppingCartItem> books = new HashMap<>();

	/**
	 * 修改指定购物项的数量
	 */
	public void updateItemQuantity(Integer id, int quantity) {
		ShoppingCartItem shoppingCarItem = books.get(id);
		if (shoppingCarItem != null) {
			shoppingCarItem.setQuantity(quantity);
		}
	}

	/**
	 * 移除指定的购物项
	 * 
	 * @param id
	 */
	public void removeItem(Integer id) {
		books.remove(id);
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		books.clear();
	}

	/**
	 * 返回购物车是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return books.isEmpty();
	}

	/**
	 * 获取购物车中所有的商品的总钱数
	 * 
	 * @return
	 */
	public float getTotalMoney() {
		float total = 0;
		for (ShoppingCartItem shoppingCarItem : getItems()) {
			total += shoppingCarItem.getItemMoney();
		}
		return total;
	}

	/**
	 * 获取购物车中的所有的 ShoppingCartItem 组成的集合
	 * 
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems() {
		return books.values();
	}

	/**
	 * 返回购物车中商品的总数量
	 * 
	 * @return
	 */
	public int getBookNumber() {
		int total = 0;
		for (ShoppingCartItem shoppingCarItem : books.values()) {
			total += shoppingCarItem.getQuantity();
		}
		return total;
	}

	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}

	/**
	 * 检验购物车中是否有 id 指定的商品
	 * 
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id) {
		return books.containsKey(id);
	}

	/**
	 * 向购物车中添加一件商品
	 * 
	 * @param book
	 */
	public void addBook(Book book) {
		// 1. 检查购物车中有没有该商品，若有，则使其数量 +1，若没有，
		// 新创建其对应的ShoppingCartItem，并把其加入达到 books 中
		ShoppingCartItem shoppingCarItem = books.get(book.getId());
		if (shoppingCarItem == null) {
			shoppingCarItem = new ShoppingCartItem(book);
			books.put(book.getId(), shoppingCarItem);
		} else {
			shoppingCarItem.increment();
		}
	}

}
