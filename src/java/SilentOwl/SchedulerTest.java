package SilentOwl;
import java.time.LocalTime;

//import static org.junit.Assert.*;


class SchedulerTest {
    public static void main(String[] args) {

        LocalTime ltStart = LocalTime.of(23,0);
        LocalTime ltEnd= LocalTime.of(8,0);

        Scheduler scheduler = new Scheduler(23,0,8,0);
        Scheduler scheduler2 = new Scheduler(ltStart,ltEnd);
        //****NOTE**** The results of these assertions depend on the time the test is run.
        //assertFalse(scheduler.shouldBeRunning());
        //assertFalse(scheduler2.shouldBeRunning());
    }
}