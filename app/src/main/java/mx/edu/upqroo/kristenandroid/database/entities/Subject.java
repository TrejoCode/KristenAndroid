package mx.edu.upqroo.kristenandroid.database.entities;

public class Subject {
    private String name;
    private String professor;
    private String time;

    public Subject(String name,String professor, String time) {
        this.name = name;
        this.professor = professor;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
