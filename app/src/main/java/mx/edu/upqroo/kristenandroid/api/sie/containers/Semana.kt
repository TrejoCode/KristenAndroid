package mx.edu.upqroo.kristenandroid.api.sie.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Semana {
    @SerializedName("Lunes")
    @Expose
    var lunes: List<Materia>? = null
    @SerializedName("Martes")
    @Expose
    var martes: List<Materia>? = null
    @SerializedName("Miercoles")
    @Expose
    var miercoles: List<Materia>? = null
    @SerializedName("Jueves")
    @Expose
    var jueves: List<Materia>? = null
    @SerializedName("Viernes")
    @Expose
    var viernes: List<Materia>? = null
}
