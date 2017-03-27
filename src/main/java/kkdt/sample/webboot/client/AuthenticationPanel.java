package kkdt.sample.webboot.client;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * <p>
 * The panel to wrap the user input and login/logout button; should only be used
 * by this package.
 * </p>
 * 
 * @author thinh
 *
 */
class AuthenticationPanel extends JPanel {
   private static final long serialVersionUID = 1094524418528940755L;
   
   private JButton actionBtn = new JButton("Login");
   private JLabel userLabel = new JLabel("User");
   private JTextField userInput = new JTextField(25);
   
   /**
    * <p>
    * Base {@code JPanel} constructor.
    * </p>
    */
   AuthenticationPanel() {
      actionBtn.setSize(50, 100);
      
      this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
      this.add(userLabel);
      this.add(userInput);
      this.add(actionBtn);
      this.setPreferredSize(new Dimension(450, 100));
   }
   
   /**
    * <p>
    * Change the action command of the button.
    * </p>
    * 
    * @param text
    */
   void setActionLabel(String text) {
      SwingUtilities.invokeLater(() -> {
         actionBtn.setText(text);
      });
   }
   
   /**
    * <p>
    * Give access to the action button.
    * </p>
    * 
    * @return
    */
   JButton getActionButton() {
      return actionBtn;
   }
   
   /**
    * <p>
    * The current value of the user input component.
    * </p>
    * 
    * @return the user text.
    */
   String getUser() {
      return userInput.getText();
   }
   
   /**
    * <p>
    * Enable or disable the user input component.
    * </p>
    * 
    * @param enable true will (re)enable, false disable.
    */
   void enableUserInput(boolean enable) {
      SwingUtilities.invokeLater(() -> {
         userInput.setEnabled(enable);
      });
   }

}
