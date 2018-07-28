package schedule;

import model.CommomTalk;
import model.Conference;
import model.Session;
import model.Track;

import org.junit.BeforeClass;
import org.junit.Test;

import algorithms.Scheduler;
import exceptions.ConferenceTrackManagerException;
import exceptions.InvalidTotalTimeForTrackException;

public class ScheduleTasksTest {
	private static Conference invalidConference = new Conference();
	
	@BeforeClass
	public static void loadTheValidConference() {
		
	}
	
	@BeforeClass
	public static void loadTheInvalidConference() {
		Session morningSessionForTrack = new Session();
		
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk1", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk2", 60));
		
		Session afternoonSessionForTrack = new Session();
		
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk3", 30));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk4", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk5", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk6", 60));
		Track track1 = new Track(morningSessionForTrack, afternoonSessionForTrack);
		
		invalidConference.getTracks().add(track1);
	}
	
	@Test
	public void scheduleAValidConference() {
		Conference validConference = new Conference();
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
		Scheduler scheduler = Scheduler.getInstance();
		try {
			scheduler.scheduleTheConference(validConference);
		} catch (ConferenceTrackManagerException e) {
			System.err.println("An unexpected exception occurs:" );
			e.printStackTrace();
		}
	}
	
	@Test(expected=ConferenceTrackManagerException.class)
	public void scheduleAnInvalidConference() throws ConferenceTrackManagerException {
		Conference validConference = new Conference();
		Session morningSessionForTrack = new Session();

		morningSessionForTrack.getTalks().add(new CommomTalk("Talk1", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk2", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk3", 60));

		Session afternoonSessionForTrack = new Session();

		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk4", 30));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk5", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk6", 45));
		Track track1 = new Track(morningSessionForTrack, afternoonSessionForTrack);

		validConference.getTracks().add(track1);
		Scheduler scheduler = Scheduler.getInstance();
		scheduler.scheduleTheConference(validConference);
	}
	
	@Test(expected=InvalidTotalTimeForTrackException.class)
	public void scheduleAnInvalidConferenceWithTracksOutOfTheIntervalTime() throws ConferenceTrackManagerException {
		Conference conference = new Conference();
		Session morningSessionForTrack = new Session();

		morningSessionForTrack.getTalks().add(new CommomTalk("Talk1", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk2", 60));
		morningSessionForTrack.getTalks().add(new CommomTalk("Talk3", 60));

		Session afternoonSessionForTrack = new Session();

		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk4", 30));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk5", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk6", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk7", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk8", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk9", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk10", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk11", 45));
		afternoonSessionForTrack.getTalks().add(new CommomTalk("Talk12", 45));
		Track track1 = new Track(morningSessionForTrack, afternoonSessionForTrack);

		conference.getTracks().add(track1);
		Scheduler scheduler = Scheduler.getInstance();
		scheduler.scheduleTheConference(conference);
	}
	
	@Test
	public void scheduleConferenceWithNoTracks()  {
		Conference validConference = new Conference();
		Scheduler scheduler = Scheduler.getInstance();
		
		try {
			scheduler.scheduleTheConference(validConference);
		} catch (ConferenceTrackManagerException e) {
			System.err.println("An unexpected exception occurs:" );
			e.printStackTrace();
		}
	}
}
