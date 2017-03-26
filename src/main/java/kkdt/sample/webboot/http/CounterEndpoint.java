package kkdt.sample.webboot.http;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kkdt.sample.webboot.CounterModel;

@RestController
public class CounterEndpoint {
   private static final Logger logger = Logger.getLogger(CounterEndpoint.class);
   
   private CounterModel counterModel;
   
   public void setCounterModel(CounterModel counterModel) {
      this.counterModel = counterModel;
   }

   @RequestMapping(path="/counter", method=RequestMethod.GET)
   public String counter() {
      String value = "N/A";
      if(counterModel != null) {
         value = String.valueOf(counterModel.getCounter());
      } else {
         logger.warn("Counter Model does not exist");
      }
      return value;
   }
}
