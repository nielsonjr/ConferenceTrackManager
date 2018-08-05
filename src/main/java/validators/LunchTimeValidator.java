package validators;

import java.time.Duration;
import java.time.LocalTime;

import config.Configuration;

/**
 * A validator for the lunch time
 * @author Nielson
 */
		
public class LunchTimeValidator implements IValidator<LocalTime> {

	@Override
	public Boolean validate(LocalTime value) {
		Boolean isValid = false;	
		
		LocalTime confLunchTime = LocalTime.parse(Configuration.getConferenceLunchTime());
		if(value != null && Duration.between(value, confLunchTime).isZero()) {
			isValid = true;
		}
		
		return isValid;
	}


}
