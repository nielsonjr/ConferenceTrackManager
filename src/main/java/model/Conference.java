package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Conference
 * @author Nielson
 *
 */
public class Conference {
	private List<Track> tracks = new ArrayList<Track>();
	
	public List<Track> getTracks() {
		return tracks;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	@Override
	public String toString() {
		String string = "";
		for(int i = 0; i < tracks.size(); i++) {
			string = "=============TRACK " + i+1 + "=============\n" + tracks.get(i).toString();
			
		}
		return string;
	}
}
