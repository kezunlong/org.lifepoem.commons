package org.lifepoem.commons.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

/**
 * A tool class used to split files
 * 
 * Usage:
 * 		String sourceFile = "D:\\Java Workspaces\\desktop.bmp";
 * 		String targetDirectory = "D:\\Java Workspaces\\partfiles1";
 * 		FileSplitter splitter = new FileSplitter(sourceFile, targetDirectory);
 * 		splitter.setCreatePropertiesFile(true);
 * 		splitter.SplitFile();
 * 
 * @author irfgoy
 *
 */
public class FileSplitter {
	public static final String SOURCE_FILE_NAME = "{source_file_name}";
	public static final String SEQUENCE_ID = "{sequence_id}";
	
	public FileSplitter(String sourceFilePath, String targetDirectory) {
		this.sourceFilePath = sourceFilePath;
		this.targetDirectory = targetDirectory;
	}
	
	/*
	 * Source file to split
	 */
	private String sourceFilePath;
	
	/*
	 * Target directory to store the splitted files
	 */
	private String targetDirectory;
	
	/*
	 * Default buffer/file size: 1M
	 */
	private int size = 1024 * 1024;
	
	/*
	 * Target file name pattern and it can include variables: {sourceFileName}, {sequence_id}
	 */
	private String targetFileNamePattern = SOURCE_FILE_NAME + "_" + SEQUENCE_ID + ".part";
	/*
	 * Whether to create properties file, the properties file can be used to merge the part files
	 */
	private boolean isCreatePropertiesFile = false;
	

	public boolean isCreatePropertiesFile() {
		return isCreatePropertiesFile;
	}

	public void setCreatePropertiesFile(boolean isCreatePropertiesFile) {
		this.isCreatePropertiesFile = isCreatePropertiesFile;
	}

	/*
	 * Get the size limitation of the split file (bytes)
	 */
	public int getSize() {
		return size;
	}

	/*
	 * Set the size limitation of the split file (bytes)
	 */
	public void setSize(int size) throws Exception {
		if (size < 1024) {
			throw new Exception("File size can't be less than 1K.");
		}
		this.size = size;
	}

	public String getTargetFileNamePattern() {
		return targetFileNamePattern;
	}

	public void setTargetFileNamePattern(String targetFileNamePattern) throws Exception {	
		if(!targetFileNamePattern.contains(SEQUENCE_ID)) {
			throw new Exception("Target file name pattern should include the sequence id.");
		}
		this.targetFileNamePattern = targetFileNamePattern;
	}

	public void SplitFile() throws IOException {
		FileInputStream fis = new FileInputStream(sourceFilePath);
		FileOutputStream fos = null;
		byte[] buffer = new byte[size];
		int len = 0;
		int count = 1;

		File dir = new File(targetDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(sourceFilePath);
		String sourceFileName = file.getName();
		String sourceBaseName = FilenameUtils.getBaseName(sourceFileName);
		
		while ((len = fis.read(buffer)) != -1) {
			String targetFile = getTargetFileName(sourceBaseName, count);
			fos = new FileOutputStream(new File(dir, targetFile));
			fos.write(buffer, 0, len);
			fos.close();
			count++;
		}
		
		if(isCreatePropertiesFile) {
			createPropertiesFile(sourceFileName, count - 1);
		}

		fis.close();
	}
	
	private void createPropertiesFile(String sourceFileName, int count) throws IOException {
		String sourceBaseName = FilenameUtils.getBaseName(sourceFileName);
		String propertiesFileName = sourceBaseName + ".properties";
		File propertiesFile = new File(targetDirectory, propertiesFileName);
		FileOutputStream fos = new FileOutputStream(propertiesFile);
		
		Properties properties = new Properties();
		properties.setProperty("source_file_name", sourceFileName);
		properties.setProperty("file_count", String.valueOf(count));
		properties.setProperty("target_file_name_pattern", targetFileNamePattern);
		
		properties.store(fos, "");
		fos.close();
	}

	private String getTargetFileName(String sourceFileName, int count) {
		String result = targetFileNamePattern;
		
		result = result.replace(SOURCE_FILE_NAME, sourceFileName);
		result = result.replace(SEQUENCE_ID, String.valueOf(count));
		
		return result;
	}
	
}