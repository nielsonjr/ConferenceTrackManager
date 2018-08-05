package generator.buildConference.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import comparators.PossibleCombinationComparator;
import comparators.SessionComparator;
import comparators.TalkDurationComparator;
import model.conference.Conference;
import model.session.Session;
import model.talk.Talk;

/**
 * This class builds a {@link Conference} using an algorithm that combines all
 * talks until find a valid conference ordering the talks in the sessions by
 * duration.
 * 
 * @author Nielson
 *
 */
public class TalksByDurationConferenceGenerator extends CheckAllCombinationsConferenceGenerator {

	@Override
	protected Set<Session> initializePossibleSessionsSet() {
		return new TreeSet<Session>(new SessionComparator(new TalkDurationComparator()));
	}

	/**
	 * This method returns all the possible combinations of the elements from a list
	 * ordered by duration
	 * 
	 * @param listToBeCombined - List with all elements to be combined
	 * @return A set with all the possible combinations
	 */
	public Set<Set<Talk>> allCombinations(List<Talk> listToBeCombined) {

		// Creating a set to holds all possible combinations. The comparator will avoid
		// duplications
		Set<Set<Talk>> allCombList = new TreeSet<Set<Talk>>(
				new PossibleCombinationComparator(new TalkDurationComparator()));

		// Populating the list with the combinations "1 by 1"
		for (Talk entityToBeCombined : listToBeCombined) {
			List<Talk> arrayListOfTalks = new ArrayList<Talk>();
			arrayListOfTalks.add(entityToBeCombined);

			TreeSet<Talk> initialTreeTalk = new TreeSet<Talk>(new TalkDurationComparator());
			initialTreeTalk.addAll(arrayListOfTalks);

			allCombList.add(initialTreeTalk);
		}

		for (int level = 1; level < listToBeCombined.size(); level++) {
			// Creates another collection to modify the allCombList
			List<Set<Talk>> beforeCombinationState = new ArrayList<Set<Talk>>(allCombList);

			for (Set<Talk> beforeCombination : beforeCombinationState) {
				TreeSet<Talk> newSetOfCombinations = new TreeSet<Talk>(new TalkDurationComparator());
				newSetOfCombinations.addAll(beforeCombination);
				newSetOfCombinations.add(listToBeCombined.get(level));

				// Add new combinations
				allCombList.add(newSetOfCombinations);
			}
		}

		return allCombList;
	}
	

}
