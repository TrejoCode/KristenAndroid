package mx.edu.upqroo.kristenandroid.api.sie.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Materia {
    @SerializedName("acronimo")
    @Expose
    var acronimo: String? = null
    @SerializedName("nombre")
    @Expose
    var nombre: String? = null
    @SerializedName("profesor")
    @Expose
    var profesor: String? = null
    @SerializedName("hora")
    @Expose
    var hora: String? = null
}
