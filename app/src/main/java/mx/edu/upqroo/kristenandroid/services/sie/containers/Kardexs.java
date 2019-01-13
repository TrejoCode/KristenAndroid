package mx.edu.upqroo.kristenandroid.services.sie.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kardexs {
    @SerializedName("matricula")
    @Expose
    private String matricula;
    @SerializedName("nombre_mat")
    @Expose
    private String nombreMat;
    @SerializedName("calificacion")
    @Expose
    private String calificacion;
    @SerializedName("periodo")
    @Expose
    private String periodo;
    @SerializedName("cuatrimestre")
    @Expose
    private String cuatrimestre;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreMat() {
        return nombreMat;
    }

    public void setNombreMat(String nombreMat) {
        this.nombreMat = nombreMat;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }
}
