package mx.edu.upqroo.kristenandroid.services.kristen.containers;

import java.util.Date;

public class Publicacion {
    private int idPublicaciones;
    private String url;
    private String descripcion;
    private String titulo;
    private String categorias;
    private String portada;
    private Date fecha;
    private int idTipos_Publicacion;

    public int getIdPublicaciones() {
        return idPublicaciones;
    }

    public void setIdPublicaciones(int idPublicaciones) {
        this.idPublicaciones = idPublicaciones;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdTipos_Publicacion() {
        return idTipos_Publicacion;
    }

    public void setIdTipos_Publicacion(int idTipos_Publicaciones) {
        this.idTipos_Publicacion = idTipos_Publicaciones;
    }
}
