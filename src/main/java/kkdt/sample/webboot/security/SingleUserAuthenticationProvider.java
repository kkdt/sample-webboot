package kkdt.sample.webboot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * <p>
 * An authentication provider that takes any {@code Authentication} type and will
 * only look at the principal and compares it to the valid configured during this
 * provider's instantiation. This provider will only allow one single user identifier
 * to log into the application.
 * </p>
 * 
 * @author thinh
 *
 */
public class SingleUserAuthenticationProvider implements AuthenticationProvider {
   private static final Logger logger = Logger.getLogger(SingleUserAuthenticationProvider.class);
   
   private String authorizedUser;
   
   /**
    * <p>
    * Must instantiate with a non-empty user.
    * </p>
    * 
    * @param authorizedUser the only user who will successfully authenticate.
    */
   public SingleUserAuthenticationProvider(String authorizedUser) {
      Assert.notNull(authorizedUser, "Authorized user must be provided");
      this.authorizedUser = authorizedUser;
   }

   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      logger.info("Authenticating: " + authentication);
      Authentication auth = null;
      if(authentication != null && authentication.getPrincipal() != null) {
         boolean valid = authorizedUser.equalsIgnoreCase((String)authentication.getPrincipal());
         SimpleTokenAuthentication a = new SimpleTokenAuthentication(authentication.getPrincipal(), null);
         if(valid) {
            Collection<GrantedAuthority> grants = new ArrayList<>();
            grants.add(new SimpleGrantedAuthority("ADMIN"));
            a = new SimpleTokenAuthentication(authentication.getPrincipal(), null, grants);
            a.setId(SingleUserAuthenticationProvider.class.getSimpleName());
            a.setToken(UUID.randomUUID().toString());
         }
         auth = a;
      }
      return auth;
   }

   @Override
   public boolean supports(Class<?> authentication) {
      // accept everything
      return true;
   }

}
