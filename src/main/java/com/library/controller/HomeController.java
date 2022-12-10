/**
 * 
 */
package com.library.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.library.entity.EmployeeEntity;
import com.library.model.Book;
import com.library.model.Borrower;
import com.library.model.CheckIn;
import com.library.model.CheckOut;
import com.library.model.CheckOutObj;
import com.library.model.Employee;
import com.library.model.Fine;
import com.library.repository.EmployeeRepository;
import com.library.service.BorrowerService;
import com.library.service.CheckOutService;
import com.library.service.CheckinService;
import com.library.service.SearchService;










/**
 * @author Surajit
 *
 */
@RestController(value="/library")
public class HomeController {

 @Autowired
 private  EmployeeRepository empRepository;
 
@Autowired 
private BorrowerService borrowerService;

@Autowired
private SearchService searchService;
@Autowired
private CheckOutService  checkoutService;
@Autowired
private CheckinService checkinService;
	
	

	@RequestMapping(value="/")
	public ModelAndView index()
	{
		ModelAndView mv=new ModelAndView();
	    //mv.addObject("borrower", new Borrower());
		mv.setViewName("home/home");
		return mv;
	}
	
	@RequestMapping(value="/addborrow")
	public ModelAndView requestBorrow()
	{
		ModelAndView mv=new ModelAndView();
	    //mv.addObject("borrower", new Borrower());
		mv.setViewName("borrower/addborrower");
		return mv;
	}
	
	@RequestMapping(value="/searchbook")
	public ModelAndView searchBook()
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("book", new Book());
		mv.setViewName("search/search");
		return mv;
		
	}
	@RequestMapping(value="/findbooks" , method=RequestMethod.GET)
	public List<Book> findBooks(@RequestParam String bookName)
	{
		
		
		List<Book> resultBook=searchService.findBooks(bookName);
		
		
		
		return resultBook;
	
	}
	
	
	
	@RequestMapping(value="/checkout" , method=RequestMethod.POST)
	public ModelAndView checkout(@ModelAttribute CheckOutObj checkoutobj,HttpSession session)
	{
		ModelAndView mv =new ModelAndView();
		String[] isbnarr=checkoutobj.getValues().split(",");
		Set<String> hs=new HashSet<>();
		for(String str:isbnarr)
		{
			hs.add(str);
		}
		if(hs.size()>3)
		{
			mv.setViewName("success/success");
			mv.addObject("resultStr","You are allowed to select Maximum 3 books");
		
		return mv;
			
		}
		CheckOut chkout=checkoutService.findBooks(hs);
		
		mv.addObject("checkoutdata", chkout);
		mv.setViewName("checkout/checkout");
		session.setAttribute("checkoutbook", chkout);
		return mv;
	
	}
	
	@RequestMapping(value="/checkin" ,method=RequestMethod.GET)
	public ModelAndView checkin(HttpSession session)
	{
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("checkIn/checkIn");
		
		
		return mv;
		
		
	}
	@RequestMapping(value="/checkinUpdate" ,method=RequestMethod.POST)
	public ModelAndView updateCheckIn(@ModelAttribute("checkin")CheckIn checkin)
	{
		ModelAndView mv=new ModelAndView();
		
		
		String msg=checkinService.completeCheckIn(checkin);
		mv.setViewName("success/success");
		mv.addObject("resultStr",msg);
		return mv;
		
	}

@RequestMapping(value="/orderBook" ,method=RequestMethod.POST)
public ModelAndView orderBook(@Valid @ModelAttribute("checkoutdata") CheckOut chkout,HttpSession session)
{
	ModelAndView mv=new ModelAndView();
	System.out.println("card no>>>"+chkout.getCardId());
	CheckOut stored=(CheckOut) session.getAttribute("checkoutbook");
	stored.setCardId(chkout.getCardId());
	
	String status=checkoutService.completeOrder(stored);
	mv.setViewName("success/success");
	mv.addObject("resultStr",status);
	
	return mv;
}
	
	
	@RequestMapping(value="/addBorrower")
	public ModelAndView addBorrower(@Valid @ModelAttribute("borrower")Borrower borrower)
	{
			String result=borrowerService.addBorrower(borrower);
			
			ModelAndView mv=new ModelAndView();
		
			
			
				mv.setViewName("success/success");
				mv.addObject("resultStr",result);
			
			return mv;
			
			
		
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("employee")Employee employee, 
      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println("Request came to add employee method");
        empRepository.save(new EmployeeEntity(employee.getFname(),employee.getLname(),employee.getDesc()));
        
        
        return "success";
    }
	
	@RequestMapping(value="/fines" , method=RequestMethod.GET)
	public ModelAndView fines()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Fine/fine");
		return mv;
	}
	
	@RequestMapping(value="/findfine" ,method=RequestMethod.GET)
	public List<Fine> getFine(@RequestParam String card_id)
	{
		List<Fine> lisfine=searchService.findFines(card_id);
		System.out.println(lisfine);
		return lisfine;
		
	}
	
	
	@RequestMapping(value="/payFine",method=RequestMethod.POST)
	public ModelAndView payFine(@ModelAttribute CheckOutObj checkoutobj,HttpSession session)
	{
		ModelAndView mv =new ModelAndView();
		String[] isbnarr=checkoutobj.getValues().split(",");
		Set<String> hs=new HashSet<>();
		for(String str:isbnarr)
		{
			hs.add(str);
		}
		
		String status=checkoutService.payFine(hs);
		
		mv.setViewName("success/success");
		mv.addObject("resultStr",status);
		return mv;
	
	}
	
}
