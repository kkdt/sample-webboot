package kkdt.sample.webboot.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import kkdt.sample.webboot.CounterModel;

public class ApplicationController 
   implements Observer, ActionListener
{
   private static final Logger logger = Logger.getLogger(ApplicationController.class);
   
   private ApplicationConsole application;
   private AuthenticationProvider authenticationProvider;
   
   public ApplicationController(AuthenticationProvider authenticationProvider) {
      Assert.notNull(authenticationProvider, "Authentication provider cannot be null");
      this.authenticationProvider = authenticationProvider;
   }
   
   public ApplicationConsole getApplicationConsole() {
      return application;
   }

   public void setApplicationConsole(ApplicationConsole application) {
      this.application = application;
   }

   @Override
   public void update(Observable o, Object arg) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if(auth == null || !auth.isAuthenticated()) {
         return;
      }
      
      if(o instanceof CounterModel) {
         CounterModel model = (CounterModel)o;
         logger.debug("Received counter: " + model.getCounter());
         
         if(application != null) {
            application.displayMessage(String.valueOf("" + arg));
         }
      }
   }
   
   private void doLogin(String user) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null);
      Authentication auth = authenticationProvider.authenticate(authentication);
      if(auth.isAuthenticated()) {
         SecurityContextHolder.getContext().setAuthentication(auth);
      }
      application.handleAuthentication(auth);
   }
   
   private void doLogout() {
      SecurityContextHolder.getContext().setAuthentication(null);
      application.handleAuthentication(SecurityContextHolder.getContext().getAuthentication());
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      if("login".equalsIgnoreCase(command)) {
         doLogin(application.getLoginUser());
      } else if("logout".equalsIgnoreCase(command)) {
         doLogout();
      }
      
   }

}
