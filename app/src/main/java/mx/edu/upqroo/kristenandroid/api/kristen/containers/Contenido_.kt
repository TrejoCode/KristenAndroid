package mx.edu.upqroo.kristenandroid.api.kristen.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contenido_ {

    @SerializedName("texto")
    @Expose
    var texto: String? = null
    @SerializedName("src")
    @Expose
    var src: String? = null
    @SerializedName("alt")
    @Expose
    var alt: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("servidor")
    @Expose
    var servidor: String? = null
    @SerializedName("titulo")
    @Expose
    var titulo: String? = null
    @SerializedName("ordenada")
    @Expose
    var ordenada: Boolean? = null
    @SerializedName("cantidad")
    @Expose
    var cantidad: Int? = null
    @SerializedName("elementos")
    @Expose
    var elementos: List<String>? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("imagenes")
    @Expose
    var imagenes: List<String>? = null
}
