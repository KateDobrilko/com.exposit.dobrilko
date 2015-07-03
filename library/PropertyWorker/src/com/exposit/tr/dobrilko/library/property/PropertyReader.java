package com.exposit.tr.dobrilko.library.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyReader {
	public final static String CONFIG_PATH = "src/com/exposit/tr/dobrilko/library/resourses/config.properties";

	private static Logger logger = Logger.getLogger(PropertyReader.class);

	public static void setProperties(Properties p) {
		try {
			FileInputStream fis = new FileInputStream(CONFIG_PATH);
			p.load(fis);

		} catch (FileNotFoundException e) {

			logger.error("File not found!", e);
		} catch (IOException e) {

			logger.error("IO Exception has been detected!", e);
		}

	}
}
