package mx.edu.upqroo.kristenandroid.services.kristen.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacto {
    @SerializedName("idContacto")
    @Expose
    private String idContacto;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("correo")
    @Expose
    private String correo;

    public Contacto() {
    }

    public Contacto(String idContacto, String nombre, String correo) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
