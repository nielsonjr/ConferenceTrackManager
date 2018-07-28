package validators;

/**
 * Defines a validator of T
 * @author Nielson
 *
 * @param <T> - The class of the value to be validate
 */
public interface IValidator<T> {
	public Boolean validate(T value);
}
