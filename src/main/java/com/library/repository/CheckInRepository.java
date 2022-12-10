/**
 * 
 */
package com.library.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.FineEntity;
import com.library.model.CheckIn;

/**
 * @author Surajit
 *
 */
@Repository("checkinRepo")
@Transactional
public class CheckInRepository {
	
@PersistenceContext	
private EntityManager em;	



public String proceedCheckIn(CheckIn checkin)
{
	List<String> isbnlist=new ArrayList<>();
	isbnlist.add(checkin.getIsbn1());
	isbnlist.add(checkin.getIsbn2());
	isbnlist.add(checkin.getIsbn3());
	//first update book table make available =y
	
	Query queryBook=em.createNativeQuery("update book set availability='Y' where isbn in(:isbnlist)");
	queryBook.setParameter("isbnlist", isbnlist);
	queryBook.executeUpdate();
	
	//second update book loan table
	Query queryLoan=em.createNativeQuery("update book_loans set date_in=:checkinDate where isbn in(:isbnlist)");
	queryLoan.setParameter("isbnlist", isbnlist);
	queryLoan.setParameter("checkinDate", new Date());
	queryLoan.executeUpdate();
	//insert into fine table
	Query querylid=em.createNativeQuery("SELECT loan_id,DATEDIFF(:currentDate,due_date) as pending_since from book_loans where card_id=:card_id and isbn in(:isbn) having pending_since>0");
	querylid.setParameter("card_id", checkin.getCard_id());
	querylid.setParameter("isbn", isbnlist);
	querylid.setParameter("currentDate", new Date());
	List<Object[]> rs=querylid.getResultList();
	if(!rs.isEmpty() && rs!=null)
	{
		for(Object[] obj:rs)
		{
			String loan_id=obj[0]+"";
			String dayCount=obj[1]+"";
			long l_id=Long.parseLong(loan_id);
			double daydouble=Double.parseDouble(dayCount);
			double fineAmount=0.25 *daydouble;
			FineEntity fineent=new FineEntity();
			fineent.setFine_amt(fineAmount);
			fineent.setLoan_id(l_id);
			em.merge(fineent);
			
			
		}
		
		
		
	}
	
	
	
	String msg="Successfully checked-in";
	return msg;
}
}
