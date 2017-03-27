package kkdt.sample.webboot.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.Assert;

public final class ApplicationSecurityContextStrategy implements SecurityContextHolderStrategy {
   private static final Logger logger = Logger.getLogger(ApplicationSecurityContextStrategy.class);
   
   private static SecurityContext contextHolder;

   @Override
   public void clearContext() {
      // do nothing
      logger.info("Clearing context, not really...");
   }

   @Override
   public SecurityContext getContext() {
      if (contextHolder == null) {
         logger.info("Creating new security context");
         contextHolder = new SecurityContextImpl();
      }
      return contextHolder;
   }

   @Override
   public void setContext(SecurityContext context) {
      Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
      logger.info("Setting context to " + context);
      contextHolder = context;
   }

   @Override
   public SecurityContext createEmptyContext() {
      logger.info("Creating empty context... (kinda, not really)");
      return contextHolder;
   }
}
