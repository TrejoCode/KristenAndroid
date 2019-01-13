package mx.edu.upqroo.kristenandroid.service.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.models.Day;

public class ScheduleMessage {
    private boolean successful;
    private List<Day> days;

    public ScheduleMessage(boolean successful, List<Day> days) {
        this.successful = successful;
        this.days = days;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
