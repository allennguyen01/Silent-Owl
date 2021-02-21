package SilentOwl;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Scheduler {

    private LocalTime startTime;
    private LocalTime endTime;
    private ZoneId zone = ZoneId.systemDefault();

    public Scheduler(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Scheduler(int startHour, int startMinute, int endHour, int endMinute) {
        if (startHour < 0 || startHour > 23 ||
            startMinute < 0 || startMinute > 59 ||
            endHour < 0 || endHour > 23 ||
            endMinute < 0 || endMinute > 59) {
            this.startTime = LocalTime.of(0, 0);
            this.endTime = LocalTime.of(0, 0);
            System.out.println("Invalid input time. Defaulted to 00:00 for start and end");
        } else {
            this.startTime = LocalTime.of(startHour, startMinute);
            this.endTime = LocalTime.of(endHour, endMinute);
        }
    }

    /**
     * Sets a new start time
     *
     * @param startTime is the new start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets a new end time
     *
     * @param endTime is the new end time
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets a new start time
     *
     * @param hour to set the start time to
     * @param minute to set the start time to
     */
    public void setStartTime(int hour, int minute) {
        this.startTime = LocalTime.of(hour, minute);
    }

    /**
     * Sets a new end time
     *
     * @param hour to set the end time to
     * @param minute to set the end time to
     */
    public void setEndTime(int hour, int minute) {
        this.endTime = LocalTime.of(hour, minute);
    }

    /**
     *
     * @return true if the monitor should be running. false if it should not.
     */
    public boolean shouldBeRunning() {
        ZonedDateTime currDate = Instant.now().atZone(zone);
        LocalTime currTime = currDate.toLocalTime();

        if (startTime.isAfter(endTime)) {
            return currTime.isAfter(startTime) || currTime.isBefore(endTime);
        } else {
            return currTime.isAfter(startTime) && currTime.isBefore(endTime);
        }
    }
}
