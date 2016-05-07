import org.apache.log4j.Logger;
import org.junit.Test;

public class LogTest {
	Logger  logger = Logger.getLogger(this.getClass());


	@Test
	public void Test(){
		logger.info("Starting...");
		logger.debug("Very detailed log");
		logger.fatal("Terribly Failed!");
	}
	

}