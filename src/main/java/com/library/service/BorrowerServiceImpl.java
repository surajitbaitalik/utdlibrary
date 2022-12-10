/**
 * 
 */
package com.library.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.BorrowerEntity;
import com.library.model.Borrower;
import com.library.repository.BorrowerRepository;
import com.library.repository.SearchRepository;

/**
 * @author Surajit
 *
 */
@Service("borrowerService")
public class BorrowerServiceImpl implements BorrowerService {
 
	@Autowired
	private BorrowerRepository borrowerRepository;
	@Autowired
	private SearchRepository searchRepo;
	
	private EntityManager em;
	
	
	
	@Override
	public String addBorrower(Borrower borrower) {
	   String msg="";	
	   long id=searchRepo.findBySSN(borrower);
	   if(0!=id)
	   {
		  msg="You are an existing member"+","+"your card_id# "+id; 
	   }else {
		BorrowerEntity borrowE=	borrowerRepository.save(new BorrowerEntity(borrower.getSsn(),borrower.getFirst_name(),borrower.getLast_name(),borrower.getEmail(),borrower.getAddress(),borrower.getCity(),borrower.getState(),borrower.getContactNumber()));
		
		
		msg="You are successfully added"+","+"your card_id# "+borrowE.getId();
	   
	}

	   return msg;
	
	}
}
