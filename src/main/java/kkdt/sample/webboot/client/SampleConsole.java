package kkdt.sample.webboot.client;

import org.apache.log4j.Logger;

import kkdt.sample.webboot.CounterModel;

public class SampleConsole implements ClientApplication {
   private static final Logger logger = Logger.getLogger(SampleConsole.class);
   
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
      
      // manually set up MVC
      applicationController.setApplicationConsole(applicationConsole);
      counterModel.addObserver(applicationController);
      
   }
}
