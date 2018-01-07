package com.ewb.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

public class CustomFileLogger {
	private Logger logger;
	private static CustomFileLogger instance;

	private CustomFileLogger() {

	}

	public static synchronized CustomFileLogger getInstance() {
		if (instance == null) {
			instance = new CustomFileLogger();
		}
		return instance;
	}

	public Logger getLogger(String className) {
		if (className == null) {
			className = "";
		}
		logger = LoggerFactory.getLogger(className);
		// if (fileappender == null) {
		// fileappender = getFileAppender();
		// }
		// logger.addAppender(fileappender);
		// if (consoleAppender == null) {
		// consoleAppender = new ConsoleAppender(new
		// PatternLayout(LoggerConstants.LOG_LAYOUT_PATTERN));
		// }
		// logger.addAppender(consoleAppender);
		// logger.setLevel(Level.DEBUG);
		return logger;
	}

	// private FileAppender getFileAppender() {
	// PatternLayout layout = new
	// PatternLayout(LoggerConstants.LOG_LAYOUT_PATTERN);
	// String logLocation = getLogLocation();
	// logLocation = logLocation == null || logLocation.isEmpty() ?
	// LoggerConstants.DEFAULT_LOG_LOCATION : logLocation;
	// try {
	// File logPathFile = new File(logLocation);
	// if (!logPathFile.exists()) {
	// logPathFile.mkdirs();
	// }
	// String filename;
	// if (this.logFileName == null || this.logFileName.isEmpty()) {
	// filename = logLocation + "/" + LoggerConstants.LOG_FILENAME;
	// } else {
	// filename = logLocation + "/" + this.logFileName;
	// }
	// RollingFileAppender appender = new RollingFileAppender(layout, filename,
	// true);
	// appender.setName(LoggerConstants.LOG_APPENDER_NAME);
	// appender.setBufferedIO(false);
	// appender.activateOptions();
	// return appender;
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// public void setLogFileName(String filename) {
	// this.logFileName = filename;
	// }
	//
	// public String getLogFileName() {
	// return this.logFileName;
	// }

	// public static String getLogLocation() {
	// String jobConfLocation = System.getProperty(LOG_LOCATION_SYSPROPERTY);
	// return jobConfLocation;
	// }
}
