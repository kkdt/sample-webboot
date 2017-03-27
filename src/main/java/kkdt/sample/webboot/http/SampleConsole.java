package kkdt.sample.webboot.http;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.util.Assert;

/**
 * <p>
 * The Spring Boot web application.
 * </p>
 * 
 * @author thinh
 *
 */
@SpringBootApplication
@EnableWebSecurity
@ImportResource({
   // import additional resources as needed
   "classpath:webContext.xml"
})
public class SampleConsole implements ApplicationRunner 
{
   private static final Logger logger = Logger.getLogger(SampleConsole.class);
   
   @Autowired(required=true)
   private CounterEndpoint counterEndpoint;
   
   @Override
   public void run(ApplicationArguments args) throws Exception {
      logger.info("(thin client) " + SampleConsole.class.getName() + " starting up"); 
      Assert.notNull(counterEndpoint, "An endpoint to expose the counter model must exist");
   }
}
