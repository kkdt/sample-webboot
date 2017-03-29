package kkdt.sample.webboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

/**
 * <p>
 * This is the recommended way for securing a web application but rather than it
 * be within an {@linkplain Configuration} container, this is a bean within a Spring
 * XML file so that the {@linkplain ApplicationSecurityFilter} can be injected
 * and configured before Spring Security filters.
 * </p>
 * 
 * @author thinh
 *
 */
public class SecuredApplicationConfigurer extends WebSecurityConfigurerAdapter {
   private AuthenticationProvider authenticationProvider;
   private ApplicationSecurityFilter applicationSecurityFilter;

   public void setApplicationSecurityFilter(ApplicationSecurityFilter applicationSecurityFilter) {
      this.applicationSecurityFilter = applicationSecurityFilter;
   }

   public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
      this.authenticationProvider = authenticationProvider;
   }
   
   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authenticationProvider);
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      // http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html
      // ChannelProcessingFilter is the first filter in the chain so the application filter
      // goes before it
      http.antMatcher("/**").addFilterBefore(applicationSecurityFilter, ChannelProcessingFilter.class)
         .authorizeRequests()
         .anyRequest().authenticated().and().httpBasic();
      
   }
}
