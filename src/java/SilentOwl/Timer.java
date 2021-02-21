package SilentOwl;

public class Timer implements Runnable {
    int seconds;
    Thread t;

    public Timer() {
        this.seconds = 0;
        t = new Thread(this, "Clap Timer");
        t.start(); // Start the thread
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (seconds < 2) {
            //Wait 1 second
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Waiting interupted.");
            }

            seconds++;
        }
    }
}
