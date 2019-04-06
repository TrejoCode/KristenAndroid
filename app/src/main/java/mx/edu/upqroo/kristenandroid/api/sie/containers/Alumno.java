package mx.edu.upqroo.kristenandroid.api.sie.containers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alumno {

    @SerializedName("matricula")
    @Expose
    private String matricula;
    @SerializedName("contrasena")
    @Expose
    private String contrasena;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("carrera")
    @Expose
    private String carrera;
    @SerializedName("nombre_carrera")
    @Expose
    private String nombreCarrera;
    @SerializedName("creditos")
    @Expose
    private String creditos;
    @SerializedName("situacion")
    @Expose
    private String situacion;
    @SerializedName("pdo_cve")
    @Expose
    private String pdoCve;
    @SerializedName("pdo_ini")
    @Expose
    private String pdoIni;
    @SerializedName("pdo_ter")
    @Expose
    private String pdoTer;
    @SerializedName("curp")
    @Expose
    private String curp;
    @SerializedName("nacimiento")
    @Expose
    private String nacimiento;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("tel_domicilio")
    @Expose
    private String telDomicilio;
    @SerializedName("tel_movil")
    @Expose
    private String telMovil;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("config")
    @Expose
    private Config config;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getPdoCve() {
        return pdoCve;
    }

    public void setPdoCve(String pdoCve) {
        this.pdoCve = pdoCve;
    }

    public String getPdoIni() {
        return pdoIni;
    }

    public void setPdoIni(String pdoIni) {
        this.pdoIni = pdoIni;
    }

    public String getPdoTer() {
        return pdoTer;
    }

    public void setPdoTer(String pdoTer) {
        this.pdoTer = pdoTer;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelDomicilio() {
        return telDomicilio;
    }

    public void setTelDomicilio(String telDomicilio) {
        this.telDomicilio = telDomicilio;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
