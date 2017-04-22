package org.onn.webportal.application.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config extends  Properties{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(Config.class);

	private static Config config = null;

	private InputStream inputStream;

	private Config(){
		super();
		String propFileName = "application.properties";
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			try {
				load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		} else {
			logger.error("property file '" + propFileName + "' not found in the classpath");
		}
	}

	public static Config getInstance(){
		if(config==null){
			config = new Config();
		}
		return config;
	}


}
