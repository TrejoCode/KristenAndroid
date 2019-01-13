package mx.edu.upqroo.kristenandroid.services.kristen;

import java.util.List;

import mx.edu.upqroo.kristenandroid.services.kristen.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.PublicacionContenido;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <h1>KristenApiInterface</h1>
 * This interface is used to declare all the api services that are used in the application.
 */
public interface KristenApiInterface {
    /**
     * This method retrieves a list of publications.
     * @param career career of the user
     * @param page page that is desired
     * @return a list of publications
     */
    @GET("WebSite/Publicacion/FeedApp/{career}/{page}?access_token=Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=")
    Call<List<Publicacion>> listPublications(@Path("career") int career, @Path("page") int page);

    /**
     * This method retrieves the details of a post.
     * @param idPost unique identifier of the post
     * @return a list with the contents of the post
     */
    @GET("WebSite/Publicacion/{idPost}?access_token=Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=")
    Call<PublicacionContenido> listContents(@Path("idPost") int idPost);


}
