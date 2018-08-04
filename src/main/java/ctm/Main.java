package ctm;

import java.io.File;
import java.util.List;

import algorithms.Scheduler;
import algorithms.buildConference.BuildConferenceAlgorithm;
import algorithms.buildConference.BuildingAlgorithmFactory;
import config.Configuration;
import model.conference.Conference;
import model.talk.Talk;
import parser.DefaultFormatParser;

/**
 * Execute the project. For more information about the project check the file "design.txt".
 * @author Nielson
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
    	if(args == null || args.length < 1) {
    		throw new RuntimeException("You must define the application properties path as parameter.");
    	}
    	
    	Configuration.init(args[0]);
    	
    	File file = new File (Configuration.getTalksFilePath());
    	
    	DefaultFormatParser dfp = DefaultFormatParser.getInstance();
    	
    	List<Talk> talks = dfp.parser(file);
    	BuildConferenceAlgorithm buildConferenceAlgorithm = BuildingAlgorithmFactory.getInstance().getAlgorithmToBuildConference(Configuration.getAlgorithm());
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
