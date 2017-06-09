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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class CommonUtilsBase {

	private String _strStatus;
	private static Properties _runProperties;

	public static void loadProperties () {
		try {
			Properties prop = new Properties();
			InputStream input = null;
			input = new FileInputStream(System.getProperty("configFile"));
			// load properties file
			prop.load(input);
			_runProperties = prop;
			logIt("** loadProperties () :: loaded properties .. ("+ _runProperties + ")");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setupEnvConvertPropertiesToEnvVariables() {
		Properties p_configProps = get_runProperties();

		String sPropFileValue = null;
		String sEnvOverRideValue = null;

		logIt("** setupEnvConvertPropertiesToEnvVariables () :: Checking for properties .. ("
				+ p_configProps + ")");

		for (String sPropKey : p_configProps.stringPropertyNames()) {
			sPropFileValue = p_configProps.getProperty(sPropKey);
			sEnvOverRideValue = System.getProperty(sPropKey);

			// If already done override from env, ignore file value
			if (sEnvOverRideValue != null
					&& !sEnvOverRideValue.trim().equals("")) {
				logIt(" Not overriding already set env value ("
						+ sEnvOverRideValue + ") for key (" + sPropKey
						+ ") by config file value of (" + sPropFileValue + ")");
			} else {
				// Set value from file into env
				System.setProperty(sPropKey, sPropFileValue);
				logIt(" Setting env value (" + sPropFileValue + ") for key ("
						+ sPropKey + ") from config file");
			}
		}
	}
	
	public static void LogConsole(String p_msg) {
		Date dt = new Date();
		// Add logger or splunk support later
		if (System.getProperty("logger.suppress.all") != null
				&& System.getProperty("logger.suppress.all").equalsIgnoreCase(
						"yes")) {
			// Do nothing for now
		} else {
			System.out.println("{" + dt.toString() + "}" + p_msg);
		}
	}

	public static void logInfo(String p_msg) {
		if (System.getProperty("logger.suppress.info") != null
				&& System.getProperty("logger.suppress.info").equalsIgnoreCase(
						"yes")) {
			// Do nothing for now
		} else {
			LogConsole("[info]" + p_msg);
		}
	}

	public static void logWarn(String p_msg) {
		if (System.getProperty("logger.suppress.warn") != null
				&& System.getProperty("logger.suppress.warn").equalsIgnoreCase(
						"yes")) {
			// Do nothing for now
		} else {
			LogConsole("[warn]" + p_msg);
		}
	}

	public static void logError(String p_msg) {
		if (System.getProperty("logger.suppress.error") != null
				&& System.getProperty("logger.suppress.error")
						.equalsIgnoreCase("yes")) {
			// Do nothing for now
		} else {
			LogConsole("[error]" + p_msg);
		}
	}

	public static void logIt(String p_msg) {
		if (System.getProperty("logger.suppress.debug") != null
				&& System.getProperty("logger.suppress.debug")
						.equalsIgnoreCase("yes")) {
			// Do nothing for now
		} else {
			LogConsole("[debug]" + p_msg);
		}
	}

	public String get_strStatus() {
		return _strStatus;
	}

	public void set_strStatus(String _strStatus) {
		this._strStatus = _strStatus;
	}
	
	public String stripSpaces(String str) {
		// Do nothing if got null
		if (str == null)
			return str;

		StringBuilder sb = new StringBuilder();

		boolean gotSpace = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != ' ') {
				sb.append(c);
				gotSpace = false;
			} else {
				if (gotSpace) {
					// If here then we got space followed by space
					// skip this one
				} else {
					sb.append(c);
					gotSpace = true;
				}
			}
		}

		return sb.toString();
	}

	public static Properties get_runProperties() {
		return _runProperties;
	}

	public void set_runProperties(Properties _runProperties) {
		CommonUtilsBase._runProperties = _runProperties;
	}
		
}
