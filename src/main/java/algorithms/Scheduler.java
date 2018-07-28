package algorithms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;

import model.Conference;
import model.Talk;
import model.Track;
import validators.LunchTimeValidator;
import validators.NetworkingMeetingTimeValidator;
import exceptions.ConferenceTrackManagerException;
import exceptions.InvalidTotalTimeForTrackException;
import exceptions.LunchTimeInvalidException;
import exceptions.NetworkMeetingTimeInvalidException;

/**
 * Responsible to schedule all the talks in the conference
 * @author Nielson
 *
 */
public class Scheduler {
	private static Scheduler instance = null;
	
	private Scheduler() {}
	
	public static Scheduler getInstance() {
		if(instance == null) {
			instance = new Scheduler();
		}
		return instance;
	}

	 /**
     * This method schedules all the {@link Talk}'s in the conference
     * @param conference - The conference to have its {@link Talk}'s scheduled
	 * @throws ConferenceTrackManagerException 
     */
	public String scheduleTheConference(Conference conference) throws ConferenceTrackManagerException {
		int trackCount = 1;
		String scheduleDescription = "";
		for (Track track : conference.getTracks()) {
			
			int timeOfTrack = track.getTimeOfTrack();
			if(timeOfTrack < Track.MIN_DURATION || timeOfTrack > Track.MAX_DURATION) {
				throw new InvalidTotalTimeForTrackException("The track has duration equals to " + timeOfTrack + "min. It is outside of the valid time interval.");
			}
			
			scheduleDescription += "=======Track " + trackCount + "======\n";

			// start time 09:00 AM.
			SimpleDateFormat dateFormat = new SimpleDateFormat ("hh:mma");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY,9);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
			Date date = cal.getTime();

			String time = dateFormat.format(date);           

			SortedSet<Talk> mornSessionTalkList = track.getPossibleCombMorning().getTalks();
			for(Talk talk : mornSessionTalkList) {
				talk.setScheduled(true);
				talk.setHour(date);
				scheduleDescription += time + "\t" + talk.getName() + "\t" + talk.getDuration() + "min\n";
				time = getNextScheduledTime(date, talk.getDuration());
			}
			
			cal.setTime(date);
			scheduleDescription = validateAndAddLunchTime(scheduleDescription, cal, time);
			time = getNextScheduledTime(date, 60);

			SortedSet<Talk> eveSessionTalkList = track.getAllCombListPossibleEve().getTalks();
			for(Talk talk : eveSessionTalkList) {
				talk.setScheduled(true);
				talk.setHour(date);
				scheduleDescription += time + "\t" + talk.getName() + "\t" + talk.getDuration() + "min\n";
				time = getNextScheduledTime(date, talk.getDuration());
			}
			
			cal.setTime(date);
			scheduleDescription = validateAndAddNetworkingMeetting(scheduleDescription, cal, time);

			trackCount++;
		}
		
		return scheduleDescription;
		
	}

	private String validateAndAddNetworkingMeetting(String scheduleDescription,
			Calendar cal, String time) throws ConferenceTrackManagerException {
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		
		NetworkingMeetingTimeValidator networkingMeetingTimeValidator = new NetworkingMeetingTimeValidator();
		boolean isNetworkingMeetingTimeValid = networkingMeetingTimeValidator.validate(hour);
		
		if(!isNetworkingMeetingTimeValid) {
			throw new NetworkMeetingTimeInvalidException("Invalid hour for the Network Meeting");
		}
		else {
			scheduleDescription += time + "\t" + "Network Meeting \n";
		}
		return scheduleDescription;
	}

	private String validateAndAddLunchTime(String scheduleDescription,
			Calendar cal, String time) throws ConferenceTrackManagerException {
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		LunchTimeValidator lunchTimeValidator = new LunchTimeValidator();
		boolean isLunchTimeValid = lunchTimeValidator.validate(hour);
		
		if(!isLunchTimeValid) {
			throw new LunchTimeInvalidException("Invalid hour for lunch");
		}
		else {
			scheduleDescription += time + "\t" + "Lunch time\n";
		}
		return scheduleDescription;
	}
	
	 /**
     * Increase the date with a time given in minutes and return the new time as a String 
     * @param date - Represents the time
     * @param timeDuration - Time in minutes to increase the date 
     * @return The time of the next talk in String
     */
    public String getNextScheduledTime(Date date, int timeDuration){ 
		
        SimpleDateFormat dateFormat = new SimpleDateFormat ("hh:mma");
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, timeDuration);
        
        Date newDate = cal.getTime();
        
		long newTimeInLong = newDate.getTime();        
        date.setTime(newTimeInLong); //change the date
        
        String str = dateFormat.format(date);
        
        return str;
    }
}
