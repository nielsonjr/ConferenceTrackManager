package comparators;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import model.talk.Talk;

public class PossibleCombinationComparator implements Comparator<Set<Talk>> {

	private Comparator<Talk> comparatorTalk = null;

	public PossibleCombinationComparator(Comparator<Talk> comparatorTalk) {
		this.comparatorTalk = comparatorTalk;
	}

	@Override
	public int compare(Set<Talk> o1, Set<Talk> o2) {
		int sizeComp = o1.size() - o2.size();
		if (sizeComp == 0) {
			Iterator<Talk> o1iIterator = o1.iterator();
			Iterator<Talk> o2iIterator = o2.iterator();
			while (sizeComp == 0 && o1iIterator.hasNext()) {
				sizeComp = comparatorTalk.compare(o1iIterator.next(), o2iIterator.next());
			}
		}
		return sizeComp;

	}

}
