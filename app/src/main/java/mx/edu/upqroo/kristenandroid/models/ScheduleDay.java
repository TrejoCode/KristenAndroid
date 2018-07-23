package mx.edu.upqroo.kristenandroid.models;

public class ScheduleDay {
    private String name;
    private String time;

    public ScheduleDay(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
