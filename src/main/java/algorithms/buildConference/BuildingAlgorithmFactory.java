package algorithms.buildConference;

/**
 * This class creates a correct algorithm to build the {@link Conference} given an identification.
 * @author Nielson
 *
 */
public class BuildingAlgorithmFactory {
	private static BuildingAlgorithmFactory instance = null;
	
	private BuildingAlgorithmFactory() {}
	
	public static BuildingAlgorithmFactory getInstance() {
		
		if(instance == null) {
			instance = new BuildingAlgorithmFactory();
		}
		
		return instance;
	}
	
	/**
	 * Returns the algorithm to build the {@link Conference} given the identification. 
	 * If the identification is not valid, then an instance for {@link BuildConferenceAlgorithm} class will be return as default.
	 * @param id - The identification from the algorithm class  
	 * @return An instance for the algorithm for build the conference
	 */
	public BuildConferenceAlgorithm getAlgorithmToBuildConference(int id) {
		BuildConferenceAlgorithm buildConferenceAlgorithm = null;
		
		//new identifications can be added, when new algorithms are developed.
		switch (id) {
		case 0: 
			buildConferenceAlgorithm = new CheckAllCombinationsOfTalksAlgorithm();
			break;
		default:
			buildConferenceAlgorithm = new CheckAllCombinationsOfTalksAlgorithm();
			break;
		}
		
		return buildConferenceAlgorithm;
	}
}
