package mx.edu.upqroo.kristenandroid.database.entities;

/**
 * Created by RafaelKoh on 07/07/2018.
 */

public class Grades {
    private String code;
    private String subject;
    private String generalGrade;
    private String gradeOne;
    private String gradeTwo;
    private String gradeThree;
    private String gradeFour;
    private String gradeFive;

    public Grades(String code, String subject, String generalGrade, String gradeOne, String gradeTwo, String gradeThree, String gradeFour, String gradeFive) {
        this.code = code;
        this.subject = subject;
        this.generalGrade = generalGrade;
        this.gradeOne = gradeOne;
        this.gradeTwo = gradeTwo;
        this.gradeThree = gradeThree;
        this.gradeFour = gradeFour;
        this.gradeFive = gradeFive;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGeneralGrade() {
        return generalGrade;
    }

    public void setGeneralGrade(String generalGrade) {
        this.generalGrade = generalGrade;
    }

    public String getGradeOne() {
        return gradeOne;
    }

    public void setGradeOne(String gradeOne) {
        this.gradeOne = gradeOne;
    }

    public String getGradeTwo() {
        return gradeTwo;
    }

    public void setGradeTwo(String gradeTwo) {
        this.gradeTwo = gradeTwo;
    }

    public String getGradeThree() {
        return gradeThree;
    }

    public void setGradeThree(String gradeThree) {
        this.gradeThree = gradeThree;
    }

    public String getGradeFour() {
        return gradeFour;
    }

    public void setGradeFour(String gradeFour) {
        this.gradeFour = gradeFour;
    }

    public String getGradeFive() {
        return gradeFive;
    }

    public void setGradeFive(String gradeFive) {
        this.gradeFive = gradeFive;
    }
}
