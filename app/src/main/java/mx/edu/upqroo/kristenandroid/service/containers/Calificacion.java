package mx.edu.upqroo.kristenandroid.service.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Calificacion {
    @SerializedName("matricula")
    @Expose
    private String matricula;
    @SerializedName("nombre_mat")
    @Expose
    private String nombreMat;
    @SerializedName("grupo")
    @Expose
    private String grupo;
    @SerializedName("calificacion")
    @Expose
    private String calificacion;
    @SerializedName("parcial_1")
    @Expose
    private String parcial1;
    @SerializedName("parcial_2")
    @Expose
    private String parcial2;
    @SerializedName("parcial_3")
    @Expose
    private String parcial3;
    @SerializedName("parcial_4")
    @Expose
    private String parcial4;
    @SerializedName("parcial_5")
    @Expose
    private String parcial5;

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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getParcial1() {
        return parcial1;
    }

    public void setParcial1(String parcial1) {
        this.parcial1 = parcial1;
    }

    public String getParcial2() {
        return parcial2;
    }

    public void setParcial2(String parcial2) {
        this.parcial2 = parcial2;
    }

    public String getParcial3() {
        return parcial3;
    }

    public void setParcial3(String parcial3) {
        this.parcial3 = parcial3;
    }

    public String getParcial4() {
        return parcial4;
    }

    public void setParcial4(String parcial4) {
        this.parcial4 = parcial4;
    }

    public String getParcial5() {
        return parcial5;
    }

    public void setParcial5(String parcial5) {
        this.parcial5 = parcial5;
    }
}
