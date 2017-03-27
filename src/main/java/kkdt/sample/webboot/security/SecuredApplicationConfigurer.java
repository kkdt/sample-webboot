package kkdt.sample.webboot.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

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
      http.antMatcher("/**").addFilterBefore(applicationSecurityFilter, ChannelProcessingFilter.class)
         .authorizeRequests()
         .anyRequest().authenticated().and().httpBasic();
      
   }
}
