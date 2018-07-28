package ctm;

import java.io.File;
import java.util.List;

import model.conference.Conference;
import model.talk.Talk;
import parser.DefaultFormatParser;
import algorithms.Scheduler;
import algorithms.buildConference.BuildConferenceAlgorithm;
import algorithms.buildConference.BuildingAlgorithmFactory;

/**
 * Execute the project. For more information about the project check the file "design.txt".
 * @author Nielson
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
    	File file = new File (args[0]);
    	
    	DefaultFormatParser dfp = DefaultFormatParser.getInstance();
    	
    	List<Talk> talks = dfp.parser(file);
    	BuildConferenceAlgorithm buildConferenceAlgorithm = BuildingAlgorithmFactory.getInstance().getAlgorithmToBuildConference(0);
    	Conference conference = buildConferenceAlgorithm.buildConference(talks);
    	
    	if(conference != null) {
    		Scheduler scheduler = Scheduler.getInstance();
    		String scheduleDescription = scheduler.scheduleTheConference(conference);
    		
    		System.out.println(scheduleDescription);
    	}
    	else {
    		System.out.println("It was not possible to find a valid conference for the talks");
    	}
    }

   
    

    
    
    
}
