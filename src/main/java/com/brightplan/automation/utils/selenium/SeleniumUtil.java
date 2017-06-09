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


package com.brightplan.automation.utils.selenium;


/**
 * @author anurag.kumar
 *
 */
public class SeleniumUtil 
extends SeleniumUtilBase
{

	//------------------ MEMBERS
	private static String THIS_IMPL_NAME = "SeleniumUtil";

	//------------------ ACCESS METHODS
	
	/**
	 * @param args
	 */
	public void run(String[] args) 
	{
		set_strStatus("entered run() method of ("+ THIS_IMPL_NAME +")");
		
		System.out.println("dummy implementation for run() method of V ("+ THIS_IMPL_NAME +")");

		set_strStatus("exiting run() method of ("+ THIS_IMPL_NAME +")");
	}	
}

