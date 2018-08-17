package mx.edu.upqroo.kristenandroid.service.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contenido_ {

    @SerializedName("texto")
    @Expose
    private String texto;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("servidor")
    @Expose
    private String servidor;
    @SerializedName("imagenes")
    @Expose
    private List<String> imagenes = null;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }
}
