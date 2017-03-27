package kkdt.sample.webboot.client;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import kkdt.sample.webboot.CounterModel;

public class SampleConsole implements ClientApplication {
   private static final Logger logger = Logger.getLogger(SampleConsole.class);
   /**
    * This is the spring security context to use for the entire JVM.
    * 
    * Built-in by Spring and recommended for Swing applications -
    * MODE_GLOBAL = org.springframework.security.core.context.GlobalSecurityContextHolderStrategy
    */
   private static final String securityContextStrategyName = "kkdt.sample.webboot.security.ApplicationSecurityContextStrategy";
   
   private ApplicationConsole applicationConsole;
   
   private CounterModel counterModel;
   
   private ApplicationController applicationController;

   public void setApplicationConsole(ApplicationConsole applicationConsole) {
      this.applicationConsole = applicationConsole;
   }

   public void setCounterModel(CounterModel counterModel) {
      this.counterModel = counterModel;
   }

   public void setApplicationController(ApplicationController applicationController) {
      this.applicationController = applicationController;
   }

   public void init() throws Exception {
      logger.info("(thick client) " + SampleConsole.class.getName() + " starting up");
      
      SecurityContextHolder.setStrategyName(securityContextStrategyName);
      
      // manually set up MVC
      applicationController.setApplicationConsole(applicationConsole);
      counterModel.addObserver(applicationController);
      
   }
}
