package mx.edu.upqroo.kristenandroid.services.kristen;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.services.kristen.containers.Contacto;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.services.kristen.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.CalendarUrlMessage;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.ContactListMessage;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsDetailMessage;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsListMessage;
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

    /**
     * Initialize the rest client if needed.
     */
    private static void initializeRestClientAdministration() {
        if (service == null)
            service = KristenApiClient.createService(KristenApiInterface.class);
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
        final Call<List<Publicacion>> repos = service.listPublications(career, page);
        repos.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(@NotNull Call<List<Publicacion>> call,
                                   @NotNull Response<List<Publicacion>> response) {
                switch (response.code()) {
                    case 200:
                        List<Publicacion> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new NewsListMessage(true, KristenApiConverter
                                            .PublicationListToNewsList(data)));
                        } else {
                            EventBus.getDefault().post(
                                    new NewsListMessage(false,
                                            KristenApiConverter.PublicationListToNewsList(
                                                    new ArrayList<Publicacion>())));
                            Crashlytics.log("200 Error data null while getting posts");
                        }
                        break;
                    case 404:
                        EventBus.getDefault().post(
                                new NewsListMessage(true,
                                        KristenApiConverter.PublicationListToNewsList(
                                                new ArrayList<Publicacion>())));
                        break;
                    default:
                        EventBus.getDefault().post(
                                new NewsListMessage(false,
                                        KristenApiConverter.PublicationListToNewsList(
                                                new ArrayList<Publicacion>())));
                        Crashlytics.log(response.code() + "Error code while getting posts");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Publicacion>> call, @NotNull Throwable t) {
                EventBus.getDefault().post(
                        new NewsListMessage(false,
                                KristenApiConverter.PublicationListToNewsList(
                                        new ArrayList<Publicacion>())));
                Crashlytics.log(t.getMessage() + " - Error code while getting posts");
            }
        });
    }



    /**
     * Gets the content of a post by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param postId Post's identifier
     */
    public static void getPostContent(String postId) {
        initializeRestClientAdministration();
        Call<PublicacionContenido> call = service.listContents(postId);
        call.enqueue(new Callback<PublicacionContenido>() {
            @Override
            public void onResponse(@NotNull Call<PublicacionContenido> call,
                                   @NotNull Response<PublicacionContenido> response) {
                switch (response.code()) {
                    case 200:
                        PublicacionContenido data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new NewsDetailMessage(true, KristenApiConverter
                                            .PublicacionToNewsDetail(data)));
                        } else {
                            EventBus.getDefault()
                                    .post(new NewsDetailMessage(false, null));
                            Crashlytics.log("200 Error data null while getting post content");
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new NewsDetailMessage(false, null));
                        Crashlytics.log(response.code() + "Error code while getting content");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<PublicacionContenido> call, @NotNull Throwable t) {
                EventBus.getDefault()
                        .post(new PostContentMessage(false,
                                null));
                Crashlytics.log(t.getMessage() + " - Error code while getting post content");
            }
        });
    }

    public static void getCalendarUrl() {
        initializeRestClientAdministration();
        Call<PublicacionContenido> call = service.getCalendarUrl();
        call.enqueue(new Callback<PublicacionContenido>() {
            @Override
            public void onResponse(@NotNull Call<PublicacionContenido> call,
                                   @NotNull Response<PublicacionContenido> response) {
                switch (response.code()) {
                    case 200:
                        PublicacionContenido data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new CalendarUrlMessage(
                                            data.getContenidos().get(0).getContenido().getTexto(),
                                            data.getContenidos().get(0).getContenido().getUrl()));
                        } else {
                            EventBus.getDefault()
                                    .post(new CalendarUrlMessage("", ""));
                            Crashlytics.log("200 Error data null while getting the calendar");
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new CalendarUrlMessage("", ""));
                        Crashlytics.log(response.code() + "Error code while getting calendar");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<PublicacionContenido> call, @NotNull Throwable t) {
                EventBus.getDefault()
                        .post(new CalendarUrlMessage("", ""));
                Crashlytics.log(t.getMessage() + " - Error code while getting the calendar");
            }
        });
    }

    public static void getContacts() {
        initializeRestClientAdministration();
        Call<List<Contacto>> call = service.getContacts();
        call.enqueue(new Callback<List<Contacto>>() {
            @Override
            public void onResponse(@NotNull Call<List<Contacto>> call,
                                   @NotNull Response<List<Contacto>> response) {
                switch (response.code()) {
                    case 200:
                        List<Contacto> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new ContactListMessage(true, data));
                        } else {
                            EventBus.getDefault()
                                    .post(new ContactListMessage(false,
                                            new ArrayList<Contacto>()));
                            Crashlytics.log("200 Error data null while getting contacts");
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new ContactListMessage(false,
                                        new ArrayList<Contacto>()));
                        Crashlytics.log(response.code() + "Error code while getting contacts");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Contacto>> call, @NotNull Throwable t) {
                EventBus.getDefault()
                        .post(new ContactListMessage(false, new ArrayList<Contacto>()));
                Crashlytics.log(t.getMessage() + " - Error code while getting contacts");
            }
        });
    }

}
