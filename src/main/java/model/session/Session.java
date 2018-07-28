package model.session;

import java.util.SortedSet;
import java.util.TreeSet;

import model.talk.Talk;

/**
 * Represents a session
 * 
 * @author Nielson
 *
 */
public class Session {
	private SortedSet<Talk> talks = new TreeSet<Talk>();
	private Integer duration = null;

	public static final Integer MIN_DURATION = 180;
	public static final Integer MAX_DURATION = 240;

	public SortedSet<Talk> getTalks() {
		return talks;
	}

	public void setTalks(SortedSet<Talk> talks) {
		this.talks = talks;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return talks.toString();
	}
}
