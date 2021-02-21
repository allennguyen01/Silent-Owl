package SilentOwl;
import java.time.LocalTime;



class SchedulerTest {
    public static void main(String[] args) {

        LocalTime ltStart = LocalTime.of(23,0);
        LocalTime ltEnd= LocalTime.of(8,0);

        Scheduler scheduler = new Scheduler(ltStart, ltEnd);
    }
}