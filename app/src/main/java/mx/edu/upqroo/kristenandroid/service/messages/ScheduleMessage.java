package mx.edu.upqroo.kristenandroid.service.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.models.Day;

public class ScheduleMessage {
    private List<Day> days;

    public ScheduleMessage(List<Day> days) {
        this.days = days;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
