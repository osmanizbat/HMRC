package test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author osmanizbat
 */
public class HMRC {
  
  private static final int THREAD_POOL_SIZE = 2;
  private static final int CACHE_SIZE = 8192;

  private final Map<String, Object> cache = new HashMap<String, Object>();

  public void run() {

    ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    Runnable runnableTask = new Runnable() {
      @Override
      public void run() {
        System.out.printf("%s started %n", Thread.currentThread().getName());
        for (int i = 0; i < CACHE_SIZE; i++) {
          String key = String.valueOf(randomKey());
          Object value = getValue(key);
        }
        System.out.printf("%s finished %n", Thread.currentThread().getName());
      }
    };
    
    for(int i = 0; i < THREAD_POOL_SIZE; i++){
      executor.execute(runnableTask);      
    }
    
    executor.shutdown();

  }

  private int randomKey() {
    return ThreadLocalRandom.current().nextInt(0, CACHE_SIZE);
  }

  private Object randomValue() {
    return ThreadLocalRandom.current().nextDouble();
  }

  private Object getValue(String key) {
    Object value = cache.get(key);

    if (value == null) {
      value = randomValue();
      cache.put(key, value);
    }

    return value;
  }

  public static void main(String[] args) {
    HMRC hmrc = new HMRC();
    hmrc.run();
  }

}
