package kkdt.sample.webboot;

import org.apache.log4j.Logger;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * The Gradle Spring Boot plugin will associate this class as the executable main
 * class when building the jar since this is the only one in this project.
 * </p>
 * 
 * <p>
 * The scenario is:
 * <ol>
 *    <li>Thick Swing clients are legacy and are configured by manually building
 *    the {@code ClassPathXmlApplicationContext} to wire in their components.</li>
 *    <li>Spring Boot Application containers are recommended to be {@code Configuration}
 *    annotated, or {@code SpringBootApplication}.</li>
 *    <li>The "newer" Spring Boot webapp will be bootstrapped with the legacy 
 *    thick client, sharing application contexts and all the beans defined.</li>
 * </ol>
 * </p>
 * 
 * @author thinh
 *
 */
public class SampleWebBoot {
   private static final Logger logger = Logger.getLogger(SampleWebBoot.class);
   
   public static void main(String[] args) {
      logger.info("======================================================");
      logger.info("Starting application...");
      logger.info("======================================================");
      
      // splitting up multiple configurations for demo
      String[] configurations = new String[] {
         "swingContext.xml", // GUI 
         "counterModel.xml", // model
         "securityContext.xml" // spring security
      };
      
      logger.info("Creating client application context...");
      ConfigurableApplicationContext appContext = 
         new ClassPathXmlApplicationContext(configurations);
      
      logger.info("Creating Sprint Boot web context...");
      SpringApplicationBuilder web = new SpringApplicationBuilder(kkdt.sample.webboot.http.SampleConsole.class)
         .bannerMode(Mode.OFF);
      web.headless(true).web(true) // these flags should already be default
         .parent(appContext) // bootstrap web application with swing
         .run(args);
   }
}
