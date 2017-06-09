/**
 * Perforce control Attributes
 *
 * $Author: $ 
 * $Change: $ 
 * $Date: $ 
 * $DateTime: $ 
 * $File: $ 
 * $Header: $ 
 * $Id: $ 
 * $Revision: $ 
 *
 */


package com.brightplan.automation.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper 
{
	/**
	 * Returns the Date field from the calendar
	 * @param p_date
	 * @param p_field
	 * @return
	 */
	public static int getDateFieldUsingCalendar (Date p_date, int p_field)
	{
		Calendar c = Calendar.getInstance(); 
		c.setTime(p_date); 
		
		return c.get(p_field);

	}
	
	/**
	 * Get padded date using Calendar
	 * @param p_date
	 * @param p_field
	 * @return
	 */
	public static String getPaddedDateFieldUsingCalendar (Date p_date, int p_field)
	{
		String retVal = "";
		Calendar c = Calendar.getInstance(); 
		c.setTime(p_date); 
		
		int val = c.get(p_field);
		
		if (p_field == Calendar.MONTH) val++;
		
		if (val < 10) retVal = "0" + val;
		else retVal = "" + val;
		
		return retVal;
	}
	
	/**
	 * Get Date and add month
	 * @param pDate
	 * @param pNewMonth
	 * @return
	 */
	public static String getDateandAddMonth (Date pDate, int pNewMonth)
	{
		
		Calendar cal1 = new GregorianCalendar( getDateFieldUsingCalendar(pDate, Calendar.YEAR),  getDateFieldUsingCalendar(pDate, Calendar.MONTH), getDateFieldUsingCalendar(pDate, Calendar.DAY_OF_MONTH));
		cal1.add(Calendar.MONTH, pNewMonth);
		cal1.add(Calendar.DAY_OF_MONTH, -1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String ldtContractStDt = df.format(cal1.getTime());
		return ldtContractStDt;
	}

	
	/**
	 * Get the Date and Add Month
	 * @param pYear
	 * @param pDay
	 * @param pMonth
	 * @param pNewMonth
	 * @return
	 */
	public static String getDateAddMonth (int pYear, int pDay, int pMonth, int pNewMonth)
	{
		Calendar cal1 = new GregorianCalendar( pYear,  pMonth, pDay);
		
		cal1.add(Calendar.MONTH, pNewMonth);
		DateFormat df = new SimpleDateFormat("yy-MM-dd");
		String ldtContractStDt = df.format(cal1.getTime());
		return ldtContractStDt;
	}
	
	/**
	 * Given a format and string containing date in that format, it returns an equivalent date object after 
	 * adjusting (increment or decrement DATE, MONTH or YEAR) the date fields 
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @param p_incrementType
	 * @param p_increaseBy
	 * @return
	 */
	public static String getAsDateString (String p_dtFormat, String p_dtVal, int p_incrementType, int p_increaseBy )
	{
		return getAsDateString (p_dtFormat,getAsDate (p_dtFormat, p_dtVal, p_incrementType, p_increaseBy ));
	}
	
	/**
	 * Given a format and string containing date in that format, it returns an equivalent date object after 
	 * adjusting (increment or decrement DATE, MONTH or YEAR) the date fields 
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @param p_incrementType
	 * @param p_increaseBy
	 * @return
	 */
	public static Date getAsDate (String p_dtFormat, String p_dtVal, int p_incrementType, int p_increaseBy )
	{
		Date ldate = null; 
		
		ldate = getAsDate (p_dtFormat, p_dtVal);
		
		Calendar lcal = Calendar.getInstance();
		
		lcal.setTime(ldate);
		
		//System.out.println("Current date from cal : " + (lcal.get(Calendar.MONTH) + 1)+ "-" + lcal.get(Calendar.DATE)+ "-"+ lcal.get(Calendar.YEAR));
		
		if (p_incrementType == 1)
		{
			//System.out.println("Adding DATE");
			lcal.add(Calendar.DATE, p_increaseBy); // add N days  
		}
		if (p_incrementType == 2)
		{
			//System.out.println("Adding MONTH");
			lcal.add(Calendar.MONTH, p_increaseBy); // add N months  
		}
		if (p_incrementType == 3)
		{
			//System.out.println("Adding YEAR");
			lcal.add(Calendar.YEAR, p_increaseBy); // add N year  
		}
		  
		ldate = lcal.getTime(); 

		
		return ldate;
	}
	
	/**
	 * Given a format and string containing date in that format, it returns an equivalent date object
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @return
	 */
	public static Date getAsDate (String p_dtFormat, String p_dtVal)
	{
		 Date ldate = null;  

		 try {
			  DateFormat formatter = null ; 
			  formatter = new SimpleDateFormat(p_dtFormat);
			  ldate = formatter.parse(p_dtVal);  
		  } catch (Exception e)
		  {
			  System.out.println("Exception :"+e);  
		  }  
		 
		 return ldate;
		 
	}
	
	/**
	 * Given a format and string containing date in that format, it returns an equivalent date object
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @return
	 */
	public static String getAsDateString (String p_dtFormat, Date p_dtVal)
	{
		 String strDate = null;  

		 try {
			  DateFormat formatter = null ; 
			  formatter = new SimpleDateFormat(p_dtFormat);
			  strDate = formatter.format(p_dtVal); 
		  } catch (Exception e)
		  {
			  System.out.println("Exception :"+e);  
		  }  
		 
		 return strDate;
		 
	}
	
	/**
	 * Gets the first date of current month from passed date, adjusting it if/as needed before getting first day of month
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @param p_incrementType
	 * @param p_increaseBy
	 * @return
	 */
	public static Date getFirstDateOfCurrentMonth (String p_dtFormat, String p_dtVal, int p_incrementType, int p_increaseBy ) 
	{
			Date ldate = null; 
			
			ldate = getAsDate (p_dtFormat, p_dtVal, p_incrementType, p_increaseBy );
			
			Calendar lcal = Calendar.getInstance();
			
			lcal.setTime(ldate);
			
			lcal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		
			return lcal.getTime();
	}
	
	/**
	 * Gets the first date of current month from passed date, adjusting it if/as needed before getting first day of month
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @param p_incrementType
	 * @param p_increaseBy
	 * @return
	 */
	public static String getFirstDateOfCurrentMonthAsString (String p_dtFormat, String p_dtVal, int p_incrementType, int p_increaseBy ) 
	{
			Date ldate = null; 
			
			ldate = getAsDate (p_dtFormat, p_dtVal, p_incrementType, p_increaseBy );
			
			Calendar lcal = Calendar.getInstance();
			
			lcal.setTime(ldate);
			
			lcal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		
			return getAsDateString (p_dtFormat, lcal.getTime());
	}
	
	/**
	 * Gets the first date of current month from passed date in string format
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @return
	 */
	public static Date getFirstDateOfCurrentMonth (String p_dtFormat, String p_dtVal) 
	{
			return getFirstDateOfCurrentMonth (p_dtFormat, p_dtVal, 0, 0) ;
	}

	/**
	 * Gets the first date of current month from passed date in string format
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @return
	 */
	public static String getFirstDateOfCurrentMonthAsString (String p_dtFormat, String p_dtVal) 
	{
			return getFirstDateOfCurrentMonthAsString (p_dtFormat, p_dtVal, 0, 0) ;
	}

	/**
	 * Compares two dates with given format and type of comparison requested
	 * 
	 * @param p_dtFormat1
	 * @param p_dtVal1
	 * @param p_dtFormat2
	 * @param p_dtVal2
	 * @param p_checkType
	 * @return
	 */
	public static boolean compareDates (String p_dtFormat1, String p_dtVal1, String p_dtFormat2, String p_dtVal2, int p_checkType)
	{
		boolean bRetVal = false;
		
		Date d1 = getAsDate (p_dtFormat1, p_dtVal1);
		Date d2 = getAsDate (p_dtFormat2, p_dtVal2);

		/*
		 * p_checkType 1 : check if dt1 < dt2
		 * p_checkType 2 : check if dt1 > dt2
		 * p_checkType 2 : check if dt1 = dt2
		 * 
		 */
		if (d1.before(d2) && p_checkType == 1)
		{
			// dt1 < dt2
			System.out.println("dt1 ("+p_dtVal1+") < dt2 ("+p_dtVal2+")");
			bRetVal = true;
		}
		else if (d1.after(d2) && p_checkType == 2)
		{
			// dt1 > dt2
			System.out.println("dt1 ("+p_dtVal1+") > dt2 ("+p_dtVal2+")");
			bRetVal = true;
		}
		else if (!d1.after(d2) && !d1.before(d2) && p_checkType == 3)
		{
			// dt1 = dt2
			System.out.println("dt1 ("+p_dtVal1+") = dt2 ("+p_dtVal2+")");
			bRetVal = true;
		}
		
	    System.out.println("returning ("+bRetVal+")");

	    return bRetVal;
	}
	
	/**
	 * Returns the year using a date in string format along with format
	 * 
	 * @param p_dtFormat
	 * @param p_dtVal
	 * @return
	 */
	public static int getYearOfDate (String p_dtFormat, String p_dtVal) 
	{
		Calendar lcal = Calendar.getInstance();
		lcal.setTime(getAsDate (p_dtFormat, p_dtVal));
		return lcal.get(Calendar.YEAR);
	}

	/**
	 * Util method to get directly formatted date to send over to sobject creation
	 * , date value is adjusted by factor (+ve or -ve) as specified
	 * 
	 * @param p_adjustDateBy
	 * @return
	 */
	public static String getDayValue(int p_adjustDateBy) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
		cal.add(Calendar.DATE, p_adjustDateBy);
		Date dateBeforeNdays = cal.getTime();

		return df.format(dateBeforeNdays);
	}

	/**
	 * Util method to get directly formatted date to send over to sobject creation
	 * , date value is adjusted by factor (+ve or -ve) as specified
	 * 
	 * @param p_dateFormat
	 * @param p_adjustDateBy
	 * @return
	 */
	public static String getDateAsString(String p_dateFormat, int p_adjustDateBy) {
		DateFormat df = new SimpleDateFormat(p_dateFormat);
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
		cal.add(Calendar.DATE, p_adjustDateBy);
		Date dateBeforeNdays = cal.getTime();

		return df.format(dateBeforeNdays);
	}

	/**
	 * 
	 * @param p_adjustDateBy
	 * @return
	 */
	public static String getDateAsString(int p_adjustDateBy) {
		return getDateAsString("yyyyMMddHHmmss", p_adjustDateBy);
	}

	/**
	 * 
	 * @return
	 */
	public static String getDateAsString() {
		return getDateAsString(0);
	}
}

