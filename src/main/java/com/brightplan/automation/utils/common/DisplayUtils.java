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

package com.brightplan.automation.utils.common;

import java.util.HashMap;
import java.util.Map;

public class DisplayUtils extends CommonUtilsBase {

	public static void HashMapDumpStrVals(HashMap<String, String> p_hmapStr,
			String p_Name) {
		String lsKey = "", lsVal = "";

		logIt("*** Start [" + p_Name + "] HashMap (String) dump ***");

		String str = "";

		for (Map.Entry<String, String> entry : p_hmapStr.entrySet()) {
			lsKey = (entry.getKey() != null ? (String) entry.getKey() : "");
			lsVal = (entry.getValue() != null ? (String) entry.getValue() : "");
			str += "key (" + lsKey + ") val (" + lsVal + ")\n";
		}

		logIt("\n" + str);
		logIt("*** End [" + p_Name + "] HashMap (String) dump ***");

	}

	public static void MapDumpStrVals(Map<String, String> p_hmapStr,
			String p_Name) {
		String lsKey = "", lsVal = "";

		logIt("*** Start [" + p_Name + "] Map (String) dump ***");

		String str = "";

		for (Map.Entry<String, String> entry : p_hmapStr.entrySet()) {
			lsKey = entry.getKey();
			lsVal = entry.getValue();
			str += "key (" + lsKey + ") val (" + lsVal + ")\n";
		}

		logIt("\n" + str);

		logIt("*** End [" + p_Name + "] Map (String) dump ***");

	}

	public static void HashMapDumpStrVals(HashMap<String, String> p_hmapStr) {
		HashMapDumpStrVals(p_hmapStr, "");
	}

	public static void HashMapDumpObjVals(HashMap<String, Object> p_hmapStr,
			String p_Name) {

		String lsKey = "";
		Object lsVal = "";

		logIt("*** Start [" + p_Name + "] HashMap (String) dump ***");

		String str = "";

		for (Map.Entry<String, Object> entry : p_hmapStr.entrySet()) {
			lsKey = (entry.getKey() != null ? (String) entry.getKey() : "");
			lsVal = (entry.getValue() != null ? entry.getValue() : "");
			str += "key (" + lsKey + ") val (" + lsVal + ")\n";
		}

		logIt("\n" + str);

		logIt("*** End [" + p_Name + "] HashMap (String) dump ***");
	}

}
