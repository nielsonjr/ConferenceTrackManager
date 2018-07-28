package model;

import java.util.Date;

/**
 * Represents a lightning Talk
 * @author Nielson
 *
 */
public class LightningTalk extends Talk {

	private final Integer duration = 5;
	
	public LightningTalk(String name) {
		super(name);
	}
	
	@Override
	public Integer getDuration() {
		return duration;
	}

	@Override
	public void setDuration(Integer duration) {}

	@Override
	public Date getHour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHour(Date hour) {
		// TODO Auto-generated method stub
		
	}

}
