import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher implements Runnable {
  State state;
  Fork left, right;
  int id;

  long sleeper = (long) (Math.random() * 100) + 50;
  long eater =  (long) (Math.random() * 40) + 10;

  public Philosopher(int id, Fork left, Fork right, State state) {
    this.state = state;
    this.left = left;
    this.right = right;
    this.id = id;
  }

  private void eat() {
    try {
      Thread.sleep(eater);
      System.out.println(id+" Eat");
    } catch (Exception e) {
    }
  }

  private void think() {
    try {
      Thread.sleep(10);
      System.out.println(id + " think");
    } catch (Exception e) {
    }
  }

  public void run() {
    while (true) {
      state.grabChopsticks(id, left, right);
      eat();
      state.releaseChopsticks(id, left, right);
      think();
    }
  }
}

public class Main {
  public static void main(String[] args) {
    Fork[] s = new Fork[5];
    Philosopher[] f = new Philosopher[5];
    State state = new State();
    for (int i = 0; i < 5; i++) {
      s[i] = new Chopstick();
    }
    for (int i = 0; i < 5; i++) {
      f[i] = new Philosopher(i, s[i], s[(i + 4) % 5], state);
      new Thread(f[i]).start();
    }
  }
}
class Fork {
  private boolean availability;

  public Fork() {
    availability = true;
  }

  public boolean getAvailability() {
    return availability;
  }

  public void setAvailability(boolean position) {
    availability = position;
  }
}

class State {
  Lock mutex = new ReentrantLock();
  Condition[] cond = new Condition[5];
  String[] state = new String[5];
  int[] id = new int[5];

  void outputState(int id) {
    StringBuffer line = new StringBuffer();
    for (int i = 0; i < 5; i++){
      line.append(state[i] + " ");
    }
    System.out.println(line + "(" + (id + 1) + ")");
  }

  public State() {
    for (int i = 0; i < 5; i++) {
      id[i] = i;
      state[i] = "O";
      cond[i] = mutex.newCondition();
    }
  }

  public void setState(int id, String s) {
    state[id] = s;
  }

  public void grabFork(int id, Fork left, Fork right) {
    mutex.lock();
    try {
      setState(id, "o");
      while (!left.getAvailability() || !right.getAvailability()) {
        cond[id].await();
      }
      left.setAvailability(false);
      right.setAvailability(false);
      setState(id, "X");
      outputState(id);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mutex.unlock();
    }
  }

  public void releaseFork(int id, Fork left, Fork right) {
    mutex.lock();
    setState(id, "O");
    left.setAvailability(true);
    right.setAvailability(true);
    cond[(id + 1) % 5].signalAll();
    cond[(id + 4) % 5].signalAll();
    outputState(id);
    mutex.unlock();
  }
}