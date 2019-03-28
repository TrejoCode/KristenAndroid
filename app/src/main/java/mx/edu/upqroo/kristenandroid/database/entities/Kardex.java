package mx.edu.upqroo.kristenandroid.database.entities;

public class Kardex {
    private String subject;
    private String grade;
    private String cuarter;

    public Kardex(String subject, String grade, String cuarter) {
        this.subject = subject;
        this.grade = grade;
        this.cuarter = cuarter;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCuarter() {
        return cuarter;
    }

    public void setCuarter(String cuarter) {
        this.cuarter = cuarter;
    }
}
