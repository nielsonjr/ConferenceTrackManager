package exceptions;

/**
 * An exception for lunch invalid time
 * @author Nielson
 *
 */
public class LunchTimeInvalidException extends ConferenceTrackManagerException {

	public LunchTimeInvalidException(String message) {
		super(message);
	}

}
