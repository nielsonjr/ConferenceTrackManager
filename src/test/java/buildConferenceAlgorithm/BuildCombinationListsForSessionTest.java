package buildConferenceAlgorithm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import generator.buildConference.impl.CheckAllCombinationsConferenceGenerator;
import model.session.Session;
import model.talk.CommomTalk;
import model.talk.Talk;

/**
 * To test all possible combinations for session. The minimum and maximum
 * session time in this test are defined respectively as 60min and 150min. The
 * results from the algorithm are compared to an expected result.
 * 
 * @author Nielson
 *
 */
public class BuildCombinationListsForSessionTest {
	private static List<Talk> listToBeCombined = new ArrayList<Talk>();
	private static List<Session> expectedResultForMorning = new ArrayList<Session>();
	private static List<Session> expectedResultForAfternoon = new ArrayList<Session>();

	@BeforeClass
	public static void loadListOfTalks() {
		CommomTalk commomTalk1 = new CommomTalk("Talk1", 30);
		listToBeCombined.add(commomTalk1);

		CommomTalk commomTalk2 = new CommomTalk("Talk2", 30);
		listToBeCombined.add(commomTalk2);

		CommomTalk commomTalk3 = new CommomTalk("Talk3", 60);
		listToBeCombined.add(commomTalk3);

		CommomTalk commomTalk4 = new CommomTalk("Talk4", 90);
		listToBeCombined.add(commomTalk4);

		// morning session always 60 minutes
		Session morningSession1 = new Session(new HashSet<Talk>());
		morningSession1.getTalks().add(commomTalk1);
		morningSession1.getTalks().add(commomTalk2);
		expectedResultForMorning.add(morningSession1);

		Session morningSession2 = new Session(new HashSet<Talk>());
		morningSession2.getTalks().add(commomTalk3);
		expectedResultForMorning.add(morningSession2);

		// afternoon sessions: minimum 60min and max 150min
		Session afternoonSession1 = new Session(new HashSet<Talk>());
		afternoonSession1.getTalks().add(commomTalk3);
		afternoonSession1.getTalks().add(commomTalk4);
		expectedResultForAfternoon.add(afternoonSession1); // 150min

		Session afternoonSession2 = new Session(new HashSet<Talk>());
		afternoonSession2.getTalks().add(commomTalk1);
		afternoonSession2.getTalks().add(commomTalk2);
		afternoonSession2.getTalks().add(commomTalk4);
		expectedResultForAfternoon.add(afternoonSession2); // 150min

		Session afternoonSession3 = new Session(new HashSet<Talk>());
		afternoonSession3.getTalks().add(commomTalk1);
		afternoonSession3.getTalks().add(commomTalk2);
		expectedResultForAfternoon.add(afternoonSession3); // 60min

		Session afternoonSession4 = new Session(new HashSet<Talk>());
		afternoonSession4.getTalks().add(commomTalk1);
		afternoonSession4.getTalks().add(commomTalk3);
		expectedResultForAfternoon.add(afternoonSession4); // 90min

		Session afternoonSession5 = new Session(new HashSet<Talk>());
		afternoonSession5.getTalks().add(commomTalk1);
		afternoonSession5.getTalks().add(commomTalk4);
		expectedResultForAfternoon.add(afternoonSession5); // 120min

		Session afternoonSession6 = new Session(new HashSet<Talk>());
		afternoonSession6.getTalks().add(commomTalk2);
		afternoonSession6.getTalks().add(commomTalk3);
		expectedResultForAfternoon.add(afternoonSession6); // 90min

		Session afternoonSession7 = new Session(new HashSet<Talk>());
		afternoonSession7.getTalks().add(commomTalk2);
		afternoonSession7.getTalks().add(commomTalk4);
		expectedResultForAfternoon.add(afternoonSession7); // 120min

		Session afternoonSession8 = new Session(new HashSet<Talk>());
		afternoonSession8.getTalks().add(commomTalk1);
		afternoonSession8.getTalks().add(commomTalk2);
		afternoonSession8.getTalks().add(commomTalk3);
		expectedResultForAfternoon.add(afternoonSession8); // 120min

		Session afternoonSession9 = new Session(new HashSet<Talk>());
		afternoonSession9.getTalks().add(commomTalk3);
		expectedResultForAfternoon.add(afternoonSession9); // 60min

		Session afternoonSession10 = new Session(new HashSet<Talk>());
		afternoonSession10.getTalks().add(commomTalk4);
		expectedResultForAfternoon.add(afternoonSession10); // 60min

	}

	@Test
	public void allCombinationsOfAStringSet() {
		CheckAllCombinationsConferenceGenerator algorithm = new CheckAllCombinationsConferenceGenerator();
		Set<Session> allCombListPossibleMorning = new HashSet<Session>();
		Set<Session> allCombListPossibleAfternoon = new HashSet<Session>();

		// the algorithm generates all the possible combinations from both morning and
		// afternoon sessions. It does not generate a valid combination of session for a
		// track
		algorithm.buildPossibleSessionsList(listToBeCombined, allCombListPossibleMorning, allCombListPossibleAfternoon,
				60, 60, 150);

		System.out.println(allCombListPossibleMorning.toString());

		// comparing the lists for morning
		int sizeComp = allCombListPossibleMorning.size() - expectedResultForMorning.size();
		if (sizeComp == 0) {
			Iterator<Session> o1iIterator = allCombListPossibleMorning.iterator();
			Iterator<Session> o2iIterator = expectedResultForMorning.iterator();

			while (sizeComp == 0 && o1iIterator.hasNext()) {
				sizeComp = compareSortedSet(o1iIterator.next(), o2iIterator.next());
			}
		}

		// comparing the lists for afternoon
		sizeComp = allCombListPossibleAfternoon.size() - expectedResultForAfternoon.size();
		System.out.println(allCombListPossibleAfternoon.toString());
		if (sizeComp == 0) {
			Iterator<Session> o1iIterator = allCombListPossibleAfternoon.iterator();

			while (sizeComp == 0 && o1iIterator.hasNext()) {
				
				Session next = o1iIterator.next();
				
				for (Session sessionExpected : expectedResultForAfternoon) {
					sizeComp = compareSortedSet(next, sessionExpected);
					
					if(sizeComp == 0) {
						break;
					}
				}
			}
		}

		// if all the lists are equals to their respective expected result, then the
		// result is 0
		assertEquals(0, sizeComp);

	}

	private int compareSortedSet(Session s1, Session s2) {
		int sizeComp = s1.getTalks().size() - s2.getTalks().size();
		if (sizeComp == 0) {
			Iterator<Talk> o1iIterator = s1.getTalks().iterator();
			Iterator<Talk> o2iIterator = s2.getTalks().iterator();
			while (sizeComp == 0 && o1iIterator.hasNext()) {
				sizeComp = o1iIterator.next().getName().compareTo(o2iIterator.next().getName());
			}
		}
		return sizeComp;
	}
}
