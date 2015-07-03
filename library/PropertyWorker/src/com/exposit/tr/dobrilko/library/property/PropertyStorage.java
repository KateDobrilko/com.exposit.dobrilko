package com.exposit.tr.dobrilko.library.property;

import java.util.Properties;

public class PropertyStorage {

	private static Properties properties = new Properties();
	private static PropertyStorage instance;

	private PropertyStorage() {
		PropertyReader.setProperties(properties);
	}

	public static PropertyStorage getInstance() {
		if (instance == null) {
			instance = new PropertyStorage();
		}

		return instance;

	}

	public Properties getProperties() {
		return properties;
	}

	public String getBookshopControllerName() {

		return properties.getProperty("LIBRARY_INDEX_SYSTEM_CONTROLLER_CLASS");
	}

}
