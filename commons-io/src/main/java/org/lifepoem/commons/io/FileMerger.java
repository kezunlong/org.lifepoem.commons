package org.lifepoem.commons.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;


/**
 * A tool class used to merge files
 * 
 * Usage:
 * 		FileMerger merger = new FileMerger();
 * 		String propertiesFile = "D:\\Java Workspaces\\partfiles1\\desktop_7.properties";
 * 		merger.MergeFiles(propertiesFile);
 * 
 * @author irfgoy
 *
 */
public class FileMerger {
	public static final String SOURCE_FILE_NAME = "{source_file_name}";
	public static final String SEQUENCE_ID = "{sequence_id}";
	
	public static final String PROP_PATTERN = "target_file_name_pattern";
	public static final String PROP_FILE_COUNT = "file_count";
	public static final String PROP_SOURCE_FILE_NAME = "source_file_name";
	
	public void MergeFiles(List<String> partialFiles, String targetFile) throws IOException {
		List<InputStream> partialStreams = new ArrayList<InputStream>();
		for(String partialFile : partialFiles) {
			partialStreams.add(new FileInputStream(partialFile));
		}
		Enumeration<InputStream> enumeration = Collections.enumeration(partialStreams);
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		FileOutputStream fos = new FileOutputStream(targetFile);
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = sis.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
		}
		
		sis.close();
		fos.close();
	}
	
	public void MergeFiles(String propertiesFile) throws IOException {
		String propertiesDirectory = new File(propertiesFile).getParent();
		MergeFiles(propertiesFile, propertiesDirectory);
	}
	
	public void MergeFiles(String propertiesFile, String targetDirectory) throws IOException {
		FileInputStream fis = new FileInputStream(propertiesFile);
		Properties properties = new Properties();
		properties.load(fis);
		fis.close();
		
		String propPattern = properties.getProperty(PROP_PATTERN);
		String propFileCount = properties.getProperty(PROP_FILE_COUNT);
		String propSourceFileName = properties.getProperty(PROP_SOURCE_FILE_NAME);
		int count = Integer.parseInt(propFileCount);
		String sourceBaseName = FilenameUtils.getBaseName(propSourceFileName);
		String propertiesDirectory = new File(propertiesFile).getParent();
		
		List<String> partialFiles = new ArrayList<String>();
		for(int i = 1; i <= count; i++) {
			String partialFile = propPattern.replace(SOURCE_FILE_NAME, sourceBaseName);
			partialFile = partialFile.replace(SEQUENCE_ID, String.valueOf(i));
			partialFile = new File(propertiesDirectory, partialFile).getPath();
			partialFiles.add(partialFile);
		}
		
		File targetFile = new File(targetDirectory, propSourceFileName);
		MergeFiles(partialFiles, targetFile.getPath());
	}
	
}