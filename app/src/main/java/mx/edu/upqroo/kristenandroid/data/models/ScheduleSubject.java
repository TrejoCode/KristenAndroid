package mx.edu.upqroo.kristenandroid.data.models;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;

public class ScheduleSubject {
    @Embedded
    private Day mDay;
    @Relation(parentColumn = "dayId",
            entityColumn = "dayId")
    private List<Subject> mSubjects = new ArrayList<>();

    public Day getDay() {
        return mDay;
    }

    public void setDay(Day mDay) {
        this.mDay = mDay;
    }

    public List<Subject> getSubjects() {
        return mSubjects;
    }

    public void setSubjects(List<Subject> mSubjects) {
        this.mSubjects = mSubjects;
    }
}
