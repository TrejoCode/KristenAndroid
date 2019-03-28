package mx.edu.upqroo.kristenandroid.services.sie.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.database.entities.Grades;

public class GradesListMessage {
    private boolean successful;
    private List<Grades> gradesList;

    public GradesListMessage(boolean successful, List<Grades> gradesList) {
        this.successful = successful;
        this.gradesList = gradesList;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<Grades> getGradesList() {
        return gradesList;
    }

    public void setGradesList(List<Grades> gradesList) {
        this.gradesList = gradesList;
    }
}
