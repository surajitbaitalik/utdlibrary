/**
 * 
 */
package com.library.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.BorrowerEntity;
import com.library.entity.FineEntity;
import com.library.model.Book;
import com.library.model.Borrower;
import com.library.model.Fine;



/**
 * @author Surajit
 *
 */
@Repository("searchRepo")
@Transactional
public class SearchRepository {
	

/*@Value("${app.findbook}")
String findbookQr;*/
	
	
@PersistenceContext	
private EntityManager em;	




@SuppressWarnings("unchecked")
public List<Book> findBooks(String bookName)
{
	List<Book> lb=new ArrayList<>();
	Map<String,Book> bookmap=new HashMap<>();
	String[] bkname=bookName.split(" ");
	
	for(String bn:bkname) {
	String squery="SELECT book.title AS title, book.isbn AS isbn, authors.name AS author, CASE WHEN book.availability = 'Y' THEN 'available' WHEN book.availability = 'N' THEN 'checked-out' END AS availability FROM book,authors,book_authors WHERE book.isbn= book_authors.isbn and book_authors.author_id=authors.author_id and book.title LIKE ('%"+bn+"%') union SELECT book.title AS title, book.isbn AS isbn, authors.name AS author, CASE WHEN book.availability = 'Y' THEN 'available' WHEN book.availability = 'N' THEN 'checked-out' END AS availability FROM book,authors,book_authors WHERE book.isbn= book_authors.isbn and book_authors.author_id=authors.author_id and authors.name LIKE ('%"+bn+"%') union SELECT book.title AS title, book.isbn AS isbn, authors.name AS author, CASE WHEN book.availability = 'Y' THEN 'available' WHEN book.availability = 'N' THEN 'checked-out' END AS availability FROM book,authors,book_authors WHERE book.isbn= book_authors.isbn and book_authors.author_id=authors.author_id and book.isbn LIKE ('%"+bn+"%')";
	Query query=em.createNativeQuery(squery);
	
	List<Object[]> books = query.getResultList();
	
	for(Object[] bo:books)
	{
		Book book=new Book();
		book.setName(bo[0]+"");
		book.setAvailability(bo[3]+"");
		String isbn=bo[1]+"";
		String author=bo[2]+"";
		book.setIsbn(isbn);
		book.setAuthor(author);
		if(bookmap.containsKey(isbn))
		{
			Book b=bookmap.get(isbn);
			String auth=b.getAuthor();
			auth=auth+","+author;
			b.setAuthor(auth);
			bookmap.put(isbn, b);
			
		}else {
			bookmap.put(isbn, book);
		}
		
		
	}
	}
	for(String keys:bookmap.keySet())
	{
		lb.add(bookmap.get(keys));
	}
	
	return lb;
}


@SuppressWarnings("unchecked")
public List<Fine> findFine(String cardId)
{
	List<Fine> lf=new ArrayList<>();
	//insert into fine table if it is not checked in and more than 14 days out
	Query infine=em.createNativeQuery("SELECT loan_id,DATEDIFF(:currentDate,due_date) as pending_since from book_loans where card_id=:cardId having pending_since>0");
	infine.setParameter("cardId", cardId);
	infine.setParameter("currentDate", new Date());
	List<Object[]> fl = infine.getResultList();
	for(Object[] obj:fl)
	{
		FineEntity fn =new FineEntity();
		long loan_id=Long.parseLong(obj[0]+"");
		fn.setLoan_id(loan_id);
		double fine_amount=Double.parseDouble(obj[1]+"")*0.25;
		fn.setFine_amt(fine_amount);
		em.merge(fn);
	}
	
	
	
	Query query=em.createNativeQuery("select bl.loan_id,bl.isbn,bl.date_out as Order_date,bl.date_in as submission_date,f.fine_amt as fine from fines f,book_loans bl where bl.loan_id=f.loan_id and f.paid is null and bl.card_id=:cardId");
	query.setParameter("cardId", cardId);
	List<Object[]> fines = query.getResultList();
	
	for(Object[] fine:fines)
	{
		Fine fn =new Fine();
		fn.setLoan_id(fine[0]+"");
		fn.setIsbn(fine[1]+"");
		fn.setOrder_Date(fine[2]+"");
		fn.setSubmission_Date(fine[3]+"");
		fn.setFine(fine[4]+"");
		lf.add(fn);
		
	}
	
	
	return lf;
	
}




	
public long findBySSN(Borrower borrower) {

	String query="select borrower_id  from borrowers where ssn=:ssn";
	Query quer=em.createNativeQuery(query);
	quer.setParameter("ssn", borrower.getSsn());
	List<BigInteger> idList=quer.getResultList();
	long id;
	if(!idList.isEmpty())
	{
		id=idList.get(0).longValue();
		
	}else {
		id=0;
	}
	
	
	
	
	
	
	return id;
	
}




}
