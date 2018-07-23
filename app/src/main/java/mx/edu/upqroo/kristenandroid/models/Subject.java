package mx.edu.upqroo.kristenandroid.models;

public class Subject {
    private String materia, hora;

    public Subject() {
    }

    public Subject(String materia, String hora) {
        this.materia = materia;
        this.hora = hora;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
