package validators;

/**
 * A validator for the meeting time
 * @author Nielson
 */
public class NetworkingMeetingTimeValidator implements IValidator<Integer>{

	@Override
	public Boolean validate(Integer value) {
		if(value >= 16 && value <= 17) {
			return true;
		}
		return false;
	}
}
