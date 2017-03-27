package kkdt.sample.webboot.client;

import kkdt.sample.webboot.CounterModel;
import kkdt.sample.webboot.security.SingleUserAuthenticationProvider;

public class ApplicationConsoleTest {

   public static void main(String[] args) {
      CounterModel counterModel = new CounterModel(3000);
      ApplicationController applicationController = new ApplicationController(new SingleUserAuthenticationProvider("admin"));
      ApplicationConsole applicationConsole = new ApplicationConsole(applicationController);
      
      applicationConsole.init();
      applicationController.setApplicationConsole(applicationConsole);
      counterModel.addObserver(applicationController);
      
      // show the frame
      applicationConsole.pack();
      applicationConsole.setVisible(true);

   }

}
