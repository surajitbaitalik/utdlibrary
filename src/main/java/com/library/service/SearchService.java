/**
 * 
 */
package com.library.service;


import java.util.List;

import com.library.model.Book;
import com.library.model.Fine;

/**
 * @author Surajit
 *
 */
public interface SearchService {
	
	public List<Book> findBooks(String bookName);
	public List<Fine> findFines(String cardId);

}
