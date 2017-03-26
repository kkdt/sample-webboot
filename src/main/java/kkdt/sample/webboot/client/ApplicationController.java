package kkdt.sample.webboot.client;

import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import kkdt.sample.webboot.CounterModel;

public class ApplicationController implements Observer {
   private static final Logger logger = Logger.getLogger(ApplicationController.class);
   
   private ApplicationConsole application;
   
   public ApplicationConsole getApplicationConsole() {
      return application;
   }

   public void setApplicationConsole(ApplicationConsole application) {
      this.application = application;
   }

   @Override
   public void update(Observable o, Object arg) {
      if(o instanceof CounterModel) {
         CounterModel model = (CounterModel)o;
         logger.info("Received counter: " + model.getCounter());
         
         if(application != null) {
            application.displayMessage(String.valueOf("" + arg));
         }
      }
   }

}
