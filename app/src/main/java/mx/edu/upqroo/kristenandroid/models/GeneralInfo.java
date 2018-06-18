package mx.edu.upqroo.kristenandroid.models;

public class GeneralInfo {
    private String name;
    private String lastName;
    private String age;
    private String career;
    private String enrollment;

    public GeneralInfo() {

    }

    public GeneralInfo(String name, String lastName, String age, String career, String enrollment) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.career = career;
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }
}
