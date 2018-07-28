package validators;


/**
 * A validator for the lunch time
 * @author Nielson
 */
		
public class LunchTimeValidator implements IValidator<Integer> {

	@Override
	public Boolean validate(Integer value) {
		Boolean isValid = false;
				
		if(value == 12) {
			isValid = true;
		}
		
		return isValid;
	}


}
