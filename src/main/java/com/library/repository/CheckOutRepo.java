/**
 * 
 */
package com.library.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.BookLoan;
import com.library.model.Book;
import com.library.model.CheckOut;
import com.library.service.CheckOutService;
import com.library.util.DateUtil;

/**
 * @author Surajit
 *
 */
@Repository("checkoutRepo")
@Transactional
public class CheckOutRepo {
	
	@PersistenceContext	
	private EntityManager em;
	
	
	public CheckOut findBooks(Set<String> isbn)
	{
		CheckOut chkout=new CheckOut();
		List<Book> lb=new ArrayList<>();
		Map<String,Book> bookmap=new HashMap<>();
		String[] arrS=isbn.toArray(new String[isbn.size()]);
		List<String> isbnNames =Arrays.asList(arrS);
		String[] isbnArray=isbn.toArray(new String[isbn.size()]);
		String findbook="SELECT book.title AS title, book.isbn AS isbn, authors.name AS author FROM book,authors,book_authors WHERE book.isbn= book_authors.isbn and book_authors.author_id=authors.author_id and book.isbn in (:isbnNames)";
		Query query=em.createNativeQuery(findbook);
		query.setParameter("isbnNames", isbnNames);
		List<Object[]> rs=query.getResultList();
		for(Object[] bo:rs)
		{
			Book book=new Book();
			book.setName(bo[0]+"");
			String isb=bo[1]+"";
			String author=bo[2]+"";
			book.setIsbn(isb);
			book.setAuthor(author);
			if(bookmap.containsKey(isb))
			{
				Book bk=bookmap.get(isb);
				String auth=bk.getAuthor();
				auth=auth+","+author;
				bk.setAuthor(auth);
				bookmap.put(isb, bk);
				
			}else {
				bookmap.put(isb, book);
			}
		}
		for(String keys:bookmap.keySet())
		{
			lb.add(bookmap.get(keys));
		}
		
		chkout.setListBooks(lb);
		return chkout;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public String completeOrder(CheckOut chkout)
	{
		
		//check i the card is valid or not
		String msg="";
		//1st check if the card id holder has 3 books or not
		Query cardCheck=em.createNativeQuery("select * from borrowers where borrower_id=:card_id");
		cardCheck.setParameter("card_id", chkout.getCardId());
		List<Object[]> cardList=cardCheck.getResultList();
		if(cardList.isEmpty() || cardList.size()==0)
		{
			msg="Please enter a valid card No";
		}else {
			//check if the loan limit exceeds or not 
			Query loanLimit=em.createNativeQuery("select count(*) as total_book from book_loans where card_id=:card_id and date_in is null");
			loanLimit.setParameter("card_id", chkout.getCardId());

			BigInteger countNo=(BigInteger) loanLimit.getSingleResult();
			int countint=countNo.intValue();
			List<Book> booklist=chkout.getListBooks();
			System.out.println("size of Booklist>>>"+booklist.size());
			if(countint+chkout.size()<=3)
			{
				//insert book_loan table
				
				for(Book book:chkout.getListBooks())
				{
					BookLoan bl=new BookLoan();
					bl.setCard_id(chkout.getCardId());
					bl.setIsbn(book.getIsbn());
					bl.setDate_out(new Date());
					bl.setDue_date(DateUtil.createDueDate());
					em.persist(bl);
					//now update the books table flag
					Query updatebook=em.createNativeQuery("update book set availability='N' where isbn=:isbn");
					updatebook.setParameter("isbn", book.getIsbn());
					int status=updatebook.executeUpdate();
					
					
				}
				
				msg="Your order is successful";
				
				
			}else {
				msg="You have exceed the limit of 3 books";
			}
			
		}
		;
		
		
		
		//2nd if does not cross the limit then insert into book loan table
		
		//respond with success message
		
		
		return msg;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public String payFine(Set<String> set)
	{
		String msg="";
		
		//check if the book is returned or not
		Query payq=em.createNativeQuery("select * from book_loans where loan_id in(:loanId) and date_in is null");
		payq.setParameter("loanId", set);
		List<Object[]> rs=payq.getResultList();
		if(rs.size()!=0)
		{
			msg="You are not allowed to pay the fine";
		}else {
		
		
		Query fineQuery=em.createNativeQuery("update fines set paid='127' where loan_id in(:loanId)");
		fineQuery.setParameter("loanId", set);
		int status=fineQuery.executeUpdate();
		
		 msg="Thank you! For Paying the selected Fine";
		}
		return msg;
	}
	
	
	

	
	
	
}
