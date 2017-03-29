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

/**
 * <p>
 * Swing controller that is hooked up to the Login/Logout button as an {@linkplain ActionListener}.
 * </p>
 * 
 * @author thinh
 *
 */
public class ApplicationController 
   implements Observer, ActionListener
{
   private static final Logger logger = Logger.getLogger(ApplicationController.class);
   
   private ApplicationConsole application;
   private AuthenticationProvider authenticationProvider;
   
   /**
    * <p>
    * Must instantiate with an authentication provider for logging the user into
    * the application.
    * </p>
    * 
    * @param authenticationProvider the authentication provider.
    */
   public ApplicationController(AuthenticationProvider authenticationProvider) {
      Assert.notNull(authenticationProvider, "Authentication provider cannot be null");
      this.authenticationProvider = authenticationProvider;
   }
   
   /**
    * <p>
    * The main view.
    * </p>
    * 
    * @param application
    */
   public void setApplicationConsole(ApplicationConsole application) {
      this.application = application;
   }

   @Override
   public void update(Observable o, Object arg) {
      /*
       * Does not process notification if the user is not authenticated
       */
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
   
   /**
    * <p>
    * Helper that performs the login by delegating the authentication to the 
    * configured {@code AuthenticationProvider}. If successful, then the 
    * {@linkplain SecurityContextHolder} is updated with the authentication returned
    * by the provider.
    * </p>
    * 
    * @param user the user to authenticate.
    */
   private void doLogin(String user) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null);
      Authentication auth = authenticationProvider.authenticate(authentication);
      if(auth.isAuthenticated()) {
         SecurityContextHolder.getContext().setAuthentication(auth);
      }
      application.handleAuthentication(auth);
   }
   
   /**
    * <p>
    * Null out the current authentication held in {@linkplain SecurityContextHolder}.
    * </p>
    */
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
