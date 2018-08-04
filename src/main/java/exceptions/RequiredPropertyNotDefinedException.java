package exceptions;

public class RequiredPropertyNotDefinedException extends RuntimeException {

	private static final long serialVersionUID = -8981092279092553713L;

	public RequiredPropertyNotDefinedException(String propertyName) {
		super(String.format("Property '%s' is required and it is not defined in the properties file.", propertyName));
	}
}
