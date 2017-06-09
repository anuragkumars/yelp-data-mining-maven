/**
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

package com.brightplan.automation.utils.report;

import com.brightplan.automation.utils.common.CommonUtilsBase;

public class ReportUtilFactory 
extends CommonUtilsBase
{
	private static final String THIS_IMPL_NAME = "XmlUtilFactory";
	private static ReportUtilFactory _thisUtilFactory = null;
	private static String _status  = "";

	// Constructor
	private ReportUtilFactory ()
	{
		// Do not let someone instantiate it
	}

	//------------------ ACCESS METHODS
	
	// Accessors
	public static ReportUtilFactory getInstance ()
	{
		synchronized (_status)
		{
			if (_thisUtilFactory == null)
			{
				_thisUtilFactory = new ReportUtilFactory ();
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
	public ReportUtilBase create (String p_Name, String p_Version)
	{
		String sClassName = p_Name + p_Version ;
		ReportUtilBase oBase = null;
		
		// Try instantiating one if you can
		try 
		{
			// Get instance
			set_strStatus("instantiating (" + sClassName + ")");
			
			oBase = (ReportUtilBase) Class.forName(sClassName).newInstance();

			set_strStatus("instantiated (" + sClassName + ")");
			
		} catch (Exception e) 
		{
			// Return instance of base class
			set_strStatus("Failed to instantiate (" + sClassName + "), attempting to get base class reference");

			// Try instantiating one if you can
			try 
			{
				sClassName = p_Name;
				
				// Get instance
				set_strStatus("instantiating (" + sClassName + ")");
				
				oBase = (ReportUtilBase) Class.forName(sClassName).newInstance();

				set_strStatus("instantiated (" + sClassName + ")");
				
			} catch (Exception e1) 
			{
				// Return instance of base class
				set_strStatus("Failed to instantiate (" + sClassName + "), returning generic base class reference");

				oBase = new ReportUtilBase ();
			}

		}
		
		// return the object ready for use
		return oBase;
	}

}

