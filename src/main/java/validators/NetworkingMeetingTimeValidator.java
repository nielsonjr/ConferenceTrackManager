package validators;

import java.time.LocalTime;

import config.Configuration;

/**
 * A validator for the meeting time
 * 
 * @author Nielson
 */
public class NetworkingMeetingTimeValidator implements IValidator<LocalTime> {

	@Override
	public Boolean validate(LocalTime value) {

		LocalTime netMeetMinTime = Configuration.getNetworkMeetingBeginTime();
		LocalTime netMeetMaxTime = Configuration.getNetworkMeetingEndTime();

		if ((value.isAfter(netMeetMinTime) || value.equals(netMeetMinTime))
				&& (value.isBefore(netMeetMaxTime) || value.equals(netMeetMinTime))) {
			return true;
		}
		return false;
	}
}
