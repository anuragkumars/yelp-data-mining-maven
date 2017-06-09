
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import com.brightplan.automation.helpers.DateHelper;
import com.brightplan.automation.utils.common.CommonUtilsBase;
import com.brightplan.automation.utils.common.DisplayUtils;

public class ReportUtilBase extends CommonUtilsBase {

	// ------------------ MEMBERS
	private String _implName = "ReportUtilBase";

	private ArrayList<String> _headerKeysOrderList = null;
	private HashMap<String, String> _headerKeysAndDisplayValuesMap = null;

	private ArrayList<String> _footerKeysOrderList = null;
	private HashMap<String, String> _footerKeysAndDisplayValuesMap = null;

	private ArrayList<String> _detailTestCaseOrderList = null;
	private HashMap<String, HashMap<String, String>> _detailTestCaseKeyValueMap = null;
	private HashMap<String, String> _detailTestCaseValueToUrlMap = null;
	private ArrayList<String> _detailKeysOrderList = null;
	private HashMap<String, String> _detailKeysAndDisplayValuesMap = null;

	private Vector <String> _resultKeysToFormatWithColor = null;

	private String _testClassIdentifier = null;
	private boolean _useTestClassIdentifierInReportName = false;
	private String _testClassReportFileName = null;
	private String _testClassReportFilePath = null;
	private FileWriter _testClassReportWriter = null;
	
	private String _envUrl = null;
	private String _reportTitle = null;
	private String _reportConfig = null;
	
	// Formatting stuff
	private String _detailHeaderColor = "#66DFFF";

	// ------------------ GETTERS AND SETTERS
	public String get_detailHeaderColor() {
		return _detailHeaderColor;
	}

	public void set_detailHeaderColor(String p_detailHeaderColor) {
		_detailHeaderColor = p_detailHeaderColor;
	}	
	
	public HashMap<String, String> get_detailKeysAndDisplayValuesMap() {
		return _detailKeysAndDisplayValuesMap;
	}

	public void add_detailKeysAndDisplayValuesMap(String p_key, String p_value) {
		_detailKeysAndDisplayValuesMap.put(p_key, p_value);
	}

	public void set_detailKeysAndDisplayValuesMap(
			HashMap<String, String> p_detailKeysAndDisplayValuesMap) {
		_detailKeysAndDisplayValuesMap = p_detailKeysAndDisplayValuesMap;
	}

	public ArrayList<String> get_detailKeysOrderList() {
		return _detailKeysOrderList;
	}

	public void set_detailKeysOrderList(ArrayList<String> p_detailKeysOrderList) {
		_detailKeysOrderList = p_detailKeysOrderList;
	}

	public void set_detailKeysOrderList(String p_keysDelimited) {
		String[] strParts = p_keysDelimited.split(",");
		for (String str: strParts) {
			_detailKeysOrderList.add(str);
		}
	}

	public void set_detailKeysOrderListAndValues(String p_keysDelimited, String p_valuesDelimited) {
		String[] strKeyParts = p_keysDelimited.split(",");
		String[] strValParts = p_valuesDelimited.split(",");
		for (int i = 0; i < strKeyParts.length; i++) {
			_detailKeysOrderList.add(strKeyParts[i]);
			add_detailKeysAndDisplayValuesMap(strKeyParts[i], strValParts[i]);
		}
	}
	
	public ArrayList<String> get_headerKeysOrderList() {
		return _headerKeysOrderList;
	}

	public void set_headerKeysOrderList(ArrayList<String> p_headerKeysOrderList) {
		_headerKeysOrderList = p_headerKeysOrderList;
	}

	public void set_headerKeysOrderList(String p_keysDelimited) {
		String[] strParts = p_keysDelimited.split(",");
		for (String str: strParts) {
			_headerKeysOrderList.add(str);
		}
	}

	public HashMap<String, String> get_headerKeysAndDisplayValuesMap() {
		return _headerKeysAndDisplayValuesMap;
	}

	public void set_headerKeysAndDisplayValuesMap(
			HashMap<String, String> p_headerKeysAndDisplayValuesMap) {
		_headerKeysAndDisplayValuesMap = p_headerKeysAndDisplayValuesMap;
	}

	public ArrayList<String> get_footerKeysOrderList() {
		return _footerKeysOrderList;
	}

	public void set_footerKeysOrderList(ArrayList<String> p_footerKeysOrderList) {
		_footerKeysOrderList = p_footerKeysOrderList;
	}

	public void set_footerKeysOrderList(String p_keysDelimited) {
		String[] strParts = p_keysDelimited.split(",");
		for (String str: strParts) {
			_footerKeysOrderList.add(str);
		}
	}
	
	public void set_footerKeysOrderListAndValues(String p_keysDelimited, String p_valuesDelimited) {
		String[] strKeyParts = p_keysDelimited.split(",");
		String[] strValParts = p_valuesDelimited.split(",");
		for (int i = 0; i < strKeyParts.length; i++) {
			_footerKeysOrderList.add(strKeyParts[i]);
			add_footerKeysAndDisplayValuesMap(strKeyParts[i], strValParts[i]);
		}
	}
	

	public HashMap<String, String> get_footerKeysAndDisplayValuesMap() {
		return _footerKeysAndDisplayValuesMap;
	}

	public void set_footerKeysAndDisplayValuesMap(
			HashMap<String, String> p_footerKeysAndDisplayValuesMap) {
		_footerKeysAndDisplayValuesMap = p_footerKeysAndDisplayValuesMap;
	}
	
	public void add_footerKeysAndDisplayValuesMap(String p_key, String p_value) {
		_footerKeysAndDisplayValuesMap.put(p_key, p_value);
	}

	public ArrayList<String> get_detailTestCaseOrderList() {
		return _detailTestCaseOrderList;
	}

	public void add_detailTestCaseOrderList(
			String p_detailTestCase) {
		_detailTestCaseOrderList.add (p_detailTestCase);
	}
	
	public void add_allDetailTestCaseOrderList(String p_keysDelimited) {
		String[] strParts = p_keysDelimited.split(",");
		for (String str: strParts) {
			add_detailTestCaseOrderList (str);
		}
	}

	public void set_detailTestCaseOrderList(
			ArrayList<String> p_detailTestCaseOrderList) {
		_detailTestCaseOrderList = p_detailTestCaseOrderList;
	}

	public HashMap<String, HashMap<String, String>> get_detailTestCaseKeyValueMap() {
		return _detailTestCaseKeyValueMap;
	}

	public void set_detailTestCaseKeyValueMap(
			HashMap<String, HashMap<String, String>> p_detailTestCaseKeyValueMap) {
		_detailTestCaseKeyValueMap = p_detailTestCaseKeyValueMap;
	}

	public String get_implName() {
		// override this method
		return _implName;
	}

	public void set_implName(String p_implName) {
		_implName = p_implName;
	}

	public String get_testClassIdentifier() {
		return _testClassIdentifier;
	}

	public void set_testClassIdentifier(String p_testClassIdentifier) {
		_testClassIdentifier = p_testClassIdentifier;
	}

	public String get_testClassReportFileName() {
		return _testClassReportFileName;
	}

	public void set_testClassReportFileName(String p_testClassReportFileName) {
		_testClassReportFileName = p_testClassReportFileName;
	}

	public FileWriter get_testClassReportWriter() {
		return _testClassReportWriter;
	}

	public void set_testClassReportWriter(FileWriter p_testClassReportWriter) {
		_testClassReportWriter = p_testClassReportWriter;
	}

	public String get_testClassReportFilePath() {
		return _testClassReportFilePath;
	}

	public void set_testClassReportFilePath(String p_testClassReportFilePath) {
		_testClassReportFilePath = p_testClassReportFilePath;
	}

	public HashMap<String, String> get_detailTestCaseValueToUrlMap() {
		return _detailTestCaseValueToUrlMap;
	}

	public void add_detailTestCaseValueToUrlMap(String p_key, String p_value) {
		_detailTestCaseValueToUrlMap.put(p_key, p_value);
	}

	public void set_detailTestCaseValueToUrlMap(
			HashMap<String, String> p_detailTestCaseValueToUrlMap) {
		_detailTestCaseValueToUrlMap = p_detailTestCaseValueToUrlMap;
	}

	public boolean is_useTestClassIdentifierInReportName() {
		return _useTestClassIdentifierInReportName;
	}

	public void set_useTestClassIdentifierInReportName(
			boolean p_useTestClassIdentifierInReportName) {
		_useTestClassIdentifierInReportName = p_useTestClassIdentifierInReportName;
	}

	public String get_envUrl() {
		return _envUrl;
	}

	public void set_envUrl(String p_envUrl) {
		_envUrl = p_envUrl;
	}
	
	public String get_reportTitle() {
		return _reportTitle;
	}

	public void set_reportTitle(String p_reportTitle) {
		_reportTitle = p_reportTitle;
	}

	public String get_reportConfig() {
		if (_reportConfig == null) _reportConfig = "default";
		return _reportConfig;
	}

	public void set_reportConfig(String p_reportConfig) {
		_reportConfig = p_reportConfig;
	}

	public Vector<String> get_resultKeysToFormatWithColor() {
		return _resultKeysToFormatWithColor;
	}

	public void set_resultKeysToFormatWithColor(
			Vector<String> p_resultKeysToFormatWithColor) {
		_resultKeysToFormatWithColor = p_resultKeysToFormatWithColor;
	}

	public void set_resultKeysToFormatWithColorDelim(
			String p_keysDelimited) {
		String[] strParts = p_keysDelimited.split(",");
		for (String str: strParts) {
			_resultKeysToFormatWithColor.add(str);
		}
	}
	
	// ------------------ CONSTRUCTORS
	public ReportUtilBase () {
		logIt(get_implName() + ":: Default constructor");
		initVars ();
		//initOutFile ();
	}

	public ReportUtilBase (String p_testClassIdentifier, boolean p_useTestClassIdentifierInReportName) {
		logIt(get_implName() + ":: Overridden constructor");
		set_testClassIdentifier (_testClassIdentifier);
		set_useTestClassIdentifierInReportName (p_useTestClassIdentifierInReportName);
		initVars ();
		initOutFile ();
	}

	// ------------------ ACCESS METHODS
	public void initVars () {
		_headerKeysOrderList = new ArrayList<String> ();
		_headerKeysOrderList.clear();
		_headerKeysAndDisplayValuesMap = new HashMap<String, String> ();
		_headerKeysAndDisplayValuesMap.clear();

		_footerKeysOrderList = new ArrayList<String> ();
		_footerKeysOrderList.clear();
		_footerKeysAndDisplayValuesMap = new HashMap<String, String> ();
		_footerKeysAndDisplayValuesMap.clear();
		
		_detailTestCaseOrderList = new ArrayList<String> ();
		_detailTestCaseOrderList.clear();
		
		_detailTestCaseKeyValueMap = new HashMap<String, HashMap<String, String>> ();
		_detailTestCaseKeyValueMap.clear();
		
		_detailKeysAndDisplayValuesMap = new HashMap<String, String> ();
		_detailKeysAndDisplayValuesMap.clear();
		
		_detailTestCaseValueToUrlMap = new HashMap<String, String> ();
		_detailTestCaseValueToUrlMap.clear();
		
		_detailKeysOrderList = new ArrayList<String> ();
		_detailKeysOrderList.clear();
		
		_resultKeysToFormatWithColor = new Vector <String> ();
		_resultKeysToFormatWithColor.clear();
	}
	
	public void initOutFile () {
		String lsUsePath = System.getProperty("UseOutputFilePath");
		String lsFileName = System.getProperty("OutputFileNameRpt");
		if (lsUsePath != null && !lsUsePath.trim().equals("")) {
			logIt(get_implName() + ":: Overriding path, prefixing path "+ lsUsePath +" in file name (" + lsFileName + ")");
		} else {
			lsUsePath = "";
			logIt(get_implName() + ":: Not overriding path, no system variable UseOutputFilePath defined. Expected full path in file name (" + lsFileName + ")");
		}

		if (System.getProperty("TestClassIdentifier") != null) {
			set_testClassIdentifier (System.getProperty("TestClassIdentifier"));
			set_useTestClassIdentifierInReportName (true);
		}
		
		// Default format to use in name
		String dtFormat = "yyyyMMdd";
		
		boolean bUseDateFirst = false;
		if (System.getProperty("UseDateFirstInReportName") != null) {
			bUseDateFirst = true;
		}

		if (System.getProperty("UseDateFormatInReportName") != null) {
			dtFormat = System.getProperty("UseDateFormatInReportName");
		}

		String dtToday = DateHelper.getAsDateString (dtFormat, new Date());

		if (is_useTestClassIdentifierInReportName()) {
			if (!bUseDateFirst) {
				lsFileName = get_testClassIdentifier() + "_" + dtToday + "_" + lsFileName;
			} else {
				lsFileName = dtToday + "_" + get_testClassIdentifier() + "_" + lsFileName;
			}
		}
		
		lsFileName = lsUsePath + lsFileName;
		
		set_testClassReportFilePath(lsUsePath);
		set_testClassReportFileName(lsFileName);
		
		try {
			set_testClassReportWriter (new FileWriter(new File (lsFileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeOutPut (String p_strOut) {
		try {
			if (get_testClassReportWriter() != null) {
				get_testClassReportWriter().write(p_strOut);
				get_testClassReportWriter().flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeOutFile () {
		try {
			if (get_testClassReportWriter() != null) {
				get_testClassReportWriter().flush();
				get_testClassReportWriter().close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add_detailTestCaseValueMap(
			String p_strKey,
			HashMap<String, String> p_detailTestCaseValueMap) {
		_detailTestCaseKeyValueMap.put(p_strKey, p_detailTestCaseValueMap);
	}

	public String formatAsURL (String p_val)
	{
		return formatAsURL (p_val, "");
	}
	
	public String formatForDisplay (String p_val)
	{
		if (p_val != null && !p_val.trim().equals("") && !p_val.trim().equals("null")) {
			return p_val;
		} else {
			return "&nbsp;";
		}
	}
	
	public String formatAsURL(String p_val, String p_display) {
		// <a href="http://www.w3schools.com" target="_blank">Visit
		// W3Schools.com!</a>
		if (get_envUrl() == null) {
			set_envUrl (System.getProperty("loginEndPoint"));
		}
		
		if (p_val != null && !p_val.equalsIgnoreCase("")) {
			if (p_display != null && !p_display.equalsIgnoreCase("")) {
				return "<a href=\"" + get_envUrl() + "/" + p_val
						+ "\" target=\"_blank\">" + p_display + "</a>";
			} else {
				return "<a href=\"" + get_envUrl() + "/" + p_val
						+ "\" target=\"_blank\">" + p_val + "</a>";
			}
		} else {
			return "&nbsp;";
		}
	}

	public void writeReport ()
	{
		writeReport (get_reportConfig());
	}

	public void writeReport (String p_configType)
	{
		StringBuilder sb = new StringBuilder ();

		initOutFile ();
		
		sb.append(getReportHeader (p_configType));
		sb.append(getReportBody   (p_configType));
		sb.append(getReportFooter (p_configType));

		writeOutPut(sb.toString());
		
		closeOutFile ();
	}
	
	public String getReportHeader ()
	{
		return getReportHeader (get_reportConfig());
	}

	public String getReportHeader (String p_configType)
	{
		StringBuilder sb = new StringBuilder ();
		
		if (p_configType.equalsIgnoreCase("default")) {
			sb.append("<HTML><HEAD><TITLE>"+get_reportTitle()+"</TITLE></HEAD>\n");
			sb.append(" <BODY>\n");

			sb.append("<br><h3>"+get_reportTitle()+"</h3>\n");
			//sb.append("<br>\n");
			
			sb.append("<i><p style=\"font-weight: bold;\">Report Generated on : "+new Date().toString()+" </p></i>\n");
			/*
			sb.append("<TABLE border=\"0\">\n");
			sb.append("  <tbody>\n");
			sb.append("   <TR>\n");
			sb.append("     <TD nowrap style=\"font-weight: bold;\">Report Generated on : </TD>\n");
			sb.append("     <TD nowrap >"+new Date().toString()+"</TD>\n");
			sb.append("   </TR>\n");
			sb.append("  </tbody>\n");
			sb.append("</TABLE>\n");
			*/

			sb.append("<br>\n");
			
		} else {
			logIt(get_implName() + ":: No template for report header configured, try using \"default\" as parameter");
		}
		
		return sb.toString(); 
	}

	public String getReportBody ()
	{
		return getReportBody ("default");
	}
	
	public String getReportBody (String p_configType)
	{
		StringBuilder sb = new StringBuilder ();
		
		if (p_configType.equalsIgnoreCase("default")) {
			sb.append(getReportBodyHeader (p_configType));
			
			for (String oneTestCase: get_detailTestCaseOrderList()) {
				logIt(get_implName() + "getReportBody:: Processing testcase : " + oneTestCase);
				// Get data
				if (_detailTestCaseKeyValueMap.get(oneTestCase) != null) {
					DisplayUtils.HashMapDumpStrVals(_detailTestCaseKeyValueMap.get(oneTestCase));
					sb.append(getReportBodyForOneTestCase (_detailTestCaseKeyValueMap.get(oneTestCase)));
				} else {
					logIt(get_implName() + "getReportBody:: testcase defined but probably not executed : " + oneTestCase);
				}
			}
			
			sb.append(getReportBodyFooter (p_configType));
			
		} else {
			logIt(get_implName() + ":: No template for report body configured, try using \"default\" as parameter");
		}
		
		return sb.toString(); 
	}
	
	public String getReportBodyHeader (String p_configType)
	{
		StringBuilder sb = new StringBuilder ();

		if (p_configType.equalsIgnoreCase("default")) {
			
			sb.append("<TABLE border=\"1\">\n");
			sb.append("  <tbody>\n");
			sb.append("   <TR valign=\"top\">\n");
			
			for (String oneKey: get_detailKeysOrderList()) {
				sb.append("     <TD nowrap style=\"font-weight: bold; \" bgcolor=\""+get_detailHeaderColor()+"\">"+_detailKeysAndDisplayValuesMap.get(oneKey)+"</TD>\n");
			}
			
			sb.append("   </TR>\n");
		} else {
			logIt(get_implName() + ":: No template for report body configured, try using \"default\" as parameter");
		}

		return sb.toString(); 
	}

	public String formatOneDetailValue_XX (String p_key, String p_value) {
		// Check if this key is part of map needing env url prefixing
		//"     <TD nowrap >"+formatOneDetailValue(oneKey,p_keyValuesMap.get(oneKey))+"</TD>\n"
		
		if (_detailTestCaseValueToUrlMap.get(p_key) != null) {
			return formatAsURL(p_value);
		} else {
			if (p_value == null) p_value = "&nbsp;";
			return p_value;
		}
	}
	
	public String formatOneDetailValue (String p_key, String p_value) {
		// Check if this key is part of map needing env url prefixing
		//"     <TD nowrap >"+formatOneDetailValue(oneKey,p_keyValuesMap.get(oneKey))+"</TD>\n"
		String strRet = p_value;
		
		if (_detailTestCaseValueToUrlMap.get(p_key) != null) {
			strRet = "     <TD nowrap >"+ formatAsURL(p_value) +"</TD>\n";
		} else {
			if (p_value == null) strRet = "&nbsp;";

			// TODO: Maybe Later Remove nowrap from test case desc as it can get big
			if (p_key != null && p_key.equalsIgnoreCase("Test_CaseDesc")) {
				strRet = "     <TD nowrap >"+ strRet +"</TD>\n";
			}
			else {
				strRet = "     <TD nowrap >"+ strRet +"</TD>\n";
			}

		}
		
		return strRet;
	}
	
	public String formatOneDetailValueA (String p_key, String p_value) {
		// Check if this key is part of map needing env url prefixing
		//"     <TD nowrap >"+formatOneDetailValue(oneKey,p_keyValuesMap.get(oneKey))+"</TD>\n"
		String strRet = p_value;
		
		if (_detailTestCaseValueToUrlMap.get(p_key) != null) {
			strRet = "     <TD nowrap >"+ formatAsURL(p_value) +"</TD>\n";
		} else {
			if (p_value == null) strRet = "&nbsp;";
			strRet = "     <TD nowrap >"+ strRet +"</TD>\n";
		}
		
		return strRet;
	}
	
	public String formatOneDetailValueWithColor (String p_key, String p_value) {
		// Check if this key is part of map needing env url prefixing
		//"     <TD nowrap >"+formatOneDetailValue(oneKey,p_keyValuesMap.get(oneKey))+"</TD>\n"
		// outStrTemp += "     <TD nowrap bgcolor=\"#FF0000\">NOT FOUND</TD>\n";
		
		String strRet = "";
		String strColor = "";
		
		if (p_value == null) {
			strRet = "&nbsp;";
		} else {
			strRet = p_value;
		}
		
		if (p_value != null && p_value.equalsIgnoreCase("pass")) {
			strColor = "bgcolor=\"#00FF00\"";
		} else if (p_value != null && p_value.equalsIgnoreCase("fail")) {
			strColor = "bgcolor=\"#FF0000\"";
		} else if (p_value != null && p_value.startsWith("conditional")) {
			strColor = "bgcolor=\"yellow\"";
		} else {
			strColor = "";
		}
		
		if (_detailTestCaseValueToUrlMap.get(p_key) != null) {
			strRet = "     <TD nowrap "+strColor+">"+ formatAsURL(p_value) +"</TD>\n";
		} else {
			strRet = "     <TD nowrap "+strColor+">"+ strRet +"</TD>\n";
		}
		
		return strRet;
	}
	
	public String getReportBodyForOneTestCase (HashMap<String, String> p_keyValuesMap)
	{
		StringBuilder sb = new StringBuilder ();
		
		sb.append("   <TR valign=\"top\">\n");
		
		// Check the list of colum
		for (String oneKey: get_detailKeysOrderList()) {
			//sb.append("     <TD nowrap >"+formatOneDetailValue(oneKey,p_keyValuesMap.get(oneKey))+"</TD>\n");
			if (get_resultKeysToFormatWithColor()!= null && get_resultKeysToFormatWithColor().contains(oneKey)) {
				sb.append(formatOneDetailValueWithColor(oneKey,p_keyValuesMap.get(oneKey)));
			} else {
				sb.append(formatOneDetailValue(oneKey,p_keyValuesMap.get(oneKey)));
			}
		}
		
		sb.append("   </TR>\n");
		
		return sb.toString(); 
	}

	public String getReportBodyFooter ()
	{
		StringBuilder sb = new StringBuilder ();
		sb.append("  </tbody>\n");
		sb.append("</TABLE>\n");
		return sb.toString(); 
	}

	public String getReportFooter (String p_configType)
	{
		StringBuilder sb = new StringBuilder ();

		if ( p_configType.equalsIgnoreCase("default")) {
			
			sb.append("<BR>\n");

			sb.append("<TABLE border=\"1\"><tbody>\n");
			sb.append("   <TR valign=\"top\">\n");
			
			for (String oneKey: _footerKeysOrderList) {
				sb.append("     <TD nowrap style=\"font-weight: bold; \" bgcolor=\"#66DFFF\">"+_footerKeysAndDisplayValuesMap.get(oneKey)+"</TD>\n");
			}

			sb.append("   </TR>\n");

			sb.append("   <TR valign=\"top\">\n");
			
			for (String oneKey: _footerKeysOrderList) {
				sb.append("     <TD nowrap>"+_detailTestCaseKeyValueMap.get("footer").get(oneKey)+"</TD>\n");
			}
			
			sb.append("   </TR>\n");
			sb.append("  </tbody></TABLE>\n");
		}
		
		sb.append(" </BODY>\n");
		sb.append("</HTML>\n");
		
		return sb.toString(); 
	}

	public String getReportBodyFooter (String p_configType)
	{
		StringBuilder sb = new StringBuilder ();

		if (p_configType.equalsIgnoreCase("default")) {
			
			sb.append( getReportBodyFooter () );
			
		} else {
			logIt(get_implName() + ":: No template for report body configured, try using \"default\" as parameter");
		}

		return sb.toString(); 
	}


}
