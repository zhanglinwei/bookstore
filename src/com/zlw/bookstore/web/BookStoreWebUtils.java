package com.zlw.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zlw.bookstore.domain.ShoppingCart;

public class BookStoreWebUtils {

	/**
	 * 获取购物车对象：从 session 中获取，若 session 没有该对象， 创建一个新的购物车对象放入 session 中。 若有，直接返回
	 * 
	 * @param request
	 * @return
	 */
	public static ShoppingCart getShoppingCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("ShoppingCart", shoppingCart);
		}
		return shoppingCart;
	}

}
