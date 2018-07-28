package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Track implements Comparable<Track> {
	private Session possibleCombMorning;
	private Session possibleCombAfternoon;
	
	public static final int MIN_DURATION = Session.MIN_DURATION + Session.MIN_DURATION;
	public static final int MAX_DURATION = Session.MIN_DURATION + Session.MAX_DURATION;
	
	public Track( Session possibleCombMorning, Session possibleCombAfternoon) {
		this.possibleCombMorning = possibleCombMorning;
		this.possibleCombAfternoon = possibleCombAfternoon;
	}
	
	public Session getPossibleCombMorning() {
		return possibleCombMorning;
	}

	public void setPossibleCombMorning(Session possibleCombMorning) {
		this.possibleCombMorning = possibleCombMorning;
	}

	public Session getAllCombListPossibleEve() {
		return possibleCombAfternoon;
	}

	public void setAllCombListPossibleEve(Session allCombListPossibleEve) {
		this.possibleCombAfternoon = allCombListPossibleEve;
	}
	
	public List<Talk> getAllTaksInTheTrack() {
		List<Talk> talks = new ArrayList<Talk>();
		
		for (Talk talkMorning : possibleCombMorning.getTalks()) {
			talks.add(talkMorning);
		}
		
		for (Talk talkAfternoon : possibleCombAfternoon.getTalks()) {
			talks.add(talkAfternoon);
		}
		
		return talks;
	}
	
	public int getTimeOfTrack() {
		int time = 0;
		
		for (Talk talkMorning : possibleCombMorning.getTalks()) {
			time+= talkMorning.getDuration();
		}
		
		for (Talk talkAfternoon : possibleCombAfternoon.getTalks()) {
			time+= talkAfternoon.getDuration();
		}
		
		return time;
	}

	@Override
	public String toString() {    		
		return "Morning Session : " + possibleCombMorning.getTalks().toString() +"\n Evening Session: " + possibleCombAfternoon.getTalks().toString() + "\n===============================================";
	}

	@Override
	public int compareTo(Track o) {	
		int sizeComp = this.possibleCombMorning.getTalks().size() - o.getPossibleCombMorning().getTalks().size();
		
		if (sizeComp == 0) {  
			Iterator<Talk> o1iIterator = this.possibleCombMorning.getTalks().iterator();  
			Iterator<Talk> o2iIterator = o.getPossibleCombMorning().getTalks().iterator();  
			while (sizeComp == 0 && o1iIterator.hasNext() ) {  
				sizeComp = o1iIterator.next().compareTo(o2iIterator.next());  
			}  
			
			if(sizeComp == 0) {
				Iterator<Talk> afternoonIterator = this.possibleCombAfternoon.getTalks().iterator();  
				Iterator<Talk> afternoonIteratorFromObject = o.getAllCombListPossibleEve().getTalks().iterator();  
				while (sizeComp == 0 && afternoonIterator.hasNext() ) {  
					sizeComp = afternoonIterator.next().compareTo(afternoonIteratorFromObject.next());  
				}
			}
		}  
		return sizeComp;  
	}
	
}
