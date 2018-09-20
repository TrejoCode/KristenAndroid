package mx.edu.upqroo.kristenandroid.service.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contenido_ {

    @SerializedName("texto")
    @Expose
    private String texto;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("alt")
    @Expose
    private String alt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("servidor")
    @Expose
    private String servidor;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("ordenada")
    @Expose
    private Boolean ordenada;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("elementos")
    @Expose
    private List<String> elementos = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("imagenes")
    @Expose
    private List<String> imagenes = null;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getOrdenada() {
        return ordenada;
    }

    public void setOrdenada(Boolean ordenada) {
        this.ordenada = ordenada;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getElementos() {
        return elementos;
    }

    public void setElementos(List<String> elementos) {
        this.elementos = elementos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }
}
