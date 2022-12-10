/**
 * 
 */
package com.library.service;

import java.util.Set;

import com.library.model.CheckOut;

/**
 * @author Surajit
 *
 */
public interface CheckOutService {

	public CheckOut findBooks(Set<String> isbnSet);
	public String completeOrder(CheckOut chkout);
	public String payFine(Set<String> set);
}
