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

package com.brightplan.automation.utils.selenium;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.brightplan.automation.helpers.RandomInfoHelper;
import com.brightplan.automation.utils.common.DisplayUtils;

/**
 * @author anurag.kumar
 *
 */
public class SeleniumUtilYelpMerchantDetailsTest extends SeleniumUtilBase {

	// ------------------ MEMBERS
	private static final String THIS_IMPL_NAME = "SeleniumUtilYelpMerchantDetailsTest";

	private static SeleniumUtilBase _utilBase = null;
	private static SeleniumUtilHelper _utilHelper = null;
	private static long _waitTimeDefault = 15000;

	// ------------------ ACCESS METHODS
	private static void setLocalProperties() {
		System.setProperty("selenium.endpoint", "http://www.yelp.com");
		System.setProperty("selenium.maximizeBrowserWin", "yes");
		System.setProperty("selenium.closeBrowserWin", "yes");

//		if (System.getProperty("selenium.webdriver.chrome") == null) {
//			System.out.println("Driver not defined in properties file, defaulting location");
//			System.setProperty("selenium.webdriver.chrome",
//					"C:/dev/docs/BrightPlan/BrightPlan_B/driver/chromedriver.exe");
//		} else {
//			System.out.println("Using driver defined in properties file");
//		}
	}

	@Override
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
	
	public void init(boolean p_useLocalProps) {
		SeleniumUtilFactory seleniumFact = SeleniumUtilFactory.getInstance();
		_utilBase = seleniumFact.create(
				"com.brightplan.automation.utils.selenium.SeleniumUtil",
				"YelpMerchantDetailsTest");
		_utilHelper = new SeleniumUtilHelper();

		// For now set locally, later move to properties file
		if (p_useLocalProps)
			setLocalProperties();

		_utilBase.init();
		_utilBase.init_driver();

	}

	public void navigateToUrl(boolean p_displayDebug) {
		navigateToUrl(p_displayDebug, "Step 1. Go to yelp.com");
	}

	public void navigateToUrl(boolean p_displayDebug, String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}
		_utilHelper.navigateToUrl(_utilBase,
				System.getProperty("selenium.endpoint"), p_stepInfo,
				_waitTimeDefault);
	}

	public void setFocusOnMainSearch(boolean p_displayDebug) {
		setFocusOnMainSearch(p_displayDebug,
				"Step 2. Select 'Restaurants', Setting focus on Find");
	}

	public void setFocusOnMainSearch(boolean p_displayDebug, String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}
		_utilHelper.setFocusOnElement(_utilBase, "find_desc",
				WebElementType.ID, p_stepInfo, _waitTimeDefault);
	}

	public void selectValueOnMainSearch(boolean p_displayDebug) {
		selectValueOnMainSearch(p_displayDebug,
				"Step 3. Search on selecting option",
				"//*[@id=\"header_find_form\"]/div/div[1]/div/div/ul/li[1]");
	}

	public void selectValueOnMainSearch(boolean p_displayDebug,
			int p_searchIndex) {
		selectValueOnMainSearch(p_displayDebug,
				"Step 3. Search on selecting option",
				"//*[@id=\"header_find_form\"]/div/div[1]/div/div/ul/li["
						+ p_searchIndex + "]");
	}

	public void selectValueOnMainSearch(boolean p_displayDebug,
			String p_stepInfo, String p_ref) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}
		_utilHelper.findAndClickOnElement(_utilBase, p_ref,
				WebElementType.XPATH, p_stepInfo, _waitTimeDefault);
	}

	public void setValueAndSearchOnNext(boolean p_displayDebug) {
		setValueAndSearchOnNext(
				p_displayDebug,
				"Step 4. Append Pizza to Restaurant in the search page before Find",
				"Restaurant Pizza");
	}

	public void setValueAndSearchOnNext(boolean p_displayDebug,
			String p_stepInfo, String p_textToSearch) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}
		_utilHelper.findAndClickOnElementWithValue(_utilBase,
				"//*[@id=\"find_desc\"]", WebElementType.XPATH, p_stepInfo,
				_waitTimeDefault, p_textToSearch);
	}

	public void clickFindAfterSettingNewSearchCriteria(boolean p_displayDebug) {
		clickFindAfterSettingNewSearchCriteria(p_displayDebug,
				"Step 4. Click Find button after setting search criteria");
	}

	public void clickFindAfterSettingNewSearchCriteria(boolean p_displayDebug,
			String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}
		_utilHelper.findAndClickOnElement(_utilBase,
				"//*[@id=\"header-search-submit\"]/span/span[1]",
				WebElementType.XPATH, p_stepInfo, _waitTimeDefault);
	}

	public void showCurrentUrl(boolean p_displayDebug) {
		String currUrl = _utilBase.get_driver().getCurrentUrl();
		if (p_displayDebug) {
			logIt("Current Url is : " + currUrl);
		}
	}

	public String reportTotalInitialSearchResults(boolean p_displayDebug) {
		return reportTotalInitialSearchResults(
				p_displayDebug,
				"Step 5. Report total no. of Search results with no. of results in the current page");
	}

	public String reportTotalInitialSearchResults(boolean p_displayDebug,
			String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}

		String totalResult = _utilHelper
				.getElementText(
						_utilBase,
						"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[1]/div/span",
						WebElementType.XPATH, p_stepInfo, _waitTimeDefault);

		logIt("Total no. of Search results  : " + totalResult);
		
		return totalResult;
	}

	public void expandAllFiltersToSelectParams(boolean p_displayDebug) {
		expandAllFiltersToSelectParams(p_displayDebug,
				"Step 6/7. a) Parameterize any 2 of the filtering parameters");
	}

	public void expandAllFiltersToSelectParams(boolean p_displayDebug,
			String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}

		_utilHelper
				.findAndClickOnElement(
						_utilBase,
						"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/ul/li[7]/label/span",
						WebElementType.XPATH, p_stepInfo, _waitTimeDefault);
	}

	public void applyOneSetOfParamsToFilterNeighborhood(boolean p_displayDebug,
			int[] p_indexes) {
		applyOneSetOfParamsToFilterNeighborhood(p_displayDebug, p_indexes,
				"Step 6/7. b) Applying filter : Neighborhood");
	}

	public void applyOneSetOfParamsToFilterNeighborhood(boolean p_displayDebug,
			int[] p_indexes, String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}

		// Neighborhood .. index 1
		for (int i : p_indexes) {
			_utilHelper
					.findAndClickOnElement(
							_utilBase,
							"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[2]/ul[1]/li["
									+ i + "]/label/input",
							WebElementType.XPATH, p_stepInfo + "from index "
									+ i, _waitTimeDefault);
		}
	}

	public void applyOneSetOfParamsToFilterDistance(boolean p_displayDebug,
			int[] p_indexes) {
		applyOneSetOfParamsToFilterDistance(p_displayDebug, p_indexes,
				"Step 6/7. c) Applying filter : Distance");
	}

	public void applyOneSetOfParamsToFilterDistance(boolean p_displayDebug,
			int[] p_indexes, String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}

		// Neighborhood .. index 1
		for (int i : p_indexes) {
			_utilHelper
					.findAndClickOnElement(
							_utilBase,
							"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[3]/ul/li["
									+ i + "]/label/span", WebElementType.XPATH,
							p_stepInfo + "from index " + i, _waitTimeDefault);
		}
	}

	public void applyOneSetOfParamsToFilterPrice(boolean p_displayDebug,
			int[] p_indexes) {
		applyOneSetOfParamsToFilterPrice(p_displayDebug, p_indexes,
				"Step 6/7. d) Applying filter : Price");
	}

	public void applyOneSetOfParamsToFilterPrice(boolean p_displayDebug,
			int[] p_indexes, String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}

		// Neighborhood .. index 1
		for (int i : p_indexes) {
			_utilHelper
					.findAndClickOnElement(
							_utilBase,
							"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[4]/ul/li["
									+ i + "]/label/input",
							WebElementType.XPATH, p_stepInfo + "from index "
									+ i, _waitTimeDefault);
		}
	}

	public void applyOneSetOfParamsToFilterCategory(boolean p_displayDebug,
			int[] p_indexes) {
		applyOneSetOfParamsToFilterCategory(p_displayDebug, p_indexes,
				"Step 6/7. d) Applying filter : Category");
	}

	public void applyOneSetOfParamsToFilterCategory(boolean p_displayDebug,
			int[] p_indexes, String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}

		// Neighborhood .. index 1
		for (int i : p_indexes) {
			_utilHelper
					.findAndClickOnElement(
							_utilBase,
							"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[6]/ul/li["
									+ i + "]/label/input",
							WebElementType.XPATH, p_stepInfo + "from index "
									+ i, _waitTimeDefault);
		}
	}

	public void applyOneSetOfParamsToFilterFeatures(boolean p_displayDebug,
			int[] p_indexes) {
		applyOneSetOfParamsToFilterFeatures(p_displayDebug, p_indexes,
				"Step 6/7. d) Applying filter : Features");
	}

	public void applyOneSetOfParamsToFilterFeatures(boolean p_displayDebug,
			int[] p_indexes, String p_stepInfo) {
		if (p_displayDebug) {
			logIt(">> " + p_stepInfo);
		}

		// Neighborhood .. index 1
		for (int i : p_indexes) {
			_utilHelper
					.findAndClickOnElement(
							_utilBase,
							"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[5]/ul/li["
									+ i + "]/label/input",
							WebElementType.XPATH, p_stepInfo + "from index "
									+ i, _waitTimeDefault);
		}
	}

	public int reportTotalFilteredSearchResults(boolean p_displayDebug) {
		if (p_displayDebug) {
			logIt(">> Step 8. Report total no. of Search results with no. of results in the current page");
		}

		String totalCount = _utilHelper
				.getElementText(
						_utilBase,
						"//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[1]/div/span",
						WebElementType.XPATH,
						"Get total filtered result count", _waitTimeDefault);

		logIt("Total no. of filtered results  : " + totalCount);

		totalCount = totalCount.substring(totalCount.indexOf("-") + 1);
		totalCount = totalCount.substring(0, totalCount.indexOf(" "));

		int totalResults = 0;
		try {
			totalResults = Integer.parseInt(totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalResults;
	}

	public void reportTotalFilteredSearchResults(boolean p_displayDebug,
			List<String> p_starRatingList, Map<String, Object> p_starRatingMap,
			Map<String, Object> p_valuesMap, int p_totalResults) {
		if (p_displayDebug) {
			logIt(">> Step 9. Report the star rating of each of the results in the first result page");
		}

		String displayLinkStarRatingPrefix = "//*[@id=\"super-container\"]/div/div[2]/div[1]/div/div[4]/ul[2]/li[";
		String displayLinkStarRatingSuffix = "]/div/div[1]/div[1]/div/div[2]/div[1]/div";

		String displayLinkPrefix = "//*[@id=\"super-container\"]/div/div[2]/div[1]/div/div[4]/ul[2]/li[";
		String displayLinkSuffix = "]/div/div[1]/div[1]/div/div[2]/h3/";
		String displayLinkSuffixSpan = "span";
		
		// These guys keep changing this with one of the 3 combinations !! .. freaks !! .. will need to find pattern on what to expect when
		// String displayLinkSuffixLink = "span/a";
		//String displayLinkSuffixLink = "a";
		//String displayLinkSuffixLink = "span/a/span";
		String displayLinkSuffixLink = "span/a";

		_utilHelper.getStarRating("Getting star rating of merchants",
				_utilBase, displayLinkStarRatingPrefix,
				displayLinkStarRatingSuffix, displayLinkPrefix,
				displayLinkSuffix, displayLinkSuffixSpan,
				displayLinkSuffixLink, WebElementType.XPATH, _waitTimeDefault,
				p_totalResults, p_starRatingList, p_starRatingMap, p_valuesMap);

		if (p_displayDebug) {
			logIt(p_starRatingList.toString());
			logIt(p_starRatingMap.toString());
		}

	}

	public void openOneLinkFromTheResults(boolean p_displayDebug,
			Map<String, Object> p_valuesMap) {
		if (p_displayDebug) {
			logIt(">> Step 10. Click and expand the first result from the search results by clicking on one result");
		}
		logIt(">> Step 10. Click and expand the first result from the search results\n\nClicking on firstElem : "
				+ p_valuesMap.get("firstElement"));

		((WebElement) p_valuesMap.get("firstElement")).click();

	}

	public void getMerchantDetailsFromTheResults(boolean p_displayDebug,
			Map<String, Object> p_keyElementValueInOutMap,
			Map<String, Object> p_valuesMap) {
		if (p_displayDebug) {
			logIt(">> Step 10. Click and expand the first result from the search results by clicking on one result");
		}

		List<String> keyElementNameList = new ArrayList<String>();
		Map<String, Object> keyConfigMap = new LinkedHashMap<String, Object>();
		boolean bconstructLookup = false;
		long lwaitBetweenExtract = _waitTimeDefault;

		keyElementNameList.add("merchant.Name");
		keyElementNameList.add("merchant.Address");
		keyElementNameList.add("merchant.Phone");
		keyElementNameList.add("merchant.Website");
		keyElementNameList.add("merchant.Review.1");
		keyElementNameList.add("merchant.Review.2");
		keyElementNameList.add("merchant.Review.3");

		keyConfigMap.put("merchant.Name.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Address.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Phone.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Website.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Review.1.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Review.2.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Review.3.keyType", WebElementType.XPATH);

		keyConfigMap
				.put("merchant.Name.keyCompleteRef",
						"//*[@id=\"wrap\"]/div[2]/div/div[1]/div/div[3]/div[1]/div[1]/h1");

		keyConfigMap
				.put("merchant.Address.keyCompleteRef",
						"//*[@id=\"wrap\"]/div[2]/div/div[1]/div/div[4]/div[1]/div/div[2]/ul/li[1]/div/strong/address");
		keyConfigMap
				.put("merchant.Phone.keyCompleteRef",
						"//*[@id=\"wrap\"]/div[2]/div/div[1]/div/div[4]/div[1]/div/div[2]/ul/li[3]/span[3]");
		keyConfigMap
				.put("merchant.Website.keyCompleteRef",
						"//*[@id=\"wrap\"]/div[2]/div/div[1]/div/div[4]/div[1]/div/div[2]/ul/li[4]/span[2]/a");

		// orig
		// keyConfigMap.put("merchant.Review.1.keyCompleteRef","//*[@id=\"super-container\"]/div/div/div[1]/div[1]/div[1]/ul/li[1]/div[2]/p");
		// keyConfigMap.put("merchant.Review.2.keyCompleteRef","//*[@id=\"super-container\"]/div/div/div[1]/div[1]/div[1]/ul/li[2]/div[2]/p");
		// keyConfigMap.put("merchant.Review.3.keyCompleteRef","//*[@id=\"super-container\"]/div/div/div[1]/div[1]/div[1]/ul/li[3]/div[2]/p");

		keyConfigMap
				.put("merchant.Review.1.keyCompleteRef",
						"//*[@id=\"super-container\"]/div/div/div[1]/div[4]/div[1]/div[2]/ul/li[2]/div/div[2]/div[1]/p");
		keyConfigMap
				.put("merchant.Review.2.keyCompleteRef",
						"//*[@id=\"super-container\"]/div/div/div[1]/div[4]/div[1]/div[2]/ul/li[3]/div/div[2]/div[1]/p");
		keyConfigMap
				.put("merchant.Review.3.keyCompleteRef",
						"//*[@id=\"super-container\"]/div/div/div[1]/div[4]/div[1]/div[2]/ul/li[4]/div/div[2]/div[1]/p");

		/*
		 * //*[@id=\
		 * "super-container\"]/div/div/div[1]/div[4]/div[1]/div[2]/ul/li[2]/div/div[2]/div[1]/p
		 * //*[@id=\
		 * "super-container\"]/div/div/div[1]/div[4]/div[1]/div[2]/ul/li[3]/div/div[2]/div[1]/p
		 * //*[@id=\
		 * "super-container\"]/div/div/div[1]/div[4]/div[1]/div[2]/ul/li[4]/div/div[2]/div[1]/p
		 */
		try {
			_utilHelper.getDetailsFromPageAsMap(_utilBase, keyElementNameList,
					keyConfigMap, p_keyElementValueInOutMap, bconstructLookup,
					lwaitBetweenExtract);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DisplayUtils.HashMapDumpObjVals(
				(HashMap<String, Object>) p_keyElementValueInOutMap,
				"Merchant Details");
	}

	public void captureScreenShot() {
		captureScreenShot ("default");
	}
	
	public void captureScreenShot(String p_refName) {
		File src = ((TakesScreenshot) _utilBase.get_driver())
				.getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			String fileName = "error_screenshot_" + p_refName + "_" + RandomInfoHelper.getRadomDateBaseNumber() + ".png";
			String filePath = System.getProperty("UseErrorFilePath");
			if (filePath != null && !filePath.trim().equals("")) {
				fileName = filePath + fileName;
				System.out.println("captureScreenShot:: modified full file name for error : " + fileName);
			} else {
				System.out.println("captureScreenShot:: default file name for error : " + fileName);
			}
			
			FileUtils.copyFile(src, new File(fileName));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Map<String, Object> keyElementValueInOutMap = new LinkedHashMap<String, Object>();
		List<String> starRatingList = new ArrayList<String>();
		Map<String, Object> starRatingMap = new LinkedHashMap<String, Object>();
		Map<String, Object> valuesMap = new LinkedHashMap<String, Object>();

		try {
			SeleniumUtilYelpMerchantDetailsTest thisTest = new SeleniumUtilYelpMerchantDetailsTest();
			thisTest.init(true);
			thisTest.navigateToUrl(true);
			thisTest.setFocusOnMainSearch(true);
			thisTest.selectValueOnMainSearch(true);
			thisTest.setValueAndSearchOnNext(true);
			thisTest.clickFindAfterSettingNewSearchCriteria(true);
			thisTest.showCurrentUrl(true);
			thisTest.reportTotalInitialSearchResults(true);
			thisTest.expandAllFiltersToSelectParams(true);
			thisTest.applyOneSetOfParamsToFilterNeighborhood(true, new int[] {1});
			thisTest.applyOneSetOfParamsToFilterDistance(true,new int[] { 2});
			int totalResults = thisTest.reportTotalFilteredSearchResults(true);

//			thisTest.applyOneSetOfParamsToFilterNeighborhood(true, new int[] {1, 3 });
//			thisTest.applyOneSetOfParamsToFilterDistance(true,new int[] { 1, 3 });

			int retryCount = 0;
			while (retryCount < 3) {
				try {
					thisTest.reportTotalFilteredSearchResults(true,
							starRatingList, starRatingMap, valuesMap,
							totalResults);

					retryCount = 3;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					retryCount++;
				}
			}

			thisTest.openOneLinkFromTheResults(true, valuesMap);
			thisTest.getMerchantDetailsFromTheResults(true,
					keyElementValueInOutMap, valuesMap);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// Take screen shot
			// Take screenshot and store as a file format
			File src = ((TakesScreenshot) _utilBase.get_driver())
					.getScreenshotAs(OutputType.FILE);
			try {
				// now copy the screenshot to desired location using copyFile
				// //method
				FileUtils.copyFile(src, new File("C:/selenium/error.png"));
			}

			catch (IOException e) {
				System.out.println(e.getMessage());

			}

			e1.printStackTrace();
		}
	}
}
