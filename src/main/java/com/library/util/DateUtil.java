/**
 * 
 */
package com.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Surajit
 *
 */
public final class DateUtil {
	
	
	public static Date createDueDate()
	{
		Date duedate=null;
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, 14);
		String fdue=sdf.format(cal.getTime());
		try {
			duedate=sdf.parse(fdue);
			System.out.println("due date is>>>"+duedate);
		} catch (ParseException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return duedate;
		
	}

}
