package validators;

import org.junit.Test;
import static org.junit.Assert.*;

public class NameValidatorTest {

	/**
	 * Test a blank space as name
	 */
	@Test
	public void testABlankName() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate(" ");
		assertFalse(result);
	}
	
	/**
	 * Tests an invalid Name
	 */
	@Test
	public void testInvalidName() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("User Interface CSS in Rails Apps 30minAB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		assertFalse(result);
	}
	
	/**
	 * Test a name with no numbers and duration defined
	 */
	@Test
	public void testNameWithDurationAndNoNumbersInName() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("Talk 30min");
		assertTrue(result);
	}
	
	/**
	 * Test a name with numbers and duration defined
	 */
	@Test
	public void testNameWithNumbersAndDuration() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("TalkNameWithNumbers123 10min");
		assertFalse(result);
	}
	
	/**
	 * Test name with no numbers and with no duration
	 */
	@Test
	public void testNameWithNoNumbersAndNoDuration() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("Talk");
		assertTrue(result);
	}
	
	/**
	 * Test a name with numbers and with no duration defined
	 */
	@Test
	public void testNameWithNumbersAndNoDuration() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("TalkNameWithNumbers123");
		assertFalse(result);
	}
	
	/**
	 * Test name with duration and no numbers
	 */
	@Test
	public void testDurationWithNameWithNoNumbers() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("30min Talk");
		assertFalse(result);
	}
	
	/**
	 * Test name with duration and with numbers
	 */
	@Test
	public void testDurationWithNameWithNumbers() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("30min Talk1");
		assertFalse(result);
	}
	
	/**
	 * Test a duration as name
	 */
	@Test
	public void testDurationAsName() {
		NoNumberValidator nameValidator = new NoNumberValidator();
		Boolean result = nameValidator.validate("30min");
		assertFalse(result);
	}
}
