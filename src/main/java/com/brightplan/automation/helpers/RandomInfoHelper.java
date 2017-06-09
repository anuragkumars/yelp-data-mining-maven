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

package com.brightplan.automation.helpers;

import java.util.Random;

import com.brightplan.automation.utils.common.CommonUtilsBase;

public class RandomInfoHelper extends CommonUtilsBase {

	/*
	 * Returns randomly generated number
	 */
	public static int generateRandomNumber() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100000);
		return randomInt;
	}

	/*
	 * Returns system time in milliseconds as generated number
	 */
	public static long getRadomDateBaseNumber () {
		return System.currentTimeMillis();
	}
}
