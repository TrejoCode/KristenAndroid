package mx.edu.upqroo.kristenandroid.api.kristen.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contenido {
    @SerializedName("idTipoContenidos")
    @Expose
    var idTipoContenidos: Int? = null
    @SerializedName("contenido")
    @Expose
    var contenido: Contenido_? = null

}