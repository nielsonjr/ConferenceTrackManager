package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalTime;
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

			init(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void init(InputStream input) throws IOException {
		properties.load(input);
		
		if(getConferenceBeginTime() == null) {
			throw new RequiredPropertyNotDefinedException(ConstantsApp.CONFERENCE_BEGIN_TIME);
		}
		
		if(getConferenceEndTime() == null) {
			throw new RequiredPropertyNotDefinedException(ConstantsApp.CONFERENCE_END_BETWEEN);
		}
		
		if(getConferenceLunchTime() == null) {
			throw new RequiredPropertyNotDefinedException(ConstantsApp.CONFERENCE_LUNCH_TIME);
		}
		
		if(getConferenceLunchDuration() == null) {
			throw new RequiredPropertyNotDefinedException(ConstantsApp.CONFERENCE_LUNCH_DURATION);
		}
		
		if(getAlgorithm() == null) {
			throw new RequiredPropertyNotDefinedException(ConstantsApp.GENERATOR_NAME);
		}
		
		if(getParser() == null) {
			throw new RequiredPropertyNotDefinedException(ConstantsApp.PARSER_NAME);
		}
		
		if(getTalksFilePath() == null) {
			throw new RequiredPropertyNotDefinedException(ConstantsApp.FILE_TALKS_PATH);
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
	
	public static String getConferenceBeginTime() {
		return properties.getProperty(ConstantsApp.CONFERENCE_BEGIN_TIME);
	}
	
	public static String getConferenceLunchTime() {
		return properties.getProperty(ConstantsApp.CONFERENCE_LUNCH_TIME);
	}
	
	public static String getConferenceLunchDuration() {
		return properties.getProperty(ConstantsApp.CONFERENCE_LUNCH_DURATION);
	}
	
	public static String getConferenceEndTime() {
		return properties.getProperty(ConstantsApp.CONFERENCE_END_BETWEEN);
	}
	
	public static long getSessionMorningDuration() {
		//in minutes		
		LocalTime startTime = LocalTime.parse(getConferenceBeginTime());
		LocalTime lunchTime = LocalTime.parse(getConferenceLunchTime());
		
		return Duration.between(startTime, lunchTime).toMinutes();
	}
	
	public static long getSessionAfternoonMinDuration() {
		LocalTime startAfternoonTime = LocalTime.parse(getConferenceLunchTime()).plusMinutes(Long.parseLong(getConferenceLunchDuration()));
		LocalTime minAfternoonTime = LocalTime.parse(getConferenceEndTime().split("-")[0]);
		
		return Duration.between(startAfternoonTime, minAfternoonTime).toMinutes();
	}
	
	public static long getSessionAfternoonMaxDuration() {
		LocalTime startAfternoonTime = LocalTime.parse(getConferenceLunchTime()).plusMinutes(Long.parseLong(getConferenceLunchDuration()));
		LocalTime maxAfternoonTime = LocalTime.parse(getConferenceEndTime().split("-")[1]);
		
		return Duration.between(startAfternoonTime, maxAfternoonTime).toMinutes();
	}
	
	public static long getTrackMinDuration() {
		//morning session + afternoon session
		return getSessionMorningDuration() + getSessionAfternoonMinDuration();
	}
	
	public static long getTrackMaxDuration() {
		//morning session + afternoon session
		return getSessionMorningDuration() + getSessionAfternoonMaxDuration();
	}
	
	public static LocalTime getNetworkMeetingBeginTime() {
		LocalTime netMeetingStartTime = LocalTime.parse(getConferenceEndTime().split("-")[0]);
		
		return netMeetingStartTime;
	}
	
	public static LocalTime getNetworkMeetingEndTime() {
		LocalTime netMeetingEndTime = LocalTime.parse(getConferenceEndTime().split("-")[1]);
		
		return netMeetingEndTime;
	}
	
	public static String getAlgorithm() {
		return properties.getProperty(ConstantsApp.GENERATOR_NAME);
	}
	
	public static String getParser() {
		return properties.getProperty(ConstantsApp.PARSER_NAME);
	}
	
	public static String getTalksFilePath() {
		return properties.getProperty(ConstantsApp.FILE_TALKS_PATH);
	}
	
	public static void main(String[] args) {
		LocalTime startTime = LocalTime.parse("09:00 am");
		LocalTime lunchTime = LocalTime.parse("12:00");
		
		System.out.println(Duration.between(startTime, lunchTime).toMinutes());
	}
}
