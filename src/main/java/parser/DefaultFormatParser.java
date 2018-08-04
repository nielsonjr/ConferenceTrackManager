package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.ConferenceTrackManagerException;
import validators.DurationValidator;
import validators.IValidator;
import validators.NoNumberValidator;
import model.talk.CommomTalk;
import model.talk.LightningTalk;
import model.talk.Talk;

/**
 * This class is responsible to parser the file with the rules defined in the project:
 * - A line from must have a name and a duration separated by a blank space
 * - A talk name must not have numbers
 * - Each line is a different talk
 * @author Nielson
 *
 */
public class DefaultFormatParser implements IParser {
	private List<IValidator<String>> validators = new ArrayList<IValidator<String>>();
	private static DefaultFormatParser instance = null;
	
	private DefaultFormatParser() {
		//TODO iterar no pacote de validador e adiciona todos
		validators.add(new DurationValidator());
		validators.add(new NoNumberValidator());
	}
	
	public static DefaultFormatParser getInstance() {
		if(instance == null) {
			instance = new DefaultFormatParser();
		}
		
		return instance;
	}
	
	@Override
	public List<Talk> parser(File file) throws ConferenceTrackManagerException {
		List<Talk> talks = new ArrayList<Talk>();
		String content = null;
		
		try {
			Scanner fileScanner = new Scanner(file);
			
			while (fileScanner.hasNext()) {
				content = (String) fileScanner.nextLine();
				
				boolean isValid = validateString(content);
				
				if(isValid) {
					Talk talk = createTalk(content);
					talks.add(talk);
				}
				else {
					throw new ConferenceTrackManagerException("The name is invalid. Change the name: '" + content + "' for a valid name");
				}
			}
			
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return talks;
	}

	private boolean validateString(String content) {
		boolean isValid = true;
		for (IValidator<String> validator : validators) {
			isValid = isValid && validator.validate(content);
		}
		
		return isValid;
	}
	
	private Talk createTalk (String content) {
		String duration = content.substring(content.lastIndexOf(" ") + 1); 
		String name = content.substring(0, content.lastIndexOf(" "));		
		Talk talk = null;
		
		if(duration.equalsIgnoreCase("lightning")) {
			talk = new LightningTalk(name);
		}
		else {
			String durationWithoutExtension = duration.split("min")[0];
			talk = new CommomTalk(name, Integer.valueOf(durationWithoutExtension));
		}
		
		return talk;
	}
	
	
}
