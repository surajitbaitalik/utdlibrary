/**
 * 
 */
package com.library.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Book;
import com.library.model.Fine;
import com.library.repository.SearchRepository;

/**
 * @author Surajit
 *
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchRepository searchRepo;

	
	public List<Book> findBooks(String bookName) {
		
		List<Book> lb=searchRepo.findBooks(bookName);
		return lb;
	}


	@Override
	public List<Fine> findFines(String cardId) {
		// TODO Auto-generated method stub
		return searchRepo.findFine(cardId);
	}

}
