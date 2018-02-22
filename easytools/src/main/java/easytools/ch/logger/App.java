package easytools.ch.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	
	static Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args ){
    	logger.debug("debug");
    	logger.info("info");
    	logger.error("error");
    	logger.warn("warn");
    	logger.trace("trace");
    	logger.fatal("fatal");
    }
    
}
