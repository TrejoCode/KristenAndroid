package mx.edu.upqroo.kristenandroid.api.sie

import mx.edu.upqroo.kristenandroid.data.database.entities.Grade
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex
import mx.edu.upqroo.kristenandroid.api.sie.containers.Alumno
import mx.edu.upqroo.kristenandroid.api.sie.containers.Semana
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Api services that feed the personal information of an user to the app.
 */
interface SieApiInterface {
    /**
     * Method to login and validate a user.
     * @param user User identifier
     * @param password User's password
     * @return the user's information
     */
    @POST("Alumnos/Login")
    @FormUrlEncoded
    fun login(@Field("matricula") user: String, @Field("contrasena") password: String): Call<Alumno>

    /**
     * Method to get the list of grades of a user.
     * @param studentId user's identifier
     * @return a list with all the grades
     */
    @GET("Calificaciones/{studentId}")
    fun getGrades(@Path("studentId") studentId: String,
                  @Query("access_token") token: String): Call<List<Grade>>

    /**
     * Method to get all the historic grades of a user.
     * @param studentId user's identifier
     * @return a list with all the historic grades
     */
    @GET("Kardexes/{studentId}")
    fun getKardex(@Path("studentId") studentId: String,
                  @Query("access_token") token: String): Call<List<Kardex>>

    /**
     * Method to get the getSchedule of a user.
     * @param studentId user's identifier
     * @return returns all the week getSchedule
     */
    @GET("Horario/{studentId}")
    fun getSchedule(@Path("studentId") studentId: String,
                    @Query("access_token") token: String): Call<Semana>
}
