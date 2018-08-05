package comparators;

import java.util.Comparator;

import model.talk.Talk;

public class TalkDurationComparator implements Comparator<Talk> {

	@Override
	public int compare(Talk o1, Talk o2) {

		int compareTo = o2.getDuration().compareTo(o1.getDuration());
		
		if(compareTo == 0) {
			compareTo = o1.getName().compareTo(o2.getName());
		}
		return compareTo;
	}

}
