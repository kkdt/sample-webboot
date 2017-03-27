package kkdt.sample.webboot.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html
 * org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy
 * 
 * @author thinh
 *
 */
public class ApplicationSecurityFilter extends GenericFilterBean
{
   private static final Logger logger = Logger.getLogger(ApplicationSecurityFilter.class);

   @Override
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException 
   {
      final HttpServletRequest request = (HttpServletRequest)req;
      final HttpServletResponse response = (HttpServletResponse)res;
      Authentication current = SecurityContextHolder.getContext().getAuthentication();
      
      logger.info("Filtering request... " + request);
      logger.info("SecurityContextHolder: " + current);
      
      if(current != null && current.isAuthenticated()) {
         HttpSession session = request.getSession();
         if(session == null) {
            logger.info("The current HTTP session does not exist... creating one with the current logged in authentication " + current);
            session = request.getSession(true);
         }
         
         logger.info("Found session: " + session.getId() + ", created: " + new Date(session.getCreationTime()));
         
         String securityAttr = "SPRING_SECURITY_CONTEXT";
         if(session.getAttribute(securityAttr) == null) {
            session.setAttribute(securityAttr, SecurityContextHolder.getContext());
         }
         chain.doFilter(request, response);
      } else {
         response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      }
   }

   
}
