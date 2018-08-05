package parser;

import java.io.File;
import java.util.List;

import exceptions.ConferenceTrackManagerException;
import model.talk.Talk;

/**
 * An inferface to build a collection of {@link Talk} from a File
 * @author Nielson
 *
 */
public interface IParser {
	/**
	 * This method parsers the file
	 *  
	 * @param file - the File to be parsed
	 * @return A list of talks
	 * @throws Raised an {@link ConferenceTrackManagerException} if the format of the file is invalid
	 */
	public List<Talk> parse(File file) throws ConferenceTrackManagerException;
}
