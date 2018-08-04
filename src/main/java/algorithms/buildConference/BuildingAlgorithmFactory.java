package algorithms.buildConference;


import util.ConstantsApp;

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
	public BuildConferenceAlgorithm getAlgorithmToBuildConference(String name) {
		if(name == null) {
			return null;
		}
		
		BuildConferenceAlgorithm buildConferenceAlgorithm = null;
		
		//new identifications can be added, when new algorithms are developed.
		if(name.equalsIgnoreCase(ConstantsApp.ALGORITHM_ALL_COMBINATIONS_NAME)) {
			buildConferenceAlgorithm = new CheckAllCombinationsOfTalksAlgorithm();
		} else if(name.equalsIgnoreCase(ConstantsApp.ALGORITHM_ALL_COMBINATIONS_NO_ORDER_NAME)) {
			buildConferenceAlgorithm = new CheckAllCombinationsWithoutOrderOfTalksAlgorithm();
		}
		
		
		return buildConferenceAlgorithm;
	}
}
