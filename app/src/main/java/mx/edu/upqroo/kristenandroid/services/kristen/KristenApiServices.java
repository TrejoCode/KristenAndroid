package mx.edu.upqroo.kristenandroid.services.kristen;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import mx.edu.upqroo.kristenandroid.services.kristen.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsDetailMessage;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsListMessage;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsListMessageError;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.PostContentMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <h1>KristenApiServices</h1>
 * Class that encapsulates the call to the API defined in the Interface.
 * Every method response is notify by a EventBus post.
 */
public class KristenApiServices {
    private static KristenApiInterface service;
    private static final String authorizationToken = "Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=";

    /**
     * Initialize the rest client if needed.
     */
    private static void initializeRestClientAdministration() {
        if (service == null)
            service = KristenApiClient.createService(KristenApiInterface.class, authorizationToken);
    }

    /**
     * Gets a publication list page by calling the API.
     * When the method finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param career User's career identifier
     * @param page Page number to be retrieved
     */
    public static void getPublicationsList(int career, int page) {
        initializeRestClientAdministration();
        Call<List<Publicacion>> repos = service.listPublications(career, page);
        repos.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call,
                                   Response<List<Publicacion>> response) {
                switch (response.code()) {
                    case 200:
                        List<Publicacion> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new NewsListMessage(KristenApiConverter
                                            .PublicationListToNewsList(data)));
                        }
                        break;
                    case 404:
                        if (response.errorBody() != null) {
                            try {
                                EventBus.getDefault().post(
                                        new NewsListMessageError(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                        EventBus.getDefault().post(new NewsListMessageError(response.message()));
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                EventBus.getDefault().post(new NewsListMessageError(t.getMessage()));
            }
        });
    }



    /**
     * Gets the content of a post by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param postId Post's identifier
     */
    public static void getPostContent(int postId) {
        initializeRestClientAdministration();
        Call<PublicacionContenido> call = service.listContents(postId);
        call.enqueue(new Callback<PublicacionContenido>() {
            @Override
            public void onResponse(Call<PublicacionContenido> call,
                                   Response<PublicacionContenido> response) {
                switch (response.code()) {
                    case 200:
                        PublicacionContenido data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new NewsDetailMessage(true, KristenApiConverter
                                            .PublicacionToNewsDetail(data)));
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new NewsDetailMessage(false, null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<PublicacionContenido> call, Throwable t) {
                EventBus.getDefault()
                        .post(new PostContentMessage(false,
                                null));
            }
        });
    }

}
