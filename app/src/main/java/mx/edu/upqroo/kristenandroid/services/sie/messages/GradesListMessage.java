package mx.edu.upqroo.kristenandroid.services.sie.messages;

import java.util.List;

import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;

public class GradesListMessage {
    private boolean successful;
    private List<Grade> gradeList;

    public GradesListMessage(boolean successful, List<Grade> gradeList) {
        this.successful = successful;
        this.gradeList = gradeList;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }
}
