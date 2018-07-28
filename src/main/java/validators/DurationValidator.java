package validators;

/**
 * Check the format of a duration. It must end with 'min' or 'lightning'. If the value is min, then must have a number before it;
 * if the value ends with 'lightning', then must not have anything before it 
 * @author Nielson
 *
 */
public class DurationValidator implements IValidator<String> {

	public Boolean validate(String value) {
		boolean isValid = false;
		
		if(value == null || value.isEmpty() || value.trim().isEmpty()) {
			return isValid = false;
		}
		else {
			String duration = value.substring(value.lastIndexOf(" ") + 1); 
			
			if(duration.contains("min")) {
				String[] durationWithoutType = duration.split("min");
				
				if(durationWithoutType.length > 0) {
					isValid = durationWithoutType[0].matches("^[0-9]+$");
				}
			}
			else if (duration.contains("lightning")) {
				String[] durationWithoutType = duration.split("lightning");
				
				if(durationWithoutType.length == 0) {
					isValid = true;
				}
			}
			
		}
		
		
		return  isValid ;
	}

}
