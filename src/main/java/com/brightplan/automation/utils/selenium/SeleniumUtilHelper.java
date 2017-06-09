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
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.brightplan.automation.helpers.RandomInfoHelper;
import com.brightplan.automation.utils.common.CommonUtilsBase;
import com.brightplan.automation.utils.selenium.SeleniumUtilBase.WebElementType;

public class SeleniumUtilHelper extends CommonUtilsBase {

	public void navigateToUrl(SeleniumUtilBase p_utilBaseRef, String p_url,
			String p_msgAtEntry, long p_waitBetweenExtract) {
		String thisMethod = "SeleniumUtilHelper::navigateToUrl";
		logIt(thisMethod + " - " + p_msgAtEntry);
		p_utilBaseRef.get_driver().get(p_url);
		try {
			Thread.sleep(p_waitBetweenExtract);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public String getElementText(SeleniumUtilBase p_utilBaseRef,
			String p_keyRef, WebElementType p_keyType, String p_msgAtEntry,
			long p_waitBetweenExtract) {
		String thisMethod = "SeleniumUtilHelper::getElementText";
		logIt(thisMethod + " - " + p_msgAtEntry);
		WebElement tempElem = getWebElement(p_utilBaseRef, p_keyRef, p_keyType,
				p_msgAtEntry, p_waitBetweenExtract);
		return tempElem.getText();
	}

	public void setFocusOnElement(SeleniumUtilBase p_utilBaseRef,
			String p_keyRef, WebElementType p_keyType, String p_msgAtEntry,
			long p_waitBetweenExtract) {
		String thisMethod = "SeleniumUtilHelper::setFocusOnElement";
		logIt(thisMethod + " - " + p_msgAtEntry);
		WebElement tempElem = getWebElement(p_utilBaseRef, p_keyRef, p_keyType,
				p_msgAtEntry, p_waitBetweenExtract);
		tempElem.clear();
	}

	public void findAndClickOnElement(SeleniumUtilBase p_utilBaseRef,
			String p_keyRef, WebElementType p_keyType, String p_msgAtEntry,
			long p_waitBetweenExtract, boolean p_clickAfterSettingValue,
			String p_setValue) {
		String thisMethod = "SeleniumUtilHelper::findAndClickOnElement";
		logIt(thisMethod + " - " + p_msgAtEntry);
		WebElement tempElem = getWebElement(p_utilBaseRef, p_keyRef, p_keyType,
				p_msgAtEntry, p_waitBetweenExtract);

		if (tempElem != null) {
			if (p_clickAfterSettingValue) {
				tempElem.click();
				tempElem.sendKeys(p_setValue);
			} else {
				tempElem.click();
			}
		} else {
			logIt(thisMethod
					+ " - null element found, not performing operation");
		}
	}

	public void findAndClickOnElement(SeleniumUtilBase p_utilBaseRef,
			String p_keyRef, WebElementType p_keyType, String p_msgAtEntry,
			long p_waitBetweenExtract) {
		String thisMethod = "SeleniumUtilHelper::findAndClickOnElement";
		logIt(thisMethod + " - " + p_msgAtEntry);
		findAndClickOnElement(p_utilBaseRef, p_keyRef, p_keyType, p_msgAtEntry,
				p_waitBetweenExtract, false, "");
	}

	public void findAndClickOnElementWithValue(SeleniumUtilBase p_utilBaseRef,
			String p_keyRef, WebElementType p_keyType, String p_msgAtEntry,
			long p_waitBetweenExtract, String p_setValue) {
		String thisMethod = "SeleniumUtilHelper::findAndClickOnElementWithValue";
		logIt(thisMethod + " - " + p_msgAtEntry);
		findAndClickOnElement(p_utilBaseRef, p_keyRef, p_keyType, p_msgAtEntry,
				p_waitBetweenExtract, true, p_setValue);
	}

	public WebElement getWebElement(SeleniumUtilBase p_utilBaseRef,
			String p_keyRef, WebElementType p_keyType, String p_msgAtEntry,
			long p_waitBetweenExtract) {
		String thisMethod = "SeleniumUtilHelper::getWebElement";
		logIt(thisMethod + " - " + p_msgAtEntry);

		WebElement tempElem = null;

		try {
			switch (p_keyType) {
			case ID:
				tempElem = WebElementType.ID.getElement(
						p_utilBaseRef.get_driver(), p_keyRef.toString(),
						p_waitBetweenExtract);
				break;
			case CLASSNAME:
				tempElem = WebElementType.CLASSNAME.getElement(
						p_utilBaseRef.get_driver(), p_keyRef.toString(),
						p_waitBetweenExtract);
				break;
			case NAME:
				tempElem = WebElementType.NAME.getElement(
						p_utilBaseRef.get_driver(), p_keyRef.toString(),
						p_waitBetweenExtract);
				break;
			case TAGNAME:
				tempElem = WebElementType.TAGNAME.getElement(
						p_utilBaseRef.get_driver(), p_keyRef.toString(),
						p_waitBetweenExtract);
				break;
			case CSSSELECTOR:
				tempElem = WebElementType.CSSSELECTOR.getElement(
						p_utilBaseRef.get_driver(), p_keyRef.toString(),
						p_waitBetweenExtract);
				break;
			case LINKTEXT:
				tempElem = WebElementType.LINKTEXT.getElement(
						p_utilBaseRef.get_driver(), p_keyRef.toString(),
						p_waitBetweenExtract);
				break;
			case XPATH:
				tempElem = WebElementType.XPATH.getElement(
						p_utilBaseRef.get_driver(), p_keyRef.toString(),
						p_waitBetweenExtract);
				break;
			default:
				tempElem = null;
				break;
			}

		} catch (Exception e) {
			logIt(thisMethod + " - caught exception, message ("
					+ e.getMessage() + ")");
			e.printStackTrace();
		}

		return tempElem;
	}

	public void getDetailsFromPageAsMap(SeleniumUtilBase p_utilBaseRef,
			List<String> p_keyElementNameList,
			Map<String, Object> p_keyConfigMap,
			Map<String, Object> p_keyElementValueInOutMap,
			boolean p_constructLookup, long p_waitBetweenExtract)
			throws Exception {

		String thisMethod = "SeleniumUtilHelper::getDetailsFromPageAsMap";

		StringBuilder sbKeyName = new StringBuilder();
		WebElementType weKeyType;
		StringBuilder sbKeyPrefix = new StringBuilder();
		StringBuilder sbKeySuffix = new StringBuilder();
		StringBuilder sbKeyValue = new StringBuilder();
		StringBuilder sbKeyCompleteRef = new StringBuilder();

		// Loop through the list of elements
		for (int iElemIdx = 1; iElemIdx <= p_keyElementNameList.size(); iElemIdx++) {
			// Get Key Name
			sbKeyName.append(p_keyElementNameList.get(iElemIdx - 1));
			String strKey = sbKeyName.toString();

			logIt(thisMethod + " - Got key constructed as (" + strKey + ")");

			// sbKeyType.append ((String) p_keyConfigMap.get(strKey +
			// ".keyType"));
			weKeyType = (WebElementType) p_keyConfigMap
					.get(strKey + ".keyType");

			// Construct path or location if requested, mostly for same type of
			// links etc.
			if (p_constructLookup) {
				sbKeyPrefix.append((String) p_keyConfigMap.get(strKey
						+ ".keyPrefix"));
				sbKeyPrefix.append((String) p_keyConfigMap.get(strKey
						+ ".keyPrefix"));
				sbKeyCompleteRef.append(sbKeyPrefix.toString() + iElemIdx
						+ sbKeyPrefix.toString());
			} else {
				sbKeyCompleteRef.append((String) p_keyConfigMap.get(strKey
						+ ".keyCompleteRef"));
			}

			logIt(thisMethod + " - Got keyRed constructed as ("
					+ sbKeyCompleteRef.toString() + ")");

			WebElement tempElem1 = null;

			try {
				switch (weKeyType) {
				case ID:
					tempElem1 = WebElementType.ID.getElement(
							p_utilBaseRef.get_driver(),
							sbKeyCompleteRef.toString(), p_waitBetweenExtract);
					break;
				case CLASSNAME:
					tempElem1 = WebElementType.CLASSNAME.getElement(
							p_utilBaseRef.get_driver(),
							sbKeyCompleteRef.toString(), p_waitBetweenExtract);
					break;
				case NAME:
					tempElem1 = WebElementType.NAME.getElement(
							p_utilBaseRef.get_driver(),
							sbKeyCompleteRef.toString(), p_waitBetweenExtract);
					break;
				case TAGNAME:
					tempElem1 = WebElementType.TAGNAME.getElement(
							p_utilBaseRef.get_driver(),
							sbKeyCompleteRef.toString(), p_waitBetweenExtract);
					break;
				case CSSSELECTOR:
					tempElem1 = WebElementType.CSSSELECTOR.getElement(
							p_utilBaseRef.get_driver(),
							sbKeyCompleteRef.toString(), p_waitBetweenExtract);
					break;
				case LINKTEXT:
					tempElem1 = WebElementType.LINKTEXT.getElement(
							p_utilBaseRef.get_driver(),
							sbKeyCompleteRef.toString(), p_waitBetweenExtract);
					break;
				case XPATH:
					tempElem1 = WebElementType.XPATH.getElement(
							p_utilBaseRef.get_driver(),
							sbKeyCompleteRef.toString(), p_waitBetweenExtract);
					break;
				default:
					tempElem1 = null;
					break;
				}

				logIt(thisMethod + " - Saving key (" + strKey + ") value ("
						+ tempElem1.getText() + ")");
				p_keyElementValueInOutMap.put(strKey, tempElem1.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logIt(thisMethod + " - caught exception for key (" + strKey
						+ ") message (" + e.getMessage() + ")");
				e.printStackTrace();
			}

			// Reset for next iteration
			sbKeyName = new StringBuilder();
			weKeyType = null;
			sbKeyPrefix = new StringBuilder();
			sbKeySuffix = new StringBuilder();
			sbKeyValue = new StringBuilder();
			sbKeyCompleteRef = new StringBuilder();
		}
	}

	public void getStarRating(String p_msgAtEntry,
			SeleniumUtilBase p_utilBaseRef,
			String p_displayLinkStarRatingPrefix,
			String p_displayLinkStarRatingSuffix, String p_displayLinkPrefix,
			String p_displayLinkSuffix, String p_displayLinkSuffixSpan,
			String p_displayLinkSuffixLink, WebElementType p_keyType,
			long p_waitBetweenExtract, int p_loopCount,
			List<String> p_starRatingList, 
			Map<String, Object> p_starRatingMap,
			Map<String, Object> p_valuesMap) {

		logIt("Total times to loop : " + p_loopCount);
		for (int i = 1; i <= p_loopCount; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(p_displayLinkStarRatingPrefix + i
					+ p_displayLinkStarRatingSuffix);
			logIt("Looking for (" + i + ") xpath (" + sb.toString() + ")");
			WebElement tempElem = WebElementType.XPATH.getElement(
					p_utilBaseRef.get_driver(), sb.toString(),
					p_waitBetweenExtract);
			if (tempElem.getText() != null) {
				logIt("Adding rating for index (" + i + ") tag ("
						+ tempElem.getTagName() + ") to list as ("
						+ tempElem.getAttribute("title") + ")");
				p_starRatingList.add(tempElem.getAttribute("title"));
			}

			StringBuilder sb1 = new StringBuilder();
			sb1.append(p_displayLinkPrefix + i + p_displayLinkSuffix
					+ p_displayLinkSuffixSpan);

			WebElement tempElem1 = WebElementType.XPATH.getElement(
					p_utilBaseRef.get_driver(), sb1.toString(),
					p_waitBetweenExtract);
			logIt("Looking for link name (" + i + ") (" + tempElem1.getText()
					+ ") xpath (" + sb1.toString() + ")");

			p_starRatingMap.put(tempElem1.getText(),
					tempElem.getAttribute("title"));

			if (i == 1) {
				StringBuilder sb2 = new StringBuilder();
				sb2.append(p_displayLinkPrefix + i + p_displayLinkSuffix
						+ p_displayLinkSuffixLink);
				logIt("Looking for link url (" + i + ") xpath ("
						+ sb2.toString() + ") with wait time ("+p_waitBetweenExtract+")");
				p_valuesMap.put("firstElement",WebElementType.XPATH.getElement(
						p_utilBaseRef.get_driver(), sb2.toString(),
						p_waitBetweenExtract));
			}
		}
	}
	
	public void captureScreenShot(SeleniumUtilBase p_utilBaseRef, String p_refName) {
		File src = ((TakesScreenshot) p_utilBaseRef.get_driver())
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

	
}
