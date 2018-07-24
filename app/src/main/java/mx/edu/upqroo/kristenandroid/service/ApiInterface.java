package mx.edu.upqroo.kristenandroid.service;

import java.util.List;

import mx.edu.upqroo.kristenandroid.service.containers.Alumno;
import mx.edu.upqroo.kristenandroid.service.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("WebSite/Publicacion/FeedApp/{career}/{page}")
    Call<List<Publicacion>> listPublications(@Path("career") int career, @Path("page") int page);

    @POST("appMovil/alumnos/Login")
    @FormUrlEncoded
    Call<Alumno> login(@Field("matricula") String user, @Field("contrasena") String password);

    @GET("appMovil/Calificaciones/{studentId}")
    Call<List<Calificacion>> listGardes(@Path("studentId") String studentId);
}
