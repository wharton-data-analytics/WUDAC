interface Counter {
  public void inc();
  public int get();
}

class UCounter implements Counter {
  private int cnt = 0;

  public void inc() {
    cnt = cnt + 1;
  }

  public int get() {
    return cnt;
  }
}



// The computation thread simply increments
// the provided counter 1000 times
class CounterUser implements Runnable {
  private Counter c;
  private int id;

  CounterUser(int id, Counter c) {
    this.id = id;
    this.c = c;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
    	  // System.out.println("Thread: " + id);
      c.inc();
    }
  }
}

public class MultiThreaded {

  public static void main(String[] args) {
    
    Counter c = new SynchronizedCounter();  
    
    // set up a race on the shared counter c
    Thread t1 = new Thread(new CounterUser(1, c));
    Thread t2 = new Thread(new CounterUser(2, c));
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
    }
    
    System.out.println("Counter value = " + c.get());
  }

}



//This class uses synchronization
class SynchronizedCounter implements Counter {
private int cnt = 0;

public synchronized void inc() {
 cnt = cnt + 1;
}

public synchronized int get() {
 return cnt;
}
}
