package kkdt.sample.webboot;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

/**
 * <p>
 * Counter model that will notify all listeners of the current counter.
 * </p>
 * 
 * @author thinh
 *
 */
public class CounterModel extends Observable {
   private static final Logger logger = Logger.getLogger(CounterModel.class.getName());
   private static final long period = 2000L;
   
   private long counter = 0;
   private long interval = period;
   
   /**
    * <p>
    * Simple counter task that will update an internal counter for the specified
    * interval.
    * </p>
    * 
    * @param interval milliseconds.
    */
   public CounterModel(long interval) {
      this.interval = interval;
   }
   
   public void init() {
      logger.info("Starting counter model, interval: " + interval);
      Timer timer = new Timer(CounterModel.class.getSimpleName());
      timer.scheduleAtFixedRate(new TimerTask() {
         @Override
         public void run() {
            counter++;
            logger.info("Counter updated: " + counter);
            
            setChanged();
            try {
               notifyObservers("Current application counter: " + counter);
            } catch (Exception e) {
               logger.error(e);
            } finally {
               clearChanged();
            }
         }
      }, period, interval);
   }
   
   /**
    * <p>
    * The current counter value.
    * </p>
    * 
    * @return the counter.
    */
   public synchronized long getCounter() {
      return counter;
   }

}
