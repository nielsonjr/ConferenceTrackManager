package model.session;

import java.util.HashSet;
import java.util.Set;

import model.talk.Talk;

/**
 * Represents a session
 * 
 * @author Nielson
 *
 */
public class Session {
	private Set<Talk> talks = null;
	private Integer duration = null;

	public Session() {
		this.talks = new HashSet<>();
	}
	
	public Session(Set<Talk> talks) {
		this.talks = talks;
	}
	
	public Set<Talk> getTalks() {
		return talks;
	}

	public void setTalks(Set<Talk> talks) {
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
