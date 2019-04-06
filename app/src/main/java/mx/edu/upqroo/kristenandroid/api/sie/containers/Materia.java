package mx.edu.upqroo.kristenandroid.api.sie.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Materia {
    @SerializedName("acronimo")
    @Expose
    private String acronimo;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("profesor")
    @Expose
    private String profesor;
    @SerializedName("hora")
    @Expose
    private String hora;

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
