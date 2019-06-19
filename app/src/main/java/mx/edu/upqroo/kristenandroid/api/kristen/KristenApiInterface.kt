package mx.edu.upqroo.kristenandroid.api.kristen

import mx.edu.upqroo.kristenandroid.data.database.entities.Contact
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice
import mx.edu.upqroo.kristenandroid.data.models.News
import mx.edu.upqroo.kristenandroid.api.kristen.containers.Contacto
import mx.edu.upqroo.kristenandroid.api.kristen.containers.PublicacionContenido
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <h1>KristenApiInterface</h1>
 * This interface is used to declare all the api services that are used in the application.
 */
interface KristenApiInterface {

    /**
     * This method retrieves the calendar url
     * @return the url for the calendar
     */
    @get:GET("InfoInstitucional/findOne?filter=%7B%22where%22%3A%7B%22idTipos_Informacion%22%3A10%7D%2C%22fields%22%3A%7B%22contenidos%22%3Atrue%7D%7D")
    val calendarUrl: Call<PublicacionContenido>

    /**
     * Get all the university contacts
     * @return a list with all the contacts
     */
    @get:GET("Contactos")
    val contacts: Call<List<Contact>>

    /**
     * This method retrieves the details of a post.
     * @param idPost unique identifier of the post
     * @return a list with the contents of the post
     */
    @GET("Publicacion/{idPost}")
    fun listContents(@Path("idPost") idPost: String): Call<PublicacionContenido>

    /**
     * Get all notices
     */
    @GET("Aviso")
    fun getNotices(@Query("filter") filter: String): Call<List<Notice>>

    /**
     * Get all notices
     */
    @GET("Publicacion")
    fun getNews(@Query("filter") filter: String): Call<List<News>>
}
