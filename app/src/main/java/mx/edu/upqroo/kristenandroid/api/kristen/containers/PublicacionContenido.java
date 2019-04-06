package mx.edu.upqroo.kristenandroid.api.kristen.containers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicacionContenido {

    @SerializedName("idPublicaciones")
    @Expose
    private String idPublicaciones;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("portada")
    @Expose
    private String portada;
    @SerializedName("categorias")
    @Expose
    private String categorias;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("idTipos_Publicacion")
    @Expose
    private Integer idTiposPublicacion;
    @SerializedName("idUsuarios")
    @Expose
    private String idUsuarios;
    @SerializedName("idCarrera")
    @Expose
    private Integer idCarrera;
    @SerializedName("autor")
    @Expose
    private String autor;
    @SerializedName("contenidos")
    @Expose
    private List<Contenido> contenidos = null;

    public String getIdPublicaciones() {
        return idPublicaciones;
    }

    public void setIdPublicaciones(String idPublicaciones) {
        this.idPublicaciones = idPublicaciones;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdTiposPublicacion() {
        return idTiposPublicacion;
    }

    public void setIdTiposPublicacion(Integer idTiposPublicacion) {
        this.idTiposPublicacion = idTiposPublicacion;
    }

    public String getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(String idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }


}