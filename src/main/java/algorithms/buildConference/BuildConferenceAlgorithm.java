package algorithms.buildConference;

import java.util.List;

import model.Conference;
import model.Talk;

/**
 * This interface defines algorithms to build a conference. 
 * @author Nielson
 *
 */
public interface BuildConferenceAlgorithm {
	/**
	 * Try to build a conference with the list of talks
	 * @param talks - list of talks
	 * @return The conference or null if the talks do not generate a valid conference.
	 */
	Conference buildConference(List<Talk> talks);
}
