package parser;

import util.ConstantsApp;

public class ParserFactory {
	private static ParserFactory instance = null;
	
	private ParserFactory() {}
	
	public static ParserFactory getInstance() {
		
		if(instance == null) {
			instance = new ParserFactory();
		}
		
		return instance;
	}
	
	public IParser getParser(String name) {
		if(name == null) {
			return null;
		}
		
		IParser parser = null;
		
		//new identifications can be added, when new algorithms are developed.
		if(name.equalsIgnoreCase(ConstantsApp.PARSER_TEXT_FILE_NAME)) {
			parser = new DefaultFormatParser();
		} 	
		
		return parser;
	}
}
