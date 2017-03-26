package kkdt.sample.webboot.client;

import kkdt.sample.webboot.CounterModel;

public class ApplicationConsoleTest {

   public static void main(String[] args) {
      CounterModel counterModel = new CounterModel(3000);
      ApplicationConsole applicationConsole = new ApplicationConsole();
      ApplicationController applicationController = new ApplicationController();
      
      applicationConsole.init();
      applicationController.setApplicationConsole(applicationConsole);
      counterModel.addObserver(applicationController);
      
      // show the frame
      applicationConsole.pack();
      applicationConsole.setVisible(true);

   }

}
