package model;

import java.util.Date;

/**
 * Represents a generic Talk
 * @author Nielson
 *
 */
public abstract class Talk implements Comparable<Talk>{	
	private String name;
	private boolean isScheduled = false;
	
	public Talk(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isScheduled() {
		return isScheduled;
	}

	public void setScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}

	@Override
	public String toString() {
		return "Talk name: " + getName() + ", talk duration: " + getDuration() + "min";
	}

	@Override
	public int compareTo(Talk arg0) {
		return this.getName().compareTo(arg0.getName());
	}	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isScheduled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Talk other = (Talk) obj;
		if (isScheduled != other.isScheduled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public abstract Integer getDuration();
	public abstract void setDuration(Integer duration);
	public abstract Date getHour();
	public abstract void setHour(Date hour);
	
	
}
