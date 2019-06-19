package mx.edu.upqroo.kristenandroid.api.kristen.containers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PublicacionContenido {

    @SerializedName("idPublicaciones")
    @Expose
    var idPublicaciones: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("titulo")
    @Expose
    var titulo: String? = null
    @SerializedName("descripcion")
    @Expose
    var descripcion: String? = null
    @SerializedName("portada")
    @Expose
    var portada: String? = null
    @SerializedName("categorias")
    @Expose
    var categorias: String? = null
    @SerializedName("fecha")
    @Expose
    var fecha: String? = null
    @SerializedName("idTipos_Publicacion")
    @Expose
    var idTiposPublicacion: Int? = null
    @SerializedName("idUsuarios")
    @Expose
    var idUsuarios: String? = null
    @SerializedName("idCarrera")
    @Expose
    var idCarrera: Int? = null
    @SerializedName("autor")
    @Expose
    var autor: String? = null
    @SerializedName("contenidos")
    @Expose
    var contenidos: List<Contenido>? = null


}