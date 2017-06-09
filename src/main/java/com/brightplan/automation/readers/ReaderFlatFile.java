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

package com.brightplan.automation.readers;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReaderFlatFile extends ReaderBase
{
	// default
	public ReaderFlatFile () {
		
	}
	
	public ReaderFlatFile (String fileName) {
		set_fileName(fileName);
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ------ Local use methods
	@Override
	void init() throws Exception {
		set_reader (new BufferedReader(new FileReader(get_fileName())));
	}


	// ------ public use methods
	@Override
	public Object getNextInput() throws Exception {
		return stripSpaces(get_reader().readLine());
	}
}
