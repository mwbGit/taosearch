package com.taosearch.util;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtils {
	private static Properties prop=null;
	private ConfigUtils() {
	}
	static{
		try {
			prop=new Properties();
			prop.load(new FileInputStream(ConfigUtils.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProp() {
		return prop;
	}
	
	public static String getProp(String key) {
		return prop.getProperty(key);
	}
}
