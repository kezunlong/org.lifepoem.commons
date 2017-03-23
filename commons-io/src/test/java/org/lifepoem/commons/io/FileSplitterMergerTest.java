package org.lifepoem.commons.io;

import java.io.IOException;

public class FileSplitterMergerTest {

	public static void main(String[] args) throws IOException {
		testFileSplitter();
		testFileMerger();
	}

	private static void testFileMerger() throws IOException {
		FileMerger merger = new FileMerger();
		String propertiesFile = "D:\\Java\\workspace\\partfiles1\\desktop.properties";
		merger.MergeFiles(propertiesFile);
	}

	private static void testFileSplitter() throws IOException {
		String sourceFile = "D:\\Java\\workspace\\desktop.bmp";
		String targetDirectory = "D:\\Java\\workspace\\partfiles1";
		FileSplitter splitter = new FileSplitter(sourceFile, targetDirectory);
		splitter.setCreatePropertiesFile(true);
		splitter.SplitFile();
	}
}
