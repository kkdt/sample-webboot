package kkdt.sample.webboot.client;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

/**
 * <p>
 * The main application window.
 * </p>
 * 
 * @author thinh
 *
 */
public class ApplicationConsole extends JFrame {
   private static final long serialVersionUID = -5575311618949877211L;
   
   private JLabel dataLabel = new JLabel("N/A");
   private AuthenticationPanel authenticationPanel;
   private ApplicationController applicationController;
   
   /**
    * <p>
    * Associate this GUI with the controller.
    * </p>
    * 
    * @param applicationController the main controller.
    */
   public ApplicationConsole(ApplicationController applicationController) {
      super("Application Console");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Assert.notNull(applicationController, "Application controller cannot be null");
      this.applicationController = applicationController;
   }
   
   /**
    * <p>
    * Initialization can be invoked via Spring or programmatically and will visually
    * show/pack the application window.
    * </p>
    * 
    */
   public void init() {
      authenticationPanel = new AuthenticationPanel();
      
      JPanel data = new JPanel();
      data.setBorder(BorderFactory.createEtchedBorder());
      data.add(dataLabel);
      
      JPanel c = new JPanel(new BorderLayout(5,5));
      c.add(authenticationPanel, BorderLayout.CENTER);
      c.add(data, BorderLayout.SOUTH);
      
      Container content = this.getContentPane();
      content.setLayout(new BorderLayout());
      content.add(c, BorderLayout.CENTER);
      
      authenticationPanel.getActionButton().addActionListener(applicationController);;
      
      this.setResizable(false);
      this.pack();
      this.setVisible(true);
   }
   
   /**
    * <p>
    * The value the user typed in the input for login.
    * </p>
    * 
    * @return
    */
   public String getLoginUser() {
      return authenticationPanel.getUser();
   }
   
   /**
    * <p>
    * Display a message on the main window.
    * </p>
    * 
    * @param message the message.
    */
   public void displayMessage(String message) {
      SwingUtilities.invokeLater(() -> {
         dataLabel.setText(message);
      });
   }
   
   /**
    * <p>
    * Callback for the GUI to handle an authentication change.
    * </p>
    * 
    * @param auth the authentication.
    */
   public void handleAuthentication(Authentication auth) {
      if(auth != null) {
         if(auth.isAuthenticated()) {
            displayMessage("User successfully logged in: "+ auth.getPrincipal());
            authenticationPanel.setActionLabel("Logout");
            authenticationPanel.enableUserInput(false);
         } else {
            displayMessage("Invalid user: " + auth.getPrincipal());
         }
      } else {
         displayMessage("N/A");
         authenticationPanel.setActionLabel("Login");
         authenticationPanel.enableUserInput(true);
      }
   }

}
