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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.brightplan.automation.utils.common.CommonUtilsBase;

public class SeleniumUtilBase extends CommonUtilsBase {
	// ------------------ MEMBERS
	private static final String THIS_IMPL_NAME = "SeleniumUtilBase";

	// Required members

	// Remote related
	private String _endpointRemote = null;
	private String _userNameRemote = null;
	private String _passwordRemote = null;
	private String _accessKeyRemote = null;
	private boolean _bRemoteUrlComplete = false;
	private boolean _bRemoteExecutionEnabled = false;
	private boolean _bInitAtLogin = true;

	// Windows XP works with saucelabs, WINDOWS_8 does not work
	private String _driverPlatform = null;

	// 47 and 43.0 both work with saucelab
	private String _driverVersion = null;

	private WebDriver _driver = null;
	private DesiredCapabilities _capabilities = null;
	private String _driverLocation = null;

	private String _endpoint = null;
	private String _userName = null;
	private String _password = null;

	// Time in seconds
	private int _waitTimeLogin = -1;

	// Time in milli seconds
	public final int DEFAULT_WAIT_TIME_MILLI_SEC = 5000;
	private int _waitTimeLinkClick = -1;
	private int _waitTimePageLoad = -1;

	// Other settings
	private boolean _maximizeBrowserWin = true;
	private boolean _closeBrowserWin = true;

	private ArrayList<Object> _listKeyElements = new ArrayList<Object>();

	public enum WebElementType {
	    ID,
	    CLASSNAME,
	    NAME,
	    TAGNAME,
	    CSSSELECTOR,
	    LINKTEXT,
	    XPATH;

	    WebElement getElement(WebDriver p_driver, String p_ref, long p_delayMilliSeconds) {
	    	
	    	if (p_delayMilliSeconds > 0) {
		    	try {
		    		logIt("WebElementType::getElement - Begin sleeping/delaying for " + p_delayMilliSeconds + " milliseconds");
					Thread.sleep(p_delayMilliSeconds);
					logIt("WebElementType::getElement - End sleeping/delaying for " + p_delayMilliSeconds + " milliseconds");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logIt("WebElementType::getElement - End sleeping/delaying for " + p_delayMilliSeconds + " milliseconds");
				}
	    	}

	        switch (this) {
	            case ID:
	                return p_driver.findElement(By.id(p_ref));
	            case CLASSNAME:
	                return p_driver.findElement(By.className(p_ref));
	            case NAME:
	                return p_driver.findElement(By.name(p_ref));
	            case TAGNAME:
	                return p_driver.findElement(By.tagName(p_ref));
	            case CSSSELECTOR:
	                return p_driver.findElement(By.cssSelector(p_ref));
	            case LINKTEXT:
	                return p_driver.findElement(By.linkText(p_ref));
	            case XPATH:
	                return p_driver.findElement(By.xpath(p_ref));
	            default:
	                throw new AssertionError("Unknown operation " + this);
	        }
	    }
	    
	    WebElement getElement(WebDriver p_driver, String p_ref) {
	    	long DEFAULT_DELAY=1000;
	        return getElement(p_driver, p_ref, DEFAULT_DELAY);
	    }
	}
		
	// ------------------ METHODS
	
	public void init() {
		logIt("Entered init()..");

		if (System.getProperty("selenium.maximizeBrowserWin") != null
				&& System.getProperty("selenium.maximizeBrowserWin")
						.equalsIgnoreCase("yes")) {
			set_maximizeBrowserWin(true);
		} else {
			set_maximizeBrowserWin(false);
		}

		logIt("Property (selenium.maximizeBrowserWin) set as ("
				+ is_maximizeBrowserWin() + ")");

		if (System.getProperty("selenium.waitTimeLinkClick") != null) {
			try {
				set_waitTimeLinkClick(Integer.parseInt(System
						.getProperty("selenium.waitTimeLinkClick")));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				set_waitTimeLinkClick(DEFAULT_WAIT_TIME_MILLI_SEC);
			}
		} else {
			set_waitTimeLinkClick(DEFAULT_WAIT_TIME_MILLI_SEC);
		}

		logIt("Property (selenium.waitTimeLinkClick) set as ("
				+ get_waitTimeLinkClick() + ")");

		if (System.getProperty("selenium.waitTimePageLoad") != null) {
			try {
				set_waitTimePageLoad(Integer.parseInt(System
						.getProperty("selenium.waitTimePageLoad")));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				set_waitTimePageLoad(DEFAULT_WAIT_TIME_MILLI_SEC);
			}
		} else {
			set_waitTimePageLoad(DEFAULT_WAIT_TIME_MILLI_SEC);
		}

		logIt("Property (selenium.waitTimePageLoad) set as ("
				+ get_waitTimePageLoad() + ")");

		if (System.getProperty("selenium.closeBrowserWin") != null
				&& System.getProperty("selenium.closeBrowserWin")
						.equalsIgnoreCase("no")) {
			set_closeBrowserWin(false);
		} else {
			// default - close
			set_closeBrowserWin(true);
		}

		logIt("Property (selenium.closeBrowserWin) set as ("
				+ is_closeBrowserWin() + ")");

		// This would be full path to the driver (including driver itself) on
		// the respective host
		String ls_seleniumDriverLocation = System
				.getProperty("selenium.driver.location");
		boolean bUseLocationRef = false;
		if (ls_seleniumDriverLocation != null)
			bUseLocationRef = true;

		if (bUseLocationRef) {
			logIt("Selenium driver location override detected as ("
					+ ls_seleniumDriverLocation + ")");
			System.setProperty("selenium.webdriver.chrome",
					ls_seleniumDriverLocation);
		} else {
			// If missing starting with selenium, then use old style
			logIt("Selenium driver location taken as is from properties file");
			if (System.getProperty("selenium.webdriver.chrome") == null) {
				System.setProperty("selenium.webdriver.chrome",
						System.getProperty("googleDriverName"));
			}
		}

		// For now chrome only.. will add other browsers later
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("selenium.webdriver.chrome"));

		logIt("Property webdriver.chrome.driver from -> selenium.webdriver.chrome) set as ("
				+ System.getProperty("selenium.webdriver.chrome") + ")");

		set_capabilities(DesiredCapabilities.chrome());

		// Do remote setup now
		if (System.getProperty("selenium.remoteExecutionEnabled") != null
				&& System.getProperty("selenium.remoteExecutionEnabled")
						.equalsIgnoreCase("yes")) {
			set_remoteExecutionEnabled(true);
		} else {
			set_remoteExecutionEnabled(false);
		}

		try {
			set_driverPlatform(System
					.getProperty("selenium.webdriver.chrome.platform"));
			set_driverVersion(System
					.getProperty("selenium.webdriver.chrome.version"));

			if (get_capabilities() != null && get_driverPlatform() != null) {
				logIt("*** init() setting driver platform as : "
						+ get_driverPlatform());
				set_capability("platform", get_driverPlatform());
			}
			if (get_capabilities() != null && get_driverVersion() != null) {
				logIt("*** init() setting driver version as : "
						+ get_driverVersion());
				set_capability("version", get_driverVersion());
			}

		} catch (Exception e) {
			logIt("*** init() ERROR setting up driver platform and version..");
			e.printStackTrace();
		}

		logIt("Exiting init()..");

	}

	public void extractFormData(String p_calledFromFunctionalImpl,
			HashMap<String, Object> p_hmConfig,
			HashMap<String, Object> p_inValues,
			HashMap<String, Object> p_outValues,
			ArrayList<Object> p_listKeyElements,
			ArrayList<Object> p_listKeyElementsDesc, boolean p_bUseFrame) {
	}

	public void doImpl(String p_calledFromFunctionalImpl,
			HashMap<String, Object> p_inValues,
			HashMap<String, Object> p_outValues) {
		logIt("Entered doImpl (.2.) as called from ("
				+ p_calledFromFunctionalImpl + ") .. missing impl");

		logIt("Exiting doImpl (.2.) as called from ("
				+ p_calledFromFunctionalImpl + ") .. missing impl");
	}

	public void doImpl(String p_calledFromFunctionalImpl,
			HashMap<String, Object> p_inValues,
			HashMap<String, Object> p_outValues,
			ArrayList<Object> p_listKeyElements) {
		logIt("Entered doImpl (.3.) as called from ("
				+ p_calledFromFunctionalImpl + ")");

		logIt("Exiting doImpl (.3.) as called from ("
				+ p_calledFromFunctionalImpl + ")");
	}

	public void doImpl(String p_calledFromFunctionalImpl,
			HashMap<String, Object> p_inValues,
			HashMap<String, Object> p_outValues,
			ArrayList<Object> p_listKeyElements, boolean p_bUseFrame) {
		logIt("Entered doImpl (.4.) as called from ("
				+ p_calledFromFunctionalImpl + ")");

		logIt("Exiting doImpl (.4.) as called from ("
				+ p_calledFromFunctionalImpl + ")");
	}

	public void tearDown() {
		logIt("Entered tearDown()..");

		// close window .. too many windows open
		if (is_closeBrowserWin()) {
			logIt("Closing browser window on exit..");
			if (get_driver() != null) get_driver().close();
		} else {
			logIt("Not closing browser window on exit..");
		}

		logIt("Exiting tearDown()..");
	}

	public void doWindowAdjustment() throws Exception {
		// Maximize the Browser window
		if (is_maximizeBrowserWin()) {
			if (is_bRemoteExecutionEnabled()) {
				logIt("Not maximizing browser window in remote execution..");
			} else {
				logIt("Maximizing browser window..");
				get_driver().manage().window().maximize();
			}
		} else {
			logIt("Not maximizing browser window..");
		}

	}

	public String get_implName() {
		// override this method
		return THIS_IMPL_NAME;
	}

	public String get_endpoint() {
		return _endpoint;
	}

	public void set_endpoint(String p_endpoint) {
		_endpoint = p_endpoint;
	}

	public String get_userName() {
		return _userName;
	}

	public void set_userName(String p_userName) {
		_userName = p_userName;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String p_password) {
		_password = p_password;
	}

	// ------------------ ACCESS METHODS

	public WebDriver get_driver() {
		return _driver;
	}

	// public void init_driver() {
	//
	// }

	public void set_driver(WebDriver p_driver) {
		this._driver = p_driver;
	}

	public String getRemoteUrl() {
		StringBuilder sb = new StringBuilder();
		if (is_bRemoteUrlComplete()) {
			sb.append(get_endpointRemote());
		} else {
			sb.append("https://" + get_userNameRemote() + ":"
					+ get_accessKeyRemote() + "@" + get_endpointRemote());
		}
		return sb.toString();
	}

	public void init_driver() {
		if (is_bInitAtLogin()) {
			logIt("init_driver:: Initializaing driver as is_bInitAtLogin() is true");
			logIt("init_driver:: Initializaing Driver, checking if is_remoteExecutionEnabled() is true ? : "
					+ is_remoteExecutionEnabled());
			if (is_remoteExecutionEnabled()) {
				logIt("init_driver:: is_remoteExecutionEnabled is true");

				logIt("init_driver:: Initializing RemoteWebDriver for remote endpoint : "
						+ get_endpointRemote());

				try {
					logIt("init_driver:: started initializing RemoteWebDriver for URL : "
							+ getRemoteUrl());
					logIt("init_driver::  initializing RemoteWebDriver with capabilities : "
							+ get_capabilities());

					set_driver(new RemoteWebDriver(new URL(getRemoteUrl()),
							get_capabilities()));
					logIt("init_driver:: finished initializing RemoteWebDriver for URL : "
							+ getRemoteUrl());
				} catch (Exception e) {
					logIt("init_driver:: FAILED to initialize RemoteWebDriver for URL : "
							+ getRemoteUrl());
					e.printStackTrace();
				}

			} else {
				logIt("init_driver:: is_remoteExecutionEnabled is false, running in local mode");
				logIt("init_driver:: Initializaing ChromeDriver : "
						+ is_bInitAtLogin());
				logIt("init_driver::  using capabilities : "
						+ get_capabilities());
				set_driver(new ChromeDriver(get_capabilities()));
				
				if (is_maximizeBrowserWin()) {
					if (is_bRemoteExecutionEnabled()) {
						logIt("Not maximizing browser window in remote execution..");
					} else {
						logIt("Maximizing browser window..");
						get_driver().manage().window().maximize();
					}
				} else {
					logIt("Not maximizing browser window..");
				}
				
				logIt("init_driver:: Initialized ChromeDriver");
			}
		} else {
			logIt("init_driver:: Not initializaing driver as is_bInitAtLogin() is false");
		}
	}

	public void init_driver(boolean p_bInitAtLogin) {
		set_bInitAtLogin(p_bInitAtLogin);
		init_driver();
	}

	public DesiredCapabilities get_capabilities() {
		return _capabilities;
	}

	public void set_capabilities(DesiredCapabilities p_capabilities) {
		this._capabilities = p_capabilities;
	}

	public void set_capability(String p_capabilityKey, String p_capabilityValue) {
		_capabilities.setCapability(p_capabilityKey, p_capabilityValue);
	}

	public String get_driverLocation() {
		return _driverLocation;
	}

	public void set_driverLocation(String p_driverLocation) {
		this._driverLocation = p_driverLocation;
	}

	public int get_waitTimeLogin() {
		return _waitTimeLogin;
	}

	public void set_waitTimeLogin(int p_waitTimeLogin) {
		this._waitTimeLogin = p_waitTimeLogin;
	}

	public int get_waitTimeLinkClick() {
		return _waitTimeLinkClick;
	}

	public void set_waitTimeLinkClick(int p_waitTimeLinkClick) {
		this._waitTimeLinkClick = p_waitTimeLinkClick;
	}

	public int get_waitTimePageLoad() {
		return _waitTimePageLoad;
	}

	public void set_waitTimePageLoad(int p_waitTimePageLoad) {
		this._waitTimePageLoad = p_waitTimePageLoad;
	}

	public boolean is_maximizeBrowserWin() {
		return _maximizeBrowserWin;
	}

	public void set_maximizeBrowserWin(boolean p_maximizeBrowserWin) {
		this._maximizeBrowserWin = p_maximizeBrowserWin;
	}

	public boolean is_closeBrowserWin() {
		return _closeBrowserWin;
	}

	public void set_closeBrowserWin(boolean p_closeBrowserWin) {
		this._closeBrowserWin = p_closeBrowserWin;
	}

	public ArrayList<Object> get_listKeyElements() {
		return _listKeyElements;
	}

	public void set_listKeyElements(ArrayList<Object> p_listKeyElements) {
		this._listKeyElements = p_listKeyElements;
	}

	public String get_userNameRemote() {
		return _userNameRemote;
	}

	public void set_userNameRemote(String p_userNameRemote) {
		_userNameRemote = p_userNameRemote;
	}

	public String get_passwordRemote() {
		return _passwordRemote;
	}

	public void set_passwordRemote(String p_passwordRemote) {
		_passwordRemote = p_passwordRemote;
	}

	public String get_accessKeyRemote() {
		return _accessKeyRemote;
	}

	public void set_accessKeyRemote(String p_accessKeyRemote) {
		_accessKeyRemote = p_accessKeyRemote;
	}

	public boolean is_remoteExecutionEnabled() {
		return _bRemoteExecutionEnabled;
	}

	public void set_remoteExecutionEnabled(boolean p_bRemoteExecutionEnabled) {
		_bRemoteExecutionEnabled = p_bRemoteExecutionEnabled;
	}

	public boolean is_bInitAtLogin() {
		return _bInitAtLogin;
	}

	public void set_bInitAtLogin(boolean p_bInitAtLogin) {
		_bInitAtLogin = p_bInitAtLogin;
	}

	public String get_endpointRemote() {
		return _endpointRemote;
	}

	public void set_endpointRemote(String p_endpointRemote) {
		_endpointRemote = p_endpointRemote;
	}

	public boolean is_bRemoteUrlComplete() {
		return _bRemoteUrlComplete;
	}

	public void set_remoteUrlComplete(boolean p_bRemoteUrlComplete) {
		_bRemoteUrlComplete = p_bRemoteUrlComplete;
	}

	public boolean is_bRemoteExecutionEnabled() {
		return _bRemoteExecutionEnabled;
	}

	public void set_bRemoteExecutionEnabled(boolean p_bRemoteExecutionEnabled) {
		_bRemoteExecutionEnabled = p_bRemoteExecutionEnabled;
	}

	public String get_driverPlatform() {
		return _driverPlatform;
	}

	public void set_driverPlatform(String p_driverPlatform) {
		_driverPlatform = p_driverPlatform;
	}

	public String get_driverVersion() {
		return _driverVersion;
	}

	public void set_driverVersion(String p_driverVersion) {
		_driverVersion = p_driverVersion;
	}

}
