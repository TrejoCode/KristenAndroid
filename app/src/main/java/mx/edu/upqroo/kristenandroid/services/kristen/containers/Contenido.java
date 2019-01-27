package mx.edu.upqroo.kristenandroid.services.kristen.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contenido {
    @SerializedName("idTipoContenidos")
    @Expose
    private Integer idTipoContenidos;
    @SerializedName("contenido")
    @Expose
    private Contenido_ contenido;

    public Integer getIdTipoContenidos() {
        return idTipoContenidos;
    }

    public void setIdTipoContenidos(Integer idTipoContenidos) {
        this.idTipoContenidos = idTipoContenidos;
    }

    public Contenido_ getContenido() {
        return contenido;
    }

    public void setContenido(Contenido_ contenido) {
        this.contenido = contenido;
    }

}