
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

package com.brightplan.automation.projects.common;

import java.util.Date;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.brightplan.automation.utils.common.CommonUtilsBase;
import com.brightplan.automation.utils.report.ReportUtilBase;
import com.brightplan.automation.utils.report.ReportUtilFactory;
import com.brightplan.automation.utils.selenium.SeleniumUtilBase;
import com.brightplan.automation.utils.selenium.SeleniumUtilHelper;

/**
 * java -cp <classpath> -D<>=<>
 *         org.junit.runner.JUnitCore <junit class name>
 */
public class CommonJUnitBase extends CommonUtilsBase {

	public static SeleniumUtilBase _utilBase = null;
	public static SeleniumUtilHelper _utilHelper = null;
	public static ReportUtilBase _utilReport = null;
	public long _waitTimeDefault = 15000;
	
	public static HashMap<String, String> _hmTestDetailReportMap = new HashMap<String, String> ();

	public static String _thisTestCase = null;
	public static String _thisTestCaseNum = null;
	public static String _thisTestCaseDesc = null;
	public static String _strDataTestCase = null;
	public static String _strDataTestResult = null;
	public static String _strDataTestResultOverRide = "";
	public static String _strDataTestExecutionComplete = null;

	public HashMap<String, HashMap<String, String>> _hmStoreReportDataList = null;
	
	public static int _totalTestCount = 0;
	public static int _totalPassTestCount = 0;
	public static int _totalFailTestCount = 0;
	public static long _testsRunStartTime = 0L;
	public static long _testsRunEndTime = 0L;
	public static long _testsTotalRunTime = 0L;
	
	public static String _testsStartDateTime = null;
	public static String _testsEndDateTime = null;

	static {
		System.out.println("Executing static in Base");
		loadProperties ();
		setupEnvConvertPropertiesToEnvVariables();
	}
	
	public void collectOneTestCaseDetailData (String p_key, String p_value) {
		_hmTestDetailReportMap.put(p_key, p_value);
	}
	

	public void increment_totalFailTestCount() {
		_totalFailTestCount++;
	}
	
	public void increment_totalPassTestCount() {
		_totalPassTestCount++;
	}
		
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		System.out.println("**** setUpBeforeClass () Base ****");
//	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClassReportInit () throws Exception {
		_testsRunStartTime = System.currentTimeMillis();
		_testsStartDateTime = new Date().toString();
		
		System.out.println("**** setUpBeforeClassReportInit () Base ****");
		ReportUtilFactory reportFact = ReportUtilFactory.getInstance();
		_utilReport = reportFact.create("ReportUtil", "1");
		_utilReport.initVars();
		System.out.println("**** Initialized ReportUtil in setUpBeforeClassReportInit () Base ****");
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("**** tearDownAfterClass () Base ****");

		_testsRunEndTime = System.currentTimeMillis();
		_testsTotalRunTime = _testsRunEndTime - _testsRunStartTime;
		
		_testsEndDateTime = new Date().toString();
		
		collectTestCaseFooterData ();
		
		boolean bWriteReport = true;
		if (System.getProperty("SkipReportOut") != null && System.getProperty("SkipReportOut").equalsIgnoreCase("yes")) {
			bWriteReport = false;
		}
		
		if (bWriteReport) {
			System.out.println("**** tearDownAfterClass () Base, writing report html ****");
			_utilReport.writeReport();
		} else {
			System.out.println("**** tearDownAfterClass () Base, NOT writing report html as SkipReportOut env var is set to yes ****");
		}
		
	}
	
	@BeforeClass
	public static void setUpBeforeClassExt() throws Exception {
		System.out.println("**** setUpBeforeClassExt () Base ****");
	}
	
	@Before
	public void setUp() throws Exception {
		System.out.println("**** setUp () Base ****");
		_hmTestDetailReportMap = new HashMap<String,String> ();
		_hmTestDetailReportMap.clear();
		
		_totalTestCount++;
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("**** tearDown () Base ****");
	}

	public static  void collectTestCaseDetailData () {
		_hmTestDetailReportMap.put("Test_CaseNum", _thisTestCaseNum);
		_hmTestDetailReportMap.put("Test_CaseDataNum", _strDataTestCase);
		_hmTestDetailReportMap.put("Test_CaseDesc", _thisTestCaseDesc);
	}
	
	public static void collectTestCaseFooterData () {
		_hmTestDetailReportMap = new HashMap<String,String> ();
		_hmTestDetailReportMap.clear();
		_hmTestDetailReportMap.put("Test_ReportStartDateTime", ""+_testsStartDateTime);
		_hmTestDetailReportMap.put("Test_ReportEndDateTime", ""+_testsEndDateTime);
		_hmTestDetailReportMap.put("Test_ReportStartTime", ""+_testsRunStartTime);
		_hmTestDetailReportMap.put("Test_ReportEndTime", ""+_testsRunEndTime);
		_hmTestDetailReportMap.put("Test_ReportRunTimeInMillis", ""+_testsTotalRunTime);
		_hmTestDetailReportMap.put("Test_TestsTotal", ""+_totalTestCount);
		_hmTestDetailReportMap.put("Test_TestsPass", ""+_totalPassTestCount);
		_hmTestDetailReportMap.put("Test_TestsFail", ""+_totalFailTestCount);
		
		_utilReport.add_detailTestCaseValueMap("footer", _hmTestDetailReportMap);
	}
	
	public void doTest () {
		
	}
	
}
