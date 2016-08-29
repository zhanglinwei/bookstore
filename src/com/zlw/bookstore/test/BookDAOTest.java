package com.zlw.bookstore.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.zlw.bookstore.dao.BookDAO;
import com.zlw.bookstore.dao.impl.BookDAOImpl;
import com.zlw.bookstore.domain.Book;
import com.zlw.bookstore.domain.ShoppingCartItem;
import com.zlw.bookstore.web.CriteriaBook;
import com.zlw.bookstore.web.Page;

public class BookDAOTest {

	private BookDAO bookDAO = new BookDAOImpl();
	
	@Test
	public void testGetBook() {
		Book book = bookDAO.getBook(5);
		System.out.println(book);
	}

	@Test
	public void testGetPage() {
		CriteriaBook  cb = new CriteriaBook(50, 60, 90);
		Page<Book> page = bookDAO.getPage(cb);
		
		System.out.println("pageNo: " + page.getPageNo());
		System.out.println("totalPageNumber: " + page.getTotalPageNumber());
		System.out.println("list: " + page.getList());
		System.out.println("prevPage: " + page.getPrevPage());
		System.out.println("nextPage: " + page.getNextPage());  
	}

	@Test
	public void testGetTotalBookNumber() {
		int storeNumber = bookDAO.getStoreNumber(5);
		System.out.println(storeNumber);
	}

	@Test
	public void testGetPageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStoreNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchUpdateStoreNumberAndSalesAmount(){
		Collection<ShoppingCartItem> items = new ArrayList<>();
		
		Book book = bookDAO.getBook(1);
		ShoppingCartItem sci = new ShoppingCartItem(book);
		sci.setQuantity(10);
		items.add(sci);
		
		book = bookDAO.getBook(2);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(11);
		items.add(sci);
		
		book = bookDAO.getBook(3);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(12);
		items.add(sci);
		
		book = bookDAO.getBook(3);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(14);
		items.add(sci);
		
		book = bookDAO.getBook(5);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(15);
		items.add(sci);
		
		bookDAO.batchUpdateStoreNumberAndSalesAmount(items);
	}
	
}
