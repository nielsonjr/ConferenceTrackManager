package schedule;

import model.CommomTalk;
import model.Conference;
import model.Session;
import model.Track;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import exceptions.ConferenceTrackManagerException;
import exceptions.NetworkMeetingTimeInvalidException;
import algorithms.Scheduler;

public class NetworkMeetingTimeTest {
	private static Conference validConference = new Conference();
	private static Conference invalidConference = new Conference();
	
	@BeforeClass
	public static void loadTheValidConference() {
		Session morningSessionForTrack = new Session();
		
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk1", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk2", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk3", 60));
		
		Session afternoonSessionForTrack = new Session();
		
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk4", 30));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk5", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk6", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk7", 60));
		Track track1 = new Track(morningSessionForTrack, afternoonSessionForTrack);
		
		validConference.getTracks().add(track1);
	}
	
	@BeforeClass
	public static void loadTheInvalidConference() {
		Session morningSessionForTrack = new Session();
		
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk1", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk2", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk3", 60));
		
		Session afternoonSessionForTrack = new Session();
		
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk4", 30));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk5", 45));
		Track track1 = new Track(morningSessionForTrack, afternoonSessionForTrack);
		
		invalidConference.getTracks().add(track1);
	}
	
	@Test
	public void scheduleAValidConference() {
		Scheduler scheduler = Scheduler.getInstance();
		try {
			scheduler.scheduleTheConference(validConference);
		} catch (ConferenceTrackManagerException e) {
			System.err.println("An unexpected exception occurs:" );
			e.printStackTrace();
		}
	}
	
	@Test(expected=ConferenceTrackManagerException.class)
	public void scheduleAnInvalidValidConference() throws ConferenceTrackManagerException {
		Scheduler scheduler = Scheduler.getInstance();
		scheduler.scheduleTheConference(invalidConference);
	}
}
