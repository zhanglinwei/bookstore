package com.zlw.bookstore.servlet;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalLookupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zlw.bookstore.domain.User;
import com.zlw.bookstore.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取 username 请求参数值
		String username = request.getParameter("username");

		// 调用 UserService 的 getUser(userName) 获取 User 对象：要求 trades 是装配好的， 而且一个
		// Trade 对象
		// 的 items 也被装配好
		User user = userService.getUserWithTrades(username);

		// 把 User 对象放入 Request 中
		if (user == null) {
			response.sendRedirect(request.getServletPath() + "/error-1.jsp");
			return;
		}

		request.setAttribute("user", user);

		// 转发页面到 /WEB-INF/pages/trades.jsp
		request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);
		

	}

}
