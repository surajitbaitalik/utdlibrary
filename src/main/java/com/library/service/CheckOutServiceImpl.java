/**
 * 
 */
package com.library.service;

import java.util.Set;

import com.library.model.CheckOut;
import com.library.repository.CheckOutRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Surajit
 *
 */
@Service("checkoutService")
public class CheckOutServiceImpl implements CheckOutService {
	
	@Autowired
	private CheckOutRepo checkoutRepo;

	
	
	@Override
	public CheckOut findBooks(Set<String> isbnSet) {
		CheckOut chkout=checkoutRepo.findBooks(isbnSet);
		return chkout;
		
	}



	@Override
	public String completeOrder(CheckOut chkout) {
		String status=checkoutRepo.completeOrder(chkout);
		return status;
	}
	
	@Override
	public String payFine(Set<String> set) {
		return checkoutRepo.payFine(set);
	}

	
	
	
}
