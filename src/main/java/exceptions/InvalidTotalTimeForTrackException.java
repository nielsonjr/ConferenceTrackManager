package exceptions;
/**
 * An exception for a track with invalid total time
 * @author Nielson
 *
 */
public class InvalidTotalTimeForTrackException extends
		ConferenceTrackManagerException {

	public InvalidTotalTimeForTrackException(String message) {
		super(message);
	}

}
