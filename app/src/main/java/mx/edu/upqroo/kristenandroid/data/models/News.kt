package mx.edu.upqroo.kristenandroid.data.models

import com.google.gson.annotations.SerializedName

import java.util.Date

class News {
    @SerializedName("idPublicaciones")
    var id: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("idTipos_Publicacion")
    var postType: Int = 0
    @SerializedName("titulo")
    var title: String? = null
    @SerializedName("descripcion")
    var description: String? = null
    @SerializedName("categorias")
    var category: String? = null
    @SerializedName("portada")
    var coverUrl: String? = null
    @SerializedName("fecha")
    var date: Date? = null

    constructor(id: String, url: String, postType: Int, title: String, description: String, category: String,
                coverUrl: String, date: Date) {
        this.id = id
        this.url = url
        this.postType = postType
        this.title = title
        this.description = description
        this.category = category
        this.coverUrl = coverUrl
        this.date = date
    }
}
