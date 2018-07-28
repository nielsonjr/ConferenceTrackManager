package model.talk;

import java.util.Date;

public class CommomTalk extends Talk {
	public Integer duration;
	
	public CommomTalk(String name, Integer duration) {
		super(name);
		this.duration = duration;
	}

	@Override
	public Integer getDuration() {
		return duration;
	}

	@Override
	public void setDuration(Integer duration) {
		this.duration = duration;
		
	}

	@Override
	public Date getHour() {
		return null;
	}

	@Override
	public void setHour(Date hour) {
		
	}
}
