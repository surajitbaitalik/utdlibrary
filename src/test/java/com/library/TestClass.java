/**
 * 
 */
package com.library;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Surajit
 *
 */
public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String str="";
		
		Set<String> hs=new HashSet();
		hs.add("one");
		hs.add("two");
		hs.add("three");
		/*String[] arrS=hs.toArray(new String[hs.size()]);
		for(int i=0;i<arrS.length;i++)
		{
			if(i==arrS.length-1)
			{
				str=str+arrS[i];
				
			}
			else {
				str=str+arrS[i]+",";
			}
		}
		
		System.out.println(str);*/
		
		List<String> list = hs.stream().collect(Collectors.toList());
		System.out.println(list);
		System.out.println(new Date());
		
		
	}

}
