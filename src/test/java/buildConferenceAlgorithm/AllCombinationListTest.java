package buildConferenceAlgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import generator.buildConference.impl.CheckAllCombinationsConferenceGenerator;
import model.talk.CommomTalk;
import model.talk.Talk;

/**
 * Compare the combined list from the algorithm used as an expected result
 * 
 * @author Nielson
 *
 */
public class AllCombinationListTest {
	private static List<Talk> listToBeCombined = new ArrayList<Talk>();
	private static List<Set<Talk>> expectedResult = new ArrayList<Set<Talk>>();
	private static List<Set<Talk>> expectedResultFalse = new ArrayList<Set<Talk>>();

	@BeforeClass
	public static void loadListOfElements() {
		listToBeCombined.add(new CommomTalk("A", 10));
		listToBeCombined.add(new CommomTalk("B", 5));
		listToBeCombined.add(new CommomTalk("C", 8));
	}

	@BeforeClass
	public static void loadTrueResult() {
		// all the combinations from the set
		CommomTalk talkA = new CommomTalk("A", 10);
		CommomTalk talkB = new CommomTalk("B", 5);
		CommomTalk talkC = new CommomTalk("C", 8);

		Set<Talk> HashSetForA = new HashSet<Talk>();
		HashSetForA.add(talkA);

		expectedResult.add(HashSetForA);

		HashSet<Talk> HashSetForB = new HashSet<Talk>();
		HashSetForB.add(talkB);

		expectedResult.add(HashSetForB);

		HashSet<Talk> HashSetForC = new HashSet<Talk>();
		HashSetForC.add(talkC);
		expectedResult.add(HashSetForC);

		HashSet<Talk> HashSetForAB = new HashSet<Talk>();
		HashSetForAB.add(talkA);
		HashSetForAB.add(talkB);
		expectedResult.add(HashSetForAB);

		HashSet<Talk> HashSetForAC = new HashSet<Talk>();
		HashSetForAC.add(talkA);
		HashSetForAC.add(talkC);
		expectedResult.add(HashSetForAC);

		HashSet<Talk> HashSetForBC = new HashSet<Talk>();
		HashSetForBC.add(talkB);
		HashSetForBC.add(talkC);
		expectedResult.add(HashSetForBC);

		HashSet<Talk> HashSetForABC = new HashSet<Talk>();
		HashSetForABC.add(talkA);
		HashSetForABC.add(talkB);
		HashSetForABC.add(talkC);
		expectedResult.add(HashSetForABC);
	}

	@BeforeClass
	public static void loadFalseResult() {
		// all the combinations from the set
		CommomTalk talkA = new CommomTalk("A", 10);
		CommomTalk talkB = new CommomTalk("B", 5);
		CommomTalk talkC = new CommomTalk("C", 8);
		CommomTalk talkD = new CommomTalk("D", 3);
		
		// an invalid combination
		HashSet<Talk> treeSetForA = new HashSet<Talk>();
		treeSetForA.add(talkA);

		expectedResultFalse.add(treeSetForA);

		HashSet<Talk> treeSetForB = new HashSet<Talk>();
		treeSetForB.add(talkB);

		expectedResultFalse.add(treeSetForB);

		HashSet<Talk> treeSetForC = new HashSet<Talk>();
		treeSetForC.add(talkC);
		expectedResultFalse.add(treeSetForC);

		HashSet<Talk> treeSetForAB = new HashSet<Talk>();
		treeSetForAB.add(talkA);
		treeSetForAB.add(talkB);
		expectedResultFalse.add(treeSetForAB);

		HashSet<Talk> treeSetForAC = new HashSet<Talk>();
		treeSetForAC.add(talkA);
		treeSetForAC.add(talkC);
		expectedResultFalse.add(treeSetForAC);

		HashSet<Talk> treeSetForBD = new HashSet<Talk>();
		treeSetForBD.add(talkB);
		treeSetForBD.add(talkD); // false
		expectedResultFalse.add(treeSetForBD);

		HashSet<Talk> treeSetForABC = new HashSet<Talk>();
		treeSetForABC.add(talkA);
		treeSetForABC.add(talkB);
		treeSetForABC.add(talkC);
		expectedResultFalse.add(treeSetForABC);
	}

	@Test
	public void allCombinationsOfAStringSet() {
		CheckAllCombinationsConferenceGenerator algorithm = new CheckAllCombinationsConferenceGenerator();
		Set<Set<Talk>> combinations = algorithm.allCombinations(listToBeCombined);

		List<Set<Talk>> combList = new ArrayList<Set<Talk>>();
		combList.addAll(combinations);

		int sizeComp = combList.size() - expectedResult.size();
		if (sizeComp == 0) {
			Iterator<Set<Talk>> o1iIterator = combList.iterator();

			while (sizeComp == 0 && o1iIterator.hasNext()) {
				Set<Talk> talk = o1iIterator.next();
				
				for (Set<Talk> sessionExpected : expectedResult) {
					sizeComp = compareSet(talk, sessionExpected);
					
					if(sizeComp == 0) {
						break;
					}
				}
			}
		}

		assertEquals(0, sizeComp);

	}

	@Test
	public void allCombinationsOfAStringSetFalse() {
		CheckAllCombinationsConferenceGenerator algorithm = new CheckAllCombinationsConferenceGenerator();
		Set<Set<Talk>> combinations = algorithm.allCombinations(listToBeCombined);

		List<Set<Talk>> combList = new ArrayList<Set<Talk>>();
		combList.addAll(combinations);

		int sizeComp = combList.size() - expectedResultFalse.size();
		if (sizeComp == 0) {
			Iterator<Set<Talk>> o1iIterator = combList.iterator();

			while (sizeComp == 0 && o1iIterator.hasNext()) {
				Set<Talk> talk = o1iIterator.next();
				
				for (Set<Talk> combExpected : expectedResultFalse) {
					sizeComp = compareSet(talk, combExpected);
					
					if(sizeComp == 0) {
						break;
					}
				}
			}
		}

		assertNotEquals(0, sizeComp);

	}

	private int compareSet(Set<Talk> s1, Set<Talk> s2) {
		int sizeComp = s1.size() - s2.size();
		if (sizeComp == 0) {
			Iterator<Talk> o1iIterator = s1.iterator();
			Iterator<Talk> o2iIterator = s2.iterator();

			while (sizeComp == 0 && o1iIterator.hasNext()) {
				sizeComp = o1iIterator.next().getName().compareTo(o2iIterator.next().getName());
			}
		}

		return sizeComp;
	}
}
