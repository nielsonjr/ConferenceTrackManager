package ctm;

import java.io.File;
import java.util.List;

import config.Configuration;
import generator.Scheduler;
import generator.buildConference.ConferenceGenerator;
import generator.buildConference.ConferenceGeneratorFactory;
import model.conference.Conference;
import model.talk.Talk;
import parser.IParser;
import parser.ParserFactory;

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
    	
    	IParser parser = ParserFactory.getInstance().getParser(Configuration.getParser());
    	List<Talk> talks = parser.parse(file);
    	
    	ConferenceGenerator buildConferenceAlgorithm = ConferenceGeneratorFactory.getInstance().getAlgorithmToBuildConference(Configuration.getAlgorithm());
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
