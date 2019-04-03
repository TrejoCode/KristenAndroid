package mx.edu.upqroo.kristenandroid.services.sie;

import java.util.List;

import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Alumno;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Semana;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Api services that feed the personal information of an user to the app.
 */
public interface SieApiInterface {
    /**
     * Method to login and validate a user.
     * @param user User identifier
     * @param password User's password
     * @return the user's information
     */
    @POST("Alumnos/Login")
    @FormUrlEncoded
    Call<Alumno> login(@Field("matricula") String user, @Field("contrasena") String password);

    /**
     * Method to get the list of grades of a user.
     * @param studentId user's identifier
     * @return a list with all the grades
     */
    @GET("Calificaciones/{studentId}")
    Call<List<Calificacion>> listGrades(@Path("studentId") String studentId,
                                        @Query("access_token") String token);

    /**
     * Method to get all the historic grades of a user.
     * @param studentId user's identifier
     * @return a list with all the historic grades
     */
    @GET("Kardexes/{studentId}")
    Call<List<Kardex>> getKardex(@Path("studentId") String studentId,
                                 @Query("access_token") String token);

    /**
     * Method to get the getSchedule of a user.
     * @param studentId user's identifier
     * @return returns all the week getSchedule
     */
    @GET("Horario/{studentId}")
    Call<Semana> getSchedule(@Path("studentId") String studentId,
                             @Query("access_token") String token);
}
