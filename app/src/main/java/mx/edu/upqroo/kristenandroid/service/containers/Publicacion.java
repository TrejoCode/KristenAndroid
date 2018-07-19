package mx.edu.upqroo.kristenandroid.service.containers;

import java.util.Date;

public class Publicacion {
    private int idPublicaciones;
    private String descripcion;
    private String titulo;
    private String categorias;
    private String portada;
    private Date fecha;
    private int idTipos_Publicaciones;

    public int getIdPublicaciones() {
        return idPublicaciones;
    }

    public void setIdPublicaciones(int idPublicaciones) {
        this.idPublicaciones = idPublicaciones;
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

    public int getIdTipos_Publicaciones() {
        return idTipos_Publicaciones;
    }

    public void setIdTipos_Publicaciones(int idTipos_Publicaciones) {
        this.idTipos_Publicaciones = idTipos_Publicaciones;
    }
}
