package mx.edu.upqroo.kristenandroid.service;

import java.util.List;

import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("WebSite/Publicacion/FeedApp/{career}/{page}")
    Call<List<Publicacion>> listPublications(@Path("career") int career, @Path("page") int page);
}
