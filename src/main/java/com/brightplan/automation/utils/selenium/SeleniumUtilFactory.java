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

import com.brightplan.automation.utils.common.CommonUtilsBase;


/**
 * @author anurag.kumar
 *
 */

public class SeleniumUtilFactory 
extends CommonUtilsBase
{
	private static final String THIS_IMPL_NAME = "SeleniumUtilFactory";
	private static SeleniumUtilFactory _thisUtilFactory = null;
	private static String _status  = "";

	// Constructor
	private SeleniumUtilFactory ()
	{
		// Do not let someone instantiate it
	}

	//------------------ ACCESS METHODS
	
	// Accessors
	public static SeleniumUtilFactory getInstance ()
	{
		synchronized (_status)
		{
			if (_thisUtilFactory == null)
			{
				_thisUtilFactory = new SeleniumUtilFactory ();
				_status = "initialized "+THIS_IMPL_NAME+" instance";
			}
		}
		return _thisUtilFactory;
	}

	/**
	 * Creates an instance of requested version and returns base by default 
	 * if fails to get requested version instantiated
	 * 
	 * @param p_Name
	 * @param p_Version
	 * @return
	 */
	/**
	 * Creates an instance of requested version and returns base by default 
	 * if fails to get requested version instantiated
	 * 
	 * @param p_Name
	 * @param p_Version
	 * @return
	 */
	public SeleniumUtilBase create (String p_Name, String p_Version)
	{
		String sClassName = p_Name + p_Version ;
		SeleniumUtilBase oBase = null;
		
		// Try instantiating one if you can
		try 
		{
			// Get instance
			set_strStatus("instantiating (" + sClassName + ")");
			System.out.println (get_strStatus());
			
			oBase = (SeleniumUtilBase) Class.forName(sClassName).newInstance();

			set_strStatus("instantiated (" + sClassName + ")");
			System.out.println (get_strStatus());
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			
			// Return instance of base class
			set_strStatus("Failed to instantiate (" + sClassName + "), attempting to get base class reference");
			System.out.println (get_strStatus());

			// Try instantiating one if you can
			try 
			{
				sClassName = p_Name;
				
				// Get instance
				set_strStatus("instantiating (" + sClassName + ")");
				System.out.println (get_strStatus());
				
				oBase = (SeleniumUtilBase) Class.forName(sClassName).newInstance();

				set_strStatus("instantiated (" + sClassName + ")");
				System.out.println (get_strStatus());
				
			} catch (Exception e1) 
			{
				// Return instance of base class
				set_strStatus("Failed to instantiate (" + sClassName + "), returning generic base class reference");
				System.out.println (get_strStatus());

				oBase = new SeleniumUtilBase ();
			}

		}
		
		// return the object ready for use
		return oBase;
	}


}

