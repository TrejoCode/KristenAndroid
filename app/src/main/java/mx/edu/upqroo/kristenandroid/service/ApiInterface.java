package mx.edu.upqroo.kristenandroid.service;

import java.util.List;

import mx.edu.upqroo.kristenandroid.service.containers.Alumno;
import mx.edu.upqroo.kristenandroid.service.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.service.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.service.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.service.containers.Semana;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * <h1>ApiInterface</h1>
 * This interface is used to declare all the api services that are used in the application.
 */
public interface ApiInterface {
    /**
     * This method retrieves a list of publications.
     * @param career career of the user
     * @param page page that is desired
     * @return a list of publications
     */
    @GET("WebSite/Publicacion/FeedApp/{career}/{page}")
    Call<List<Publicacion>> listPublications(@Path("career") int career, @Path("page") int page);

    /**
     * This method retrieves the details of a post.
     * @param idPost unique identifier of the post
     * @return a list with the contents of the post
     */
    @GET("WebSite/Publicacion/{idPost}")
    Call<PublicacionContenido> listContents(@Path("idPost") int idPost);

    /**
     * Method to login and validate a user.
     * @param user User identifier
     * @param password User's password
     * @return the user's information
     */
    @POST("appMovil/alumnos/Login")
    @FormUrlEncoded
    Call<Alumno> login(@Field("matricula") String user, @Field("contrasena") String password);

    /**
     * Method to get the list of grades of a user.
     * @param studentId user's identifier
     * @return a list with all the grades
     */
    @GET("appMovil/Calificaciones/{studentId}")
    Call<List<Calificacion>> listGardes(@Path("studentId") String studentId);

    /**
     * Method to get all the historic grades of a user.
     * @param studentId user's identifier
     * @return a list with all the historic grades
     */
    @GET("appMovil/Kardexes/{studentId}")
    Call<List<Kardexs>> listKardex(@Path("studentId") String studentId);

    /**
     * Method to get the schedule of a user.
     * @param studentId user's identifier
     * @return returns all the week schedule
     */
    @GET("appMovil/Horario/{studentId}")
    Call<Semana> schedule(@Path("studentId") String studentId);
}
