package mx.edu.upqroo.kristenandroid.database.entities;

import java.util.List;

public class Day {
    private String name;
    private List<Subject> subjects;

    public Day() {

    }

    public Day(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
