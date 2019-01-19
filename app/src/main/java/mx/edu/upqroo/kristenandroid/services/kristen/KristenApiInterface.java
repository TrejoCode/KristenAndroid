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
    @GET("Publicacion/FeedApp/{career}/{page}")
    Call<List<Publicacion>> listPublications(@Path("career") int career, @Path("page") int page);

    /**
     * This method retrieves the details of a post.
     * @param idPost unique identifier of the post
     * @return a list with the contents of the post
     */
    @GET("Publicacion/{idPost}")
    Call<PublicacionContenido> listContents(@Path("idPost") String idPost);


}
