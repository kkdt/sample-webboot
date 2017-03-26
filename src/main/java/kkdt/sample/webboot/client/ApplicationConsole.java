package kkdt.sample.webboot.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ApplicationConsole extends JFrame {
   private static final long serialVersionUID = -5575311618949877211L;
   
   private JLabel dataLabel = new JLabel("N/A");
   
   public ApplicationConsole() {
      super("Application Console");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public void init() {
      AuthenticationPanel top = new AuthenticationPanel();
      
      JPanel data = new JPanel();
      data.setSize(new Dimension(200, 200));
      data.add(dataLabel);
      
      JPanel c = new JPanel(new BorderLayout(5,5));
      c.setLayout(new BorderLayout(5,5));
      c.add(top, BorderLayout.NORTH);
      c.add(data, BorderLayout.CENTER);
      
      Container content = this.getContentPane();
      content.setLayout(new BorderLayout());
      content.add(c, BorderLayout.CENTER);
      
      this.setResizable(false);
      this.pack();
      this.setVisible(true);
   }
   
   public void displayMessage(String message) {
      SwingUtilities.invokeLater(() -> {
         dataLabel.setText(message);
      });
   }

}
