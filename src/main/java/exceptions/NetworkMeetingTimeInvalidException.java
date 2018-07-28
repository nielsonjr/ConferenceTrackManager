package exceptions;

/**
 * An exception for networking meeting invalid time
 * @author Nielson
 *
 */
public class NetworkMeetingTimeInvalidException extends
		ConferenceTrackManagerException {

	public NetworkMeetingTimeInvalidException(String message) {
		super(message);
	}


}
