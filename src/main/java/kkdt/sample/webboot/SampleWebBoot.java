package kkdt.sample.webboot;

import org.apache.log4j.Logger;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SampleWebBoot {
   private static final Logger logger = Logger.getLogger(SampleWebBoot.class);
   
   public static void main(String[] args) {
      logger.info("======================================================");
      logger.info("Starting application...");
      logger.info("======================================================");
      
      logger.info("Creating client application context...");
      ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("swingContext.xml");
      
      logger.info("Creating Sprint Boot web context...");
      SpringApplicationBuilder web = new SpringApplicationBuilder(kkdt.sample.webboot.http.SampleConsole.class)
         .bannerMode(Mode.LOG);
      web.headless(true).web(true)
         .parent(appContext) // bootstrap web application with swing app
         .run(args);
   }
}
