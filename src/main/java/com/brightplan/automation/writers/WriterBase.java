
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

package com.brightplan.automation.writers;

import java.io.File;
import java.io.FileWriter;

import com.brightplan.automation.utils.common.CommonUtilsBase;

public class WriterBase extends CommonUtilsBase {

	private String _fileName;
	private File _fileOut;
	private FileWriter _writer;
	private String _strStatus;
	
	// ------ Getters / Setters
	public String get_fileName() {
		return _fileName;
	}
	public void set_fileName(String _fileName) {
		this._fileName = _fileName;
	}
	public File get_fileOut() {
		return _fileOut;
	}
	public void set_fileOut(File _fileOut) {
		this._fileOut = _fileOut;
	}
	public FileWriter get_writer() {
		return _writer;
	}
	public void set_writer(FileWriter _writer) {
		this._writer = _writer;
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
		// override
	}

	public void writeOutPut(String outputString) throws Exception {
		// override
	}
	
	public void teardown() throws Exception {
		if (_writer != null) {
			_writer.flush();
			_writer.close();
			_writer = null;
			_fileOut = null;
		}
	}
}
