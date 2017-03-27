package kkdt.sample.webboot.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * An extension of {@code UsernamePasswordAuthenticationToken} but with an identifier
 * and token that are generated after a successful client log-in.
 * </p>
 * 
 * @author thinh
 *
 */
public class SimpleTokenAuthentication extends UsernamePasswordAuthenticationToken {
   private static final long serialVersionUID = 194317432584672257L;
   
   private String id;
   private String token;

   /**
    * <p>
    * Unauthenticated, created after authentication with the provider.
    * </p>
    * 
    * @param principal user identifier.
    * @param credentials (not used).
    */
   public SimpleTokenAuthentication(Object principal, Object credentials) {
      super(principal, credentials);
   }
   
   /**
    * <p>
    * Authenticated constructor only if the {@code authorities} are valid.
    * </p>
    * 
    * @param principal the user identifier.
    * @param credentials (not used).
    * @param authorities granted authorities (i.e. admin).
    */
   public SimpleTokenAuthentication(Object principal,Object credentials, Collection<GrantedAuthority> authorities) {
      super(principal, credentials, authorities);
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }
}

