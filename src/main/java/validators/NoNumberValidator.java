package validators;

public class NoNumberValidator implements IValidator<String>{

	@Override
	public Boolean validate(String value) {
		if(value == null || value.isEmpty() || value.trim().isEmpty()) {
			return false;
		}
		
		int lastIndexOf = value.lastIndexOf(" ");
		String name = null;
		
		if(lastIndexOf == -1) {
			name = value;
		}
		else {
			name = value.substring(0, lastIndexOf);	
		}
		
		return name.matches("^([^0-9]*)$");
	}

}
