package validators;

import org.junit.Test;
import static org.junit.Assert.*;

public class TalkDurationValidatorTest {

	/**
	 * Test a blank space as duration
	 */
	@Test
	public void testABlankDuration() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate(" ");
		assertFalse(result);
	}
	
	/**
	 * Tests an invalid duration
	 */
	@Test
	public void testInvalidDuration() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("User Interface CSS in Rails Apps 30minAB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		assertFalse(result);
	}
	
	/**
	 * Test a name and duration well defined
	 */
	@Test
	public void testNameWithDuration() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("Talk 30min");
		assertTrue(result);
	}
	
	/**
	 * Test name and an invalid duration 
	 */
	@Test
	public void testDurationWithNameAndInvalidTime() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("Talk ymin");
		assertFalse(result);
	}
	
	/**
	 * Test name and duration with no time type defined
	 */
	@Test
	public void testDurationWithNameAndNoTimeTypeDefined() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("Talk 10");
		assertFalse(result);
	}
	
	/**
	 * Test name and duration with no time defined
	 */
	@Test
	public void testDurationWithNameAndNoTimeDefined() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("Talk min");
		assertFalse(result);
	}
	
	/**
	 * Test name and duration as lightning
	 */
	@Test
	public void testLightningDurationWithName() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("Talk lightning");
		assertTrue(result);
	}
	
	/**
	 * Test name and duration as lightning
	 */
	@Test
	public void testLightningDurationWithNameAndNumber() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("Talk 7lightning");
		assertFalse(result);
	}
		
	/**
	 * Test duration as null
	 */
	@Test
	public void testDurationAsNull() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate(null);
		assertFalse(result);
	}
	
	/**
	 * Test a talk with name but with no duration
	 */
	@Test
	public void testDurationWithNoValue() {
		DurationValidator durationValidator = new DurationValidator();
		Boolean result = durationValidator.validate("Talk");
		assertFalse(result);
	}
	
}
