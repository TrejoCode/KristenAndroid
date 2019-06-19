package mx.edu.upqroo.kristenandroid.api.sie.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Alumno {

    @SerializedName("matricula")
    @Expose
    var matricula: String? = null
    @SerializedName("contrasena")
    @Expose
    var contrasena: String? = null
    @SerializedName("nombre")
    @Expose
    var nombre: String? = null
    @SerializedName("carrera")
    @Expose
    var carrera: String? = null
    @SerializedName("nombre_carrera")
    @Expose
    var nombreCarrera: String? = null
    @SerializedName("creditos")
    @Expose
    var creditos: String? = null
    @SerializedName("situacion")
    @Expose
    var situacion: String? = null
    @SerializedName("pdo_cve")
    @Expose
    var pdoCve: String? = null
    @SerializedName("pdo_ini")
    @Expose
    var pdoIni: String? = null
    @SerializedName("pdo_ter")
    @Expose
    var pdoTer: String? = null
    @SerializedName("curp")
    @Expose
    var curp: String? = null
    @SerializedName("nacimiento")
    @Expose
    var nacimiento: String? = null
    @SerializedName("direccion")
    @Expose
    var direccion: String? = null
    @SerializedName("tel_domicilio")
    @Expose
    var telDomicilio: String? = null
    @SerializedName("tel_movil")
    @Expose
    var telMovil: String? = null
    @SerializedName("correo")
    @Expose
    var correo: String? = null
    @SerializedName("config")
    @Expose
    var config: Config? = null
}
