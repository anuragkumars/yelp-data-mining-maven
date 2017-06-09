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

package com.brightplan.automation.readers;

import java.io.BufferedReader;

import com.brightplan.automation.utils.common.CommonUtilsBase;

public class ReaderBase extends CommonUtilsBase {

	private String _fileName;
	private BufferedReader _reader = null;
	private String _strStatus;

	// ------ Getters / Setters
	public String get_fileName() {
		return _fileName;
	}

	public void set_fileName(String _fileName) {
		this._fileName = _fileName;
	}

	public BufferedReader get_reader() {
		return _reader;
	}

	public void set_reader(BufferedReader _reader) {
		this._reader = _reader;
	}

	@Override
	public String get_strStatus() {
		return _strStatus;
	}

	@Override
	public void set_strStatus(String _strStatus) {
		this._strStatus = _strStatus;
	}

	// ------ Misc methods
	void init() throws Exception {
		// to be overwritten
	}

	public Object getNextInput() throws Exception {
		// to be overwritten
		return null;
	}

	public void teardown() throws Exception {
		if (_reader != null) {
			_reader.close();
			_reader = null;
		}
	}
}
