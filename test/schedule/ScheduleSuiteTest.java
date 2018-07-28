package schedule;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	schedule.LunchTimeTest.class, 
	schedule.NetworkMeetingTimeTest.class,
	schedule.ScheduleTasksTest.class}
)
public class ScheduleSuiteTest {
	 
}
