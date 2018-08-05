package suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import buildConferenceAlgorithm.AllCombinationListTest;
import buildConferenceAlgorithm.BuildCombinationListsForSessionTest;
import schedule.ScheduleSuiteTest;
import validators.NameValidatorTest;
import validators.TalkDurationValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({
	ScheduleSuiteTest.class,
	AllCombinationListTest.class,
	BuildCombinationListsForSessionTest.class,
	NameValidatorTest.class,
	TalkDurationValidatorTest.class}
)
public class ConferenceTrackManagerSuiteTest {

}
