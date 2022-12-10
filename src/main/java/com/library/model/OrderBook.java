/**
 * 
 */
package com.library.model;

import java.util.List;

/**
 * @author Surajit
 *
 */
public class OrderBook {
	private String ssn;
	private List<Book> booklist;
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public List<Book> getBooklist() {
		return booklist;
	}
	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}
	@Override
	public String toString() {
		return "OrderBook [ssn=" + ssn + ", booklist=" + booklist + "]";
	}

}
