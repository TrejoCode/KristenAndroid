package mx.edu.upqroo.kristenandroid.service;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.models.Grades;
import mx.edu.upqroo.kristenandroid.models.Kardex;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.service.containers.Alumno;
import mx.edu.upqroo.kristenandroid.service.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.service.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.service.containers.Publicacion;
import mx.edu.upqroo.kristenandroid.service.containers.PublicacionContenido;
import mx.edu.upqroo.kristenandroid.service.messages.GradesListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.KardexListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.LoginMessage;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessageError;
import mx.edu.upqroo.kristenandroid.service.messages.PostContentMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServices {
    private static ApiInterface service;

    private static void initializeRestClientAdministration() {
        if (service == null)
        service = ApiClient.createService(ApiInterface.class);
    }

    public static void getPublicationsList(int career, int page) {
        initializeRestClientAdministration();
        Call<List<Publicacion>> repos = service.listPublications(career + 1, page);
        repos.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, Response<List<Publicacion>> response) {
                switch (response.code()) {
                    case 200:
                        List<Publicacion> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new NewsListMessage(convertPublicationListToNewsList(data)));
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

    private static News convertPublicationToNews(Publicacion publicacion) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.US);
        return new News(publicacion.getIdPublicaciones(), publicacion.getTitulo(), publicacion.getDescripcion(),
                publicacion.getCategorias(), publicacion.getPortada(), formatter.format(publicacion.getFecha()));
    }

    private static List<News> convertPublicationListToNewsList(List<Publicacion> publicacionList) {
        List<News> newsList = new ArrayList<>();
        for (Publicacion p : publicacionList) {
            newsList.add(convertPublicationToNews(p));
        }
        return newsList;
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
                                    .post(new LoginMessage(true, convertAlumnoToGeneralInfo(data)));
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

    private static GeneralInfo convertAlumnoToGeneralInfo(Alumno alumno) {
        return new GeneralInfo(alumno.getNombre(),
                alumno.getCarrera(),
                alumno.getCarrera(),
                alumno.getCreditos(),
                alumno.getSituacion(),
                alumno.getPdoIni(),
                alumno.getCurp(),
                alumno.getNacimiento(),
                alumno.getDireccion(),
                alumno.getTelDomicilio(),
                alumno.getTelMovil(),
                alumno.getCorreo(),
                "GENERAL",
                getUserTopic(alumno.getCarrera()),
                alumno.getMatricula(),
                alumno.getContrasena()
                );
    }

    private static String getUserTopic(String career) {
        int i = Integer.parseInt(career);
        String topic;
        switch(i){
            case 1:
                topic = "GENERAL";
                break;
            case 2:
                topic = "INGENIERIAENSOFTWARE";
                break;
            case 3:
                topic = "INGENIERIAFINANCIERA";
                break;
            case 4:
                topic = "INGENIERIAENBIOMEDICA";
                break;
            case 5:
                topic = "LICENCIATURAADMINISTRACIONYGESTIONDEPYMES";
                break;
            case 7:
                topic = "LICENCIATURAENTERAPIAFISICA";
                break;
            default:
                topic = "GENERAL";
        }
        return topic;
    }

    public static void getGradesList(String studentId) {
        initializeRestClientAdministration();
        Call<List<Calificacion>> call = service.listGardes(studentId);
        call.enqueue(new Callback<List<Calificacion>>() {
            @Override
            public void onResponse(Call<List<Calificacion>> call, Response<List<Calificacion>> response) {
                switch (response.code()) {
                    case 200:
                        List<Calificacion> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new GradesListMessage(convertCalificacionListToGradeList(data)));
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

    private static Grades convertCalificacionToGrade(Calificacion calificacion) {
        return new Grades(calificacion.getGrupo(), calificacion.getNombreMat(),
                calificacion.getCalificacion(), calificacion.getParcial1(),
                calificacion.getParcial2(), calificacion.getParcial3(),
                calificacion.getParcial4(), calificacion.getParcial5());
    }

    private static List<Grades> convertCalificacionListToGradeList(List<Calificacion> calificacionList) {
        List<Grades> gradesList = new ArrayList<>();
        for (Calificacion c : calificacionList) {
            gradesList.add(convertCalificacionToGrade(c));
        }
        return gradesList;
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
                                    .post(new KardexListMessage(convertKardexsListToKardexList(data)));
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

    private static Kardex convertKardexsToKardex(Kardexs kardexs) {
        return new Kardex(kardexs.getNombreMat(), kardexs.getCalificacion(), kardexs.getCuatrimestre());
    }

    private static List<Kardex> convertKardexsListToKardexList(List<Kardexs> kardexsList) {
        List<Kardex> kardexList = new ArrayList<>();
        for (Kardexs c : kardexsList) {
            kardexList.add(convertKardexsToKardex(c));
        }
        return kardexList;
    }

    public static void getPostContent(int postId) {
        initializeRestClientAdministration();
        Call<PublicacionContenido> call = service.listContents(postId);
        call.enqueue(new Callback<PublicacionContenido>() {
            @Override
            public void onResponse(Call<PublicacionContenido> call, Response<PublicacionContenido> response) {
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
}
