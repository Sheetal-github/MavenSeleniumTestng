package com.gohighlevel.web.selenium.dataProviders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;

	BufferedReader reader;

	/**
	 * reading from the properties file
	 */
	public ConfigFileReader(String propertyFilePath) {
		// System.out.println("Test " + System.getProperty("user.dir"));
		// System.out.println("Inside config reader");

		FileInputStream reader = null;
		try {
			reader = new FileInputStream(propertyFilePath);

			try {
				properties = new Properties();
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	/**
	 * reading property values based on the key
	 */
	public String getProperty(String propertyName) {
		if (propertyName != null) {
			return properties.getProperty(propertyName);
		} else
			throw new RuntimeException("Property:" + propertyName + " not found in configuration.properties");
	}
}
