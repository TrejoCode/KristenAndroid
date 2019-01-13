package mx.edu.upqroo.kristenandroid.services.sie;

import java.util.List;

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
    @POST("Alumnos/Login?access_token=Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=")
    @FormUrlEncoded
    Call<Alumno> login(@Field("matricula") String user, @Field("contrasena") String password);

    /**
     * Method to get the list of grades of a user.
     * @param studentId user's identifier
     * @return a list with all the grades
     */
    @GET("Calificaciones/{studentId}?access_token=Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=")
    Call<List<Calificacion>> listGardes(@Path("studentId") String studentId);

    /**
     * Method to get all the historic grades of a user.
     * @param studentId user's identifier
     * @return a list with all the historic grades
     */
    @GET("Kardexes/{studentId}?access_token=Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=")
    Call<List<Kardexs>> listKardex(@Path("studentId") String studentId);

    /**
     * Method to get the schedule of a user.
     * @param studentId user's identifier
     * @return returns all the week schedule
     */
    @GET("Horario/{studentId}?access_token=Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=")
    Call<Semana> schedule(@Path("studentId") String studentId);
}
