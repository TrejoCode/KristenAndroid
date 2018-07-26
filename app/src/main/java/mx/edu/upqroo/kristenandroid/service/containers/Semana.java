package mx.edu.upqroo.kristenandroid.service.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Semana {
    @SerializedName("Lunes")
    @Expose
    private List<Materia> lunes;
    @SerializedName("Martes")
    @Expose
    private List<Materia> martes;
    @SerializedName("Miercoles")
    @Expose
    private List<Materia> miercoles;
    @SerializedName("Jueves")
    @Expose
    private List<Materia> jueves;
    @SerializedName("Viernes")
    @Expose
    private List<Materia> viernes;

    public List<Materia> getLunes() {
        return lunes;
    }

    public void setLunes(List<Materia> lunes) {
        this.lunes = lunes;
    }

    public List<Materia> getMartes() {
        return martes;
    }

    public void setMartes(List<Materia> martes) {
        this.martes = martes;
    }

    public List<Materia> getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(List<Materia> miercoles) {
        this.miercoles = miercoles;
    }

    public List<Materia> getJueves() {
        return jueves;
    }

    public void setJueves(List<Materia> jueves) {
        this.jueves = jueves;
    }

    public List<Materia> getViernes() {
        return viernes;
    }

    public void setViernes(List<Materia> viernes) {
        this.viernes = viernes;
    }
}
