package mx.edu.upqroo.kristenandroid.service;

import java.util.List;

import mx.edu.upqroo.kristenandroid.service.containers.Alumno;
import mx.edu.upqroo.kristenandroid.service.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.service.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.service.containers.PublicacionContenido;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("WebSite/Publicacion/FeedApp/{career}/{page}")
    Call<List<Publicacion>> listPublications(@Path("career") int career, @Path("page") int page);

    @GET("WebSite/Publicacion/{idPost}")
    Call<PublicacionContenido> listContents(@Path("idPost") int idPost);

    @POST("appMovil/alumnos/Login")
    @FormUrlEncoded
    Call<Alumno> login(@Field("matricula") String user, @Field("contrasena") String password);

    @GET("appMovil/Calificaciones/{studentId}")
    Call<List<Calificacion>> listGardes(@Path("studentId") String studentId);

    @GET("appMovil/Kardexes/{studentId}")
    Call<List<Kardexs>> listKardex(@Path("studentId") String studentId);
}
