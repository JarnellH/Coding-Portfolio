import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.io.*;

//Question 1 Solution

class Philosopher implements Runnable{
  State state;
  Fork left, right;
  int id;

  String file = "/Users/nelly/OS/A3/Output1.txt";
  FileWriter writer ; 
  PrintWriter print = null;

  long temp2 =0;
  long temp3 =0;
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

      writer = new FileWriter(file , true);
      print = new PrintWriter(writer);
      long follow = System.nanoTime();
      Thread.sleep(eater);
      long temp = System.nanoTime();

      follow = (temp - follow)/ 1000000;

      //write to a file
      print.println("Philosopher "+(id+1)+" is Eating for "+ follow +"ms ");
      temp3 = temp3 + follow;
      print.close();


    } catch(Exception e) {e.printStackTrace();
    }
  }

  private void think() {
    try {

      writer = new FileWriter(file , true);
      print = new PrintWriter(writer);
      long track = System.nanoTime();
      Thread.sleep(10);
      long track2 = System.nanoTime();
      track = (track2 - track) /1000000;

      //write this to a file 
      print.println("Philosopher "+ (id +1) + " is Thinking for " + track +"ms ") ;
      temp2 = temp2 + track;
      print.close();
    } catch (Exception e) {e.printStackTrace();
    }
  }


  @Override
  public void run() {

    try{
    int duration = 0;
    writer =  new FileWriter(file, true);
    print = new PrintWriter(writer);

    while (duration < 61) {
      state.grabFork(id, left, right);
      eat();
      state.releaseFork(id, left, right);
      think();
     Thread.sleep(1000);
      duration++;
    }
    //write to file 
     
     print.println((temp2 /5) + "ms is the average time spent Thinking");
     print.println((temp3/5) + "ms is the average time spent Eating");
     print.println((state.temp/5) + "ms is the average time being Hungry\n");

     print.close();

  }catch(Exception e){e.printStackTrace();}
  }

    public static void main(String[] args) {
    Fork[] forks = new Fork[5];
    Philosopher[] philosophers = new Philosopher[5];
    State state = new State();
    for (int i = 0; i < 5; i++) {
      forks[i] = new Fork();
    }

    for (int i = 0; i < 5; i++) {
      philosophers[i] = new Philosopher(i, forks[i], forks[(i + 4) % 5], state);
      new Thread(philosophers[i]).start();
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
  long temp = 0;
  long time = 0;
  Lock mutex = new ReentrantLock();
  Condition[] cond = new Condition[5];
  String[] state = new String[cond.length];
  int[] id = new int[state.length];

  String dest = "/Users/nelly/OS/A3/Output1.txt";

  FileWriter out; 
  PrintWriter pw = null; 


  void outputState(int id) {
    StringBuffer line = new StringBuffer();
    for (int i = 0; i < 5; i++){
      line.append(state[i] + " ");
      
    }
    
  }
  
  public State() {
    for (int i = 0; i < 5; i++) {
      id[i] = i;
      state[i] = "O";
      cond[i] = mutex.newCondition();

    }
  }

  public void setState(int id, String string) {
    state[id] = string;
  }

  public void grabFork(int id, Fork left, Fork right) {
    mutex.lock();
    try {

      out = new FileWriter(dest , true);
      pw = new PrintWriter(out);
       temp = temp;
       time = System.nanoTime();
      setState(id, "Hungry");
      while (!left.getAvailability() || !right.getAvailability()) {
        long sleeper = (long) (Math.random() * 100) + 50;
        cond[id].awaitNanos(sleeper);


      }
      left.setAvailability(false);

      System.out.println("Philosopher " + (id +1)+ " picked up left fork, Time: "+(System.nanoTime()/1000000)+"ms ");
      right.setAvailability(false);
      long timer = System.nanoTime();
    

      long hold = timer;
      hold = hold / 1000000;
      timer = (timer - time) / 1000000;

      //write to file
      System.out.println("Philosopher " + (id +1)+ " picked up right fork, Time: "+(hold) +"ms was hungry for "+ timer+"ms " );

      pw.println("Philosopher "+(id + 1)+" was hungry for "+timer+"ms ");
      
      
      temp = temp + timer;
      

      setState(id, "Eating");
      outputState(id);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      pw.close();
      mutex.unlock();
     
    }
  }

  public void releaseFork(int id, Fork left, Fork right) {
    mutex.lock();
    setState(id, "Thinking");
    left.setAvailability(true);
    System.out.println("Philosopher " + (id +1)+ " put down left fork " );
    right.setAvailability(true);
    System.out.println("Philosopher " + (id +1)+ " put down right fork " );
    cond[(id + 1) % 5].signalAll();
    cond[(id + 4) % 5].signalAll();
    outputState(id);
    mutex.unlock();
  }
}