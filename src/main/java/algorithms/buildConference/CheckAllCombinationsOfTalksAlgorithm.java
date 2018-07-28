package algorithms.buildConference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import model.Conference;
import model.Session;
import model.Talk;
import model.Track;

import comparators.SessionComparator;

/**
 * This class builds a {@link Conference} using an algorithm that combines all talks until find a valid conference.
 * @author Nielson
 *
 */
public class CheckAllCombinationsOfTalksAlgorithm implements BuildConferenceAlgorithm{

	@Override
	public Conference buildConference(List<Talk> talks) {
		//SESSIONs
    	SortedSet<Session> allCombListPossibleMorning = new TreeSet<Session>(new SessionComparator());      	
    	SortedSet<Session> allCombListPossibleAfternoon = new TreeSet<Session>(new SessionComparator());  

    	//Build all the possible sessions
    	buildPossibleSessionsList(talks, allCombListPossibleMorning, allCombListPossibleAfternoon, Session.MIN_DURATION, Session.MAX_DURATION);

    	System.out.println("Building all the possible tracks... Please, wait!");
    	//build all the possible tracks
    	List<Track> possibleTracks = getAllPossibleTracks(allCombListPossibleMorning, allCombListPossibleAfternoon);

    	System.out.println("Searching for a valid conference... This step may take a while. Please, wait! It will finish when a valid combination is found.");
    	Conference conference = searchForAConference(talks, possibleTracks);

    	return conference;
	}
	
	

    /**
     * Search for a valid combination of Track to fill a Conference. When a valid conference is found, the algorithm is finished.
     * @param talks - All the talks to set the conference
     * @param possibleTracks - All possible tracks
     * @return A Conference with its Tracks
     */
	private Conference searchForAConference(List<Talk> talks,
			List<Track> possibleTracks) {
		boolean isFoundACombForConference = false;
    	Conference conference = null;
    	
    	int timeOfTalks = 0;
    	for (Talk talk : talks) {
    		timeOfTalks += talk.getDuration();
		}
    	
    	int possibleNumberOfTracks = timeOfTalks / Track.MIN_DURATION; //number of tracks. 360min is minimum time for a track
    	
    	int deep = possibleNumberOfTracks - 1; //combination level between the tracks
    	for (int i = 0; i < possibleTracks.size() && !isFoundACombForConference; i++) {
    		
    		System.out.println((i+1) + " Step / " + possibleTracks.size());
    		Track possibleTracks1 = possibleTracks.get(i);
    		List<Track> visitedTracks = new ArrayList<Track>();
    		visitedTracks.add(possibleTracks1);
    		
			conference = combineTracksToFoundAConference(talks, possibleTracks, visitedTracks , deep);
			visitedTracks.remove(possibleTracks1);
			
			if(conference != null) {
				break;
			}


    	}
		return conference;
	}

	/**
	 * This method combine tracks until find a valid conference. It will visited all the possible tracks mixing them when the level is equal to 0. The visited tracks are save in a list to avoid no-ending loops.
	 * @param talks - All talks in the conference
	 * @param possibleTracks - All possible tracks
	 * @param visitedTracks - Visited tracks
	 * @param level - It holds how many tracks the algorithm must combined. When it is equal to "0", then it is time to search a conference. It is related to the number of tracks in a Conference.
	 * @return A valid {@link Conference} if it is found, or null if it does not.
	 */
	public Conference combineTracksToFoundAConference(List<Talk> talks, List<Track> possibleTracks, List<Track> visitedTracks, int level) {
		Conference conference = null;
		
		if(level == 0) { //when the level is 0, a possible conference can be found
			
			Set<Talk> setTalkNoDuplication = new HashSet<Talk>();
			
			boolean foundDuplication = false;
			
			//Trying to find duplications between the tracks
			for (Track possibleTrack : visitedTracks) {
				
				List<Talk> allTaksInTheTrack = possibleTrack.getAllTaksInTheTrack();
				if(!setTalkNoDuplication.isEmpty()) {
					
					for (Talk talk : setTalkNoDuplication) {
						//If a talk in the possible track is contained in then talk list from the others visited tracks, then there is a duplication 
						if(allTaksInTheTrack.contains(talk)) {
							foundDuplication = true;
							break;
						}
					}
				}
				
				if(foundDuplication) {
					break;
				} 
				else {
				//getting all the Tasks of the tracks put into a set to avoid duplication
				setTalkNoDuplication.addAll(allTaksInTheTrack);
				}
			}
			
			//If there is no duplication of talks in the set and its size is equal to the amount of tasks, then a combination for a conference was found. 
			if(!foundDuplication && setTalkNoDuplication.size() == talks.size()) {
				conference = new Conference();
				
				for (Track track : visitedTracks) {
					conference.getTracks().add(track);
				}
				
				return conference;
			}
		}
		else {
			
			level--; //decrease the level search 
			for (int i = 0; i < possibleTracks.size(); i++) {
				Track possibleTracks1 = possibleTracks.get(i);
				
				//try a new combination of the tracks in a different level
				visitedTracks.add(possibleTracks1); 
				conference = combineTracksToFoundAConference(talks, possibleTracks, visitedTracks, level);
				visitedTracks.remove(possibleTracks1);
				
				if(conference != null) {
					break;
				}
			}
			
		}
		
		return conference;
	}
    /**
     * To get all the possible Tracks given mixing the combinations set of morning and afternoon Sessions. If there is no occurrence from the talks in the set, then it can be a possible Track.
     * @param allCombListPossibleMorning - The possible set of morning Sessions
     * @param allCombListPossibleAfternoon - The possible set of afternoon Sessions
     * @return A list with all possible Tracks
     */
	private List<Track> getAllPossibleTracks(
			SortedSet<Session> allCombListPossibleMorning,
			SortedSet<Session> allCombListPossibleAfternoon) {
		
		List<Track> possibleTracks = new ArrayList<Track>();
		
		for (Session possibleCombMorning : allCombListPossibleMorning) {
			//iterating over the possible set of morning sessions
    		for (Session possibleCombEvening : allCombListPossibleAfternoon) {
    			//iterating over the possible set of afternoon sessions
    			
    			boolean isPossibleEveningCombContainsPossibleMorningComb = false;
    			
    			if(!possibleCombEvening.equals(possibleCombMorning)) { 

    				for (Talk talk : possibleCombEvening.getTalks()) {
    					//search for occurrences of a specific talk inside the morning session
    					if(possibleCombMorning.getTalks().contains(talk)) {
    						isPossibleEveningCombContainsPossibleMorningComb = true;
    						break;
    					}
    				}

    				if(!isPossibleEveningCombContainsPossibleMorningComb) {
    					//If there is no occurrence from the talks in the set, then a possible Track was found.
    					possibleTracks.add(new Track(possibleCombMorning, possibleCombEvening));
    				}
    			}

    		}

    	}
		
		return possibleTracks;
	}

    /**
     * To build the possible sessions set. 
     * @param talks - The talks to be combined 
     * @param allCombListPossibleMorning - A collection to hold the possible morning combinations
     * @param allCombListPossibleAfternoon - A collection to hold the possible afternoon combinations
     * @param sessionMinDuration - Session minimum duration
     * @param sessionMaxDuration - Session maximum duration
     */
	public void buildPossibleSessionsList(
			List<Talk> talks,
			SortedSet<Session> allCombListPossibleMorning,
			SortedSet<Session> allCombListPossibleAfternoon,
			int sessionMinDuration,
			int sessionMaxDuration) {
		
		System.out.println("Searching for all possible combinations with the talks... Please, wait!");
		//Get all possible combinations from the talks list
    	SortedSet<SortedSet<Talk>> allCombinationList = allCombinations(talks); 
		
		
		//Get all possible combinations from talk list
    	System.out.println("Building possible combinations for the morning Talk's and for afternoon Talk's... Please, wait!");
		for (SortedSet<Talk> sortedSet : allCombinationList) {
    		int time = 0;
    		
    		for (Talk talk : sortedSet) { //get the duration from the set of talks
    			time += talk.getDuration();
    		}

    		if(time == sessionMinDuration) {
    			Session session = new Session();
    			session.setTalks(sortedSet);
    			session.setDuration(time);
    			
    			allCombListPossibleMorning.add(session); //possible conference morning
    		}

    		if(time >= sessionMinDuration && time <= sessionMaxDuration ) {
    			Session session = new Session();
    			session.setTalks(sortedSet);
    			session.setDuration(time);
    			
    			allCombListPossibleAfternoon.add(session); //possible conference afternoon
    		}
    	}
	}
	
	

    /**
     * This method returns all the possible combinations of the elements from a list
     * @param listToBeCombined - List with all elements to be combined
     * @return A set with all the possible combinations
     */
    public <T extends Comparable> SortedSet<SortedSet<T>> allCombinations(List<T> listToBeCombined) {

    	//Creating a set to holds all possible combinations. The comparator will avoid duplications
    	SortedSet<SortedSet<T>> allCombList = new TreeSet<SortedSet<T>>(new Comparator<SortedSet<T>>() {					

    		@Override			
    		public int compare(SortedSet<T> o1, SortedSet<T> o2) {  
    			int sizeComp = o1.size() - o2.size();  
    			if (sizeComp == 0) {  
    				Iterator<T> o1iIterator = o1.iterator();  
    				Iterator<T> o2iIterator = o2.iterator();  
    				while (sizeComp == 0 && o1iIterator.hasNext() ) {  
    					sizeComp = o1iIterator.next().compareTo(o2iIterator.next());  
    				}  
    			}  
    			return sizeComp;  

    		}  
    	});  

    	//Populating the list with the combinations "1 by 1"
    	for (T entityToBeCombined : listToBeCombined) {  
    		List<T> arrayListOfTalks = new ArrayList<T>();
    		arrayListOfTalks.add(entityToBeCombined);
    		allCombList.add(new TreeSet<T>(arrayListOfTalks));  
    	}  

    	for (int level = 1; level < listToBeCombined.size(); level++) {	
    		//Creates another collection to modify the allCombList 
    		List<SortedSet<T>> beforeCombinationState = new ArrayList<SortedSet<T>>(allCombList);  

    		for (Set<T> beforeCombination : beforeCombinationState) {  
    			SortedSet<T> newSetOfCombinations = new TreeSet<T>(beforeCombination);  
    			newSetOfCombinations.add(listToBeCombined.get(level)); 

    			//Add a new combinations
    			allCombList.add(newSetOfCombinations); 
    		}  
    	}

    	return allCombList;
    }

}
