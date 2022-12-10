/**
 * 
 */
package com.library.model;

import java.util.List;

/**
 * @author Surajit
 *
 */
public class CheckOut {
	private String cardId;
	private List<Book> listBooks;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public List<Book> getListBooks() {
		return listBooks;
	}
	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}
	@Override
	public String toString() {
		return "CheckOut [cardId=" + cardId + ", listBooks=" + listBooks + "]";
	}
	
	public int size()
	{
		return listBooks.size();
	}
	

}
