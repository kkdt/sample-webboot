package kkdt.sample.webboot.client;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AuthenticationPanel extends JPanel {
   private static final long serialVersionUID = 1094524418528940755L;
   
   private JButton actionBtn = new JButton("Login");
   private JLabel userLabel = new JLabel("User");
   private JTextField userInput = new JTextField(25);
   
   public AuthenticationPanel() {
      actionBtn.setSize(100, 100);
      
      this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
      this.add(userLabel);
      this.add(userInput);
      this.add(actionBtn);
   }
   
   public void setActionLabel(String text) {
      SwingUtilities.invokeLater(() -> {
         actionBtn.setText(text);
      });
   }

}
