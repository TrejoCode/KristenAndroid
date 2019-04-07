package mx.edu.upqroo.kristenandroid.api.kristen;

import android.app.Application;
import android.os.AsyncTask;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.data.database.entities.Contact;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.repositories.NoticeRepository;
import mx.edu.upqroo.kristenandroid.api.kristen.containers.Contacto;
import mx.edu.upqroo.kristenandroid.api.kristen.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.api.kristen.messages.CalendarUrlMessage;
import mx.edu.upqroo.kristenandroid.api.kristen.messages.ContactListMessage;
import mx.edu.upqroo.kristenandroid.api.kristen.messages.NewsDetailMessage;
import mx.edu.upqroo.kristenandroid.api.kristen.messages.PostContentMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <h1>KristenApiServices</h1>
 * Class that encapsulates the call to the API defined in the Interface.
 * Every method response is notify by a EventBus post.
 */
public class KristenApiServices {
    private static KristenApiServices mInstance;
    private static KristenApiInterface service;

    private KristenApiServices() {
        service = KristenApiClient.createService(KristenApiInterface.class);
    }

    public static KristenApiServices getInstance() {
        if (mInstance == null) {
            mInstance = new KristenApiServices();
        }
        return mInstance;
    }

    /**
     * Gets the content of a post by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param postId Post's identifier
     */
    public void getPostContent(String postId) {
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

    public void getCalendarUrl() {
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

    public void getContacts(Application application) {
        Call<List<Contact>> call = service.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NotNull Call<List<Contact>> call,
                                   @NotNull Response<List<Contact>> response) {
                switch (response.code()) {
                    case 200:
                        List<Contact> data = response.body();
                        if (data != null) {
                            KristenApiConverter.insertContacts(data, application);
                        } else {
                            Crashlytics.log("200 Error data null while getting contacts");
                        }
                        break;
                    default:
                        Crashlytics.log(response.code() + "Error code while getting contacts");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Contact>> call, @NotNull Throwable t) {
                Crashlytics.log(t.getMessage() + " - Error code while getting contacts");
            }
        });
    }

    public KristenApiInterface getService() {
        return service;
    }
}
