package mx.edu.upqroo.kristenandroid.service.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.models.Grades;

public class GradesListMessage {
    private List<Grades> gradesList;

    public GradesListMessage(List<Grades> gradesList) {
        this.gradesList = gradesList;
    }

    public List<Grades> getGradesList() {
        return gradesList;
    }

    public void setGradesList(List<Grades> gradesList) {
        this.gradesList = gradesList;
    }
}
