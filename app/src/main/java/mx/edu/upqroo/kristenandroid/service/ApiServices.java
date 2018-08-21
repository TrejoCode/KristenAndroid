package mx.edu.upqroo.kristenandroid.service;

import android.util.Log;

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
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessageError;
import mx.edu.upqroo.kristenandroid.service.messages.PostContentMessage;
import mx.edu.upqroo.kristenandroid.service.messages.ScheduleMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServices {
    private static ApiInterface service;

    private static void initializeRestClientAdministration() {
        if (service == null)
        service = ApiClient.createService(ApiInterface.class);
    }

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
                                .post(new LoginMessage(false, null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {

            }
        });
    }

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
                    case 400:
                        EventBus.getDefault().post(new NewsListMessageError(response.message()));
                        break;
                    case 404:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

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
                                    .post(new GradesListMessage(Converter
                                            .CalificacionListToGradeList(data)));
                        }
                        break;
                    case 400:
                        break;
                    case 404:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Calificacion>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

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
                                    .post(new KardexListMessage(
                                            Converter.KardexListToKardexList(data)));
                        }
                        break;
                    case 400:
                        break;
                    case 404:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Kardexs>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

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
                                    .post(new PostContentMessage(data));
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<PublicacionContenido> call, Throwable t) {

            }
        });
    }

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
                                    .post(new ScheduleMessage(Converter.SemanaToSchedule(data)));
                        }
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new LoginMessage(false, null));
                        break;
                }
            }

            @Override
            public void onFailure(Call<Semana> call, Throwable t) {

            }
        });
    }
}
