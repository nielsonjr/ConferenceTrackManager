package comparators;

import java.util.Comparator;
import java.util.Iterator;

import model.session.Session;
import model.talk.Talk;

/**
 * A comparator for {@link Session} objects. It compares the size of talk lists, if they are equals then compare each {@link Talk} in the list
 * @author Nielson
 *
 */
public class SessionComparator implements Comparator<Session> {

	private Comparator<Talk> comparatorTalk = null;

	public SessionComparator(Comparator<Talk> comparatorTalk) {
		this.comparatorTalk = comparatorTalk;
	}
	
	public int compare(Session o1, Session o2) {
		int sizeComp = o1.getTalks().size() - o2.getTalks().size();  
		if (sizeComp == 0) {  
			Iterator<Talk> o1iIterator = o1.getTalks().iterator();  
			Iterator<Talk> o2iIterator = o2.getTalks().iterator();  
			while (sizeComp == 0 && o1iIterator.hasNext() ) {  
				sizeComp = comparatorTalk.compare(o1iIterator.next(), o2iIterator.next());  
			}  
		}  
		return sizeComp;  

	}  
}
