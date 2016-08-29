package com.zlw.bookstore.web;

import java.sql.Connection;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class ConnectionContext {

	private ConnectionContext() {}
	
	private static ConnectionContext instance = new ConnectionContext();
	
	public static ConnectionContext getInstance() {
		return instance;
	}
	
	private ThreadLocal<Connection> connectionTreadLocal = new ThreadLocal<>();
	
	public void bind(Connection connection) {
		connectionTreadLocal.set(connection);
	}
	
	public Connection get() {
		return connectionTreadLocal.get();
	}
	
	public void remove() {
		connectionTreadLocal.remove();
	}
	
}
