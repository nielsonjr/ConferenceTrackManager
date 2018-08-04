package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import exceptions.RequiredPropertyNotDefinedException;
import util.ConstantsApp;

public class Configuration {
	private static Properties properties = new Properties();
	private static Configuration instance = null;
	
	private Configuration() {}
	
	public static void init(String filePropertiesPath) {
		try {
			InputStream input = new FileInputStream(filePropertiesPath);

			properties.load(input);
			
			if(getSessionMinDuration() == null) {
				throw new RequiredPropertyNotDefinedException(ConstantsApp.SESSION_MIN_DURATION_PROPERTY);
			}
			
			if(getSessionMaxDuration() == null) {
				throw new RequiredPropertyNotDefinedException(ConstantsApp.SESSION_MAX_DURATION_PROPERTY);
			}
			
			if(getAlgorithm() == null) {
				throw new RequiredPropertyNotDefinedException(ConstantsApp.ALGORITHM_NAME);
			}
			
			if(getParser() == null) {
				throw new RequiredPropertyNotDefinedException(ConstantsApp.PARSER_NAME);
			}
			
			if(getTalksFilePath() == null) {
				throw new RequiredPropertyNotDefinedException(ConstantsApp.FILE_TALKS_PATH);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Configuration getInstance() {
		if(instance == null) {
			instance = new Configuration();
		}
		
		return instance;
	}
	public static String getProperty(String propName) {
		return properties.getProperty(propName);
	}
	
	public static Integer getSessionMinDuration() {
		return Integer.valueOf(properties.getProperty(ConstantsApp.SESSION_MIN_DURATION_PROPERTY));
	}
	
	public static Integer getSessionMaxDuration() {
		return Integer.valueOf(properties.getProperty(ConstantsApp.SESSION_MAX_DURATION_PROPERTY));
	}
	
	public static Integer getTrackMinDuration() {
		//morning session + afternoon session
		return 2 * getSessionMinDuration();
	}
	
	public static Integer getTrackMaxDuration() {
		//morning session + afternoon session
		return getSessionMinDuration() + getSessionMaxDuration();
	}
	
	public static String getAlgorithm() {
		return properties.getProperty(ConstantsApp.ALGORITHM_NAME);
	}
	
	public static String getParser() {
		return properties.getProperty(ConstantsApp.PARSER_NAME);
	}
	
	public static String getTalksFilePath() {
		return properties.getProperty(ConstantsApp.FILE_TALKS_PATH);
	}
}
