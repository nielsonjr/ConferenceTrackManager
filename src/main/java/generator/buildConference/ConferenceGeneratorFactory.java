package generator.buildConference;


import generator.buildConference.impl.CheckAllCombinationsConferenceGenerator;
import generator.buildConference.impl.TalksByDurationConferenceGenerator;
import util.ConstantsApp;

/**
 * This class creates a correct generator to build the {@link Conference} given an identification.
 * @author Nielson
 *
 */
public class ConferenceGeneratorFactory {
	private static ConferenceGeneratorFactory instance = null;
	
	private ConferenceGeneratorFactory() {}
	
	public static ConferenceGeneratorFactory getInstance() {
		
		if(instance == null) {
			instance = new ConferenceGeneratorFactory();
		}
		
		return instance;
	}
	
	/**
	 * Returns the generator to build the {@link Conference} given the identification. 
	 * If the identification is not valid, then an instance for {@link ConferenceGenerator} class will be return as default.
	 * @param name - The identification of the generator class  
	 * @return An instance for the algorithm for build the conference
	 */
	public ConferenceGenerator getAlgorithmToBuildConference(String name) {
		if(name == null) {
			return null;
		}
		
		ConferenceGenerator buildConferenceAlgorithm = null;
		
		//new identifications can be added, when new algorithms are developed.
		if(name.equalsIgnoreCase(ConstantsApp.CONFERENCE_GENERATOR_ALL_COMBINATIONS_NAME)) {
			buildConferenceAlgorithm = new CheckAllCombinationsConferenceGenerator();
		} else if(name.equalsIgnoreCase(ConstantsApp.CONFERENCE_GENERATOR_TALKS_ORDERED_BY_DURATION_NAME)) {
			buildConferenceAlgorithm = new TalksByDurationConferenceGenerator();
		}
		
		
		return buildConferenceAlgorithm;
	}
}
