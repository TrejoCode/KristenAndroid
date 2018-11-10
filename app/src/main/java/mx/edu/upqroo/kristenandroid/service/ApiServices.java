package mx.edu.upqroo.kristenandroid.service;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import mx.edu.upqroo.kristenandroid.service.containers.Alumno;
import mx.edu.upqroo.kristenandroid.service.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.service.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.service.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.service.containers.Semana;
import mx.edu.upqroo.kristenandroid.service.messages.GradesListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.KardexListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.LoginMessage;
import mx.edu.upqroo.kristenandroid.service.messages.NewsDetailMessage;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessageError;
import mx.edu.upqroo.kristenandroid.service.messages.PostContentMessage;
import mx.edu.upqroo.kristenandroid.service.messages.ScheduleMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <h1>ApiServices</h1>
 * Class that encapsulates the call to the API defined in the Interface.
 * Every method response is notify by a EventBus post.
 */
public class ApiServices {
    private static ApiInterface service;
    private static final String authorizationToken = "Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZG" +
            "Ugb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=";

    /**
     * Initialize the rest client if needed.
     */
    private static void initializeRestClientAdministration() {
        if (service == null)
        service = ApiClient.createService(ApiInterface.class, authorizationToken);
    }

    /**
     * Login the user by calling the API.
     * When the login is finished, weather is successful or not, it posts a message using EventBus
     * to notify the caller the finish of the task, so the caller must be registered to the event,
     * otherwise it will not be able to listen to the response.
     * @param user User's identifier
     * @param password User's password
     */
    public static void login(String user, String password) {
        initializeRestClientAdministration();
        Call<Alumno> call = service.login(user, password);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                switch (response.code()) {
                    case 200:
                        Alumno data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new LoginMessage(true,
                                            Converter.AlumnoToGeneralInfo(data)));
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new LoginMessage(false, response.message()));
                        break;
                }
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                EventBus.getDefault()
                        .post(new LoginMessage(false, t.getMessage()));
            }
        });
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
                                    .post(new NewsListMessage(Converter
                                            .PublicationListToNewsList(data)));
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
     * Gets the actual grade´s list of a user by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param studentId User's identifier
     */
    public static void getGradesList(String studentId) {
        initializeRestClientAdministration();
        Call<List<Calificacion>> call = service.listGardes(studentId);
        call.enqueue(new Callback<List<Calificacion>>() {
            @Override
            public void onResponse(Call<List<Calificacion>> call,
                                   Response<List<Calificacion>> response) {
                switch (response.code()) {
                    case 200:
                        List<Calificacion> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new GradesListMessage(true, Converter
                                            .CalificacionListToGradeList(data)));
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new GradesListMessage(false, null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Calificacion>> call, Throwable t) {
                EventBus.getDefault()
                        .post(new GradesListMessage(false, null));
            }
        });
    }

    /**
     * Gets the user's kardex by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param studentId
     */
    public static void getKardexList(String studentId) {
        initializeRestClientAdministration();
        Call<List<Kardexs>> call = service.listKardex(studentId);
        call.enqueue(new Callback<List<Kardexs>>() {
            @Override
            public void onResponse(Call<List<Kardexs>> call, Response<List<Kardexs>> response) {
                switch (response.code()) {
                    case 200:
                        List<Kardexs> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new KardexListMessage(true,
                                            Converter.KardexListToKardexList(data)));
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new KardexListMessage(false, null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Kardexs>> call, Throwable t) {
                EventBus.getDefault()
                        .post(new KardexListMessage(false, null));
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
                                    .post(new NewsDetailMessage(true, Converter
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

    /**
     * Get's the user's weekly schedule by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param studentId User's identifier
     */
    public static void getSchedule(String studentId) {
        initializeRestClientAdministration();
        Call<Semana> call = service.schedule(studentId);
        call.enqueue(new Callback<Semana>() {
            @Override
            public void onResponse(Call<Semana> call, Response<Semana> response) {
                switch (response.code()) {
                    case 200:
                        Semana data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new ScheduleMessage(true,
                                            Converter.SemanaToSchedule(data)));
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new ScheduleMessage(false,null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<Semana> call, Throwable t) {
                EventBus.getDefault()
                        .post(new ScheduleMessage(false,null));
            }
        });
    }
}
