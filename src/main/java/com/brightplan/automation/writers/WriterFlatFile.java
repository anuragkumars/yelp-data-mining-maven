package com.brightplan.automation.writers;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class WriterFlatFile extends WriterBase {

	public WriterFlatFile() {
		
	}
	
	public WriterFlatFile(String fileName) {
		set_fileName(fileName);
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ------ Getters / Setters


	// ------ Local use methods
	@Override
	void init() throws Exception {
		set_fileOut (new File(get_fileName()));
		get_fileOut().createNewFile();
		set_writer (new FileWriter(get_fileOut()));
	}

	// ------ public use methods
	@Override
	public void writeOutPut(String outputString) throws Exception {
		get_writer().write(outputString + "\n");
		get_writer().flush();
	}

	public void writeOutPut(List<String> outputList) throws Exception {
		for (String str : outputList) {
			writeOutPut(str);
		}
	}

}
