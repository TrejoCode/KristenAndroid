package mx.edu.upqroo.kristenandroid.api.kristen.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contacto {
    @SerializedName("idContacto")
    @Expose
    var idContacto: String? = null

    @SerializedName("nombre")
    @Expose
    var nombre: String? = null

    @SerializedName("correo")
    @Expose
    var correo: String? = null
}
