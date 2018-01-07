package com.ewb.config.init;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

import com.ewb.logger.CustomFileLogger;

public enum ConfigFileLoader {

	CONFIG;

	private static final Logger LOGGER = CustomFileLogger.getInstance().getLogger(ConfigFileLoader.class.getName());
	private Properties configProperties;

	public Properties readProperties(String filename) {
		Properties properties = new Properties();
		if (filename == null || filename.isEmpty()) {
			throw new RuntimeException("File name is empty.");
		}
		File propFile = new File(filename);
		if (!(propFile.exists() && propFile.isFile())) {
			throw new RuntimeException(filename + " file does not exists or is not a file");
		}
		try {
			properties.load(new FileReader(propFile));
			LOGGER.debug("Properties: " + properties);
		} catch (IOException exp) {
			LOGGER.error("Error occured while loading properties from file " + filename, exp);
			throw new RuntimeException(exp.getMessage());
		}
		return properties;
	}

	public void writeProperties(Properties properties, String filename) {
		if (filename == null || filename.isEmpty()) {
			throw new RuntimeException("File name is empty.");
		}
		File propFile = new File(filename);
		if (!propFile.getParentFile().exists()) {
			LOGGER.error("Path is incorrect: " + propFile.getAbsolutePath());
			return;
		}
		try {
			properties.store(new FileWriter(propFile), "No Comments");
		} catch (IOException exp) {
			LOGGER.error("Error occured while loading properties from file " + filename, exp);
			throw new RuntimeException(exp.getMessage());
		}
	}

	public void loadConfigProperties() {
		String filename = System.getProperty(ConfigConstants.CONFIG_FILE_PROPERTY.getConfigProperty());
		if (filename == null || filename.isEmpty()) {
			throw new RuntimeException(
					" System property is missing: " + ConfigConstants.CONFIG_FILE_PROPERTY.getConfigProperty());
		}
		configProperties = readProperties(filename);
	}

	public Properties getConfigProperties() {
		if (configProperties == null) {
			loadConfigProperties();
		}
		return (Properties) configProperties.clone();
	}

	public String getConfigPropertyValue(String property) {
		if (configProperties != null) {
			return configProperties.getProperty(property);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Object getConfigPropertyValue(ConfigConstants configProperty) {
		if (configProperties == null || configProperties.isEmpty()) {
			throw new RuntimeException(" configProperties is null or empty");
		}
		String value = configProperties.getProperty(configProperty.getConfigProperty());
		if (value == null || value.isEmpty()) {
			if (configProperty.isMandatory()) {
				throw new RuntimeException(
						configProperty.getConfigProperty() + " property is mandatory and is not defined");
			} else {
				value = configProperty.getDefaultValue();
			}
		}
		Object valueObj = value;
		try {
			Class valueClass = configProperty.getValueClass();
			if (valueClass.equals(String.class)) {
				valueObj = value;
			} else if (valueClass.equals(Integer.class)) {
				valueObj = Integer.parseInt(value);
			} else if (valueClass.equals(Long.class)) {
				valueObj = Long.parseLong(value);
			} else if (valueClass.equals(Float.class)) {
				valueObj = Float.parseFloat(value);
			} else if (valueClass.equals(Boolean.class)) {
				valueObj = Boolean.parseBoolean(value);
			} else {
				throw new RuntimeException("Unsupported value class " + valueClass);
			}
		} catch (NumberFormatException exp) {
			LOGGER.error(configProperty.getConfigProperty() + " property value is invalid", exp);
			if (configProperty.isMandatory()) {
				throw new RuntimeException(configProperty.getConfigProperty() + " property value is invalid");
			}
		}
		return valueObj;
	}

}
