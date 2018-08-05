package model.track;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.session.Session;
import model.talk.Talk;

/**
 * 
 * @author Nielson
 *
 */
public class Track /*implements Comparable<Track>*/ {
	private Session possibleCombMorning;
	private Session possibleCombAfternoon;

//	public static final int MIN_DURATION = Session.MIN_DURATION + Session.MIN_DURATION;
//	public static final int MAX_DURATION = Session.MIN_DURATION + Session.MAX_DURATION;

	public Track(Session possibleCombMorning, Session possibleCombAfternoon) {
		this.possibleCombMorning = possibleCombMorning;
		this.possibleCombAfternoon = possibleCombAfternoon;
	}

	public Session getPossibleCombMorning() {
		return possibleCombMorning;
	}

	public void setPossibleCombMorning(Session possibleCombMorning) {
		this.possibleCombMorning = possibleCombMorning;
	}

	public Session getAllCombListPossibleAfternoon() {
		return possibleCombAfternoon;
	}

	public void setAllCombListPossibleAfternoon(Session allCombListPossibleAfternoon) {
		this.possibleCombAfternoon = allCombListPossibleAfternoon;
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
			time += talkMorning.getDuration();
		}

		for (Talk talkAfternoon : possibleCombAfternoon.getTalks()) {
			time += talkAfternoon.getDuration();
		}

		return time;
	}

	@Override
	public String toString() {
		return "Morning Session : " + possibleCombMorning.getTalks().toString() + "\n Evening Session: "
				+ possibleCombAfternoon.getTalks().toString() + "\n===============================================";
	}

//	@Override
//	public int compareTo(Track o) {
//		int sizeComp = this.possibleCombMorning.getTalks().size() - o.getPossibleCombMorning().getTalks().size();
//
//		if (sizeComp == 0) {
//			Iterator<Talk> morningIterator = this.possibleCombMorning.getTalks().iterator();
//			Iterator<Talk> morningIteratorFromObject = o.getPossibleCombMorning().getTalks().iterator();
//			while (sizeComp == 0 && morningIterator.hasNext()) {
//				sizeComp = morningIterator.next().compareTo(morningIteratorFromObject.next());
//			}
//
//			if (sizeComp == 0) {
//				Iterator<Talk> afternoonIterator = this.possibleCombAfternoon.getTalks().iterator();
//				Iterator<Talk> afternoonIteratorFromObject = o.getAllCombListPossibleAfternoon().getTalks().iterator();
//				while (sizeComp == 0 && afternoonIterator.hasNext()) {
//					sizeComp = afternoonIterator.next().compareTo(afternoonIteratorFromObject.next());
//				}
//			}
//		}
//		return sizeComp;
//	}

}
