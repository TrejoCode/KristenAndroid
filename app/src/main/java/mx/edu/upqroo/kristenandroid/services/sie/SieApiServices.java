package mx.edu.upqroo.kristenandroid.services.sie;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import mx.edu.upqroo.kristenandroid.services.sie.containers.Alumno;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Kardexs;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Semana;
import mx.edu.upqroo.kristenandroid.services.sie.messages.GradesListMessage;
import mx.edu.upqroo.kristenandroid.services.sie.messages.KardexListMessage;
import mx.edu.upqroo.kristenandroid.services.sie.messages.LoginMessage;
import mx.edu.upqroo.kristenandroid.services.sie.messages.ScheduleMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SieApiServices {
    private static SieApiInterface service;
    private static final String authorizationToken = "Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=";

    /**
     * Initialize the rest client if needed.
     */
    private static void initializeRestClientAdministration() {
        if (service == null)
            service = SieApiClient.createService(SieApiInterface.class, authorizationToken);
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
                                            SieApiConverter.AlumnoToGeneralInfo(data)));
                        }
                        break;
                    case 403:
                        //todo: agregar logica para actualizar el token de auth.
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
     * Gets the actual grade´s list of a user by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param studentId User's identifier
     */
    public static void getGradesList(String studentId, String token) {
        initializeRestClientAdministration();
        Call<List<Calificacion>> call = service.listGardes(studentId, token);
        call.enqueue(new Callback<List<Calificacion>>() {
            @Override
            public void onResponse(Call<List<Calificacion>> call,
                                   Response<List<Calificacion>> response) {
                switch (response.code()) {
                    case 200:
                        List<Calificacion> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new GradesListMessage(true, SieApiConverter
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
     * @param studentId s
     */
    public static void getKardexList(String studentId, String token) {
        initializeRestClientAdministration();
        Call<List<Kardexs>> call = service.listKardex(studentId, token);
        call.enqueue(new Callback<List<Kardexs>>() {
            @Override
            public void onResponse(Call<List<Kardexs>> call, Response<List<Kardexs>> response) {
                switch (response.code()) {
                    case 200:
                        List<Kardexs> data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new KardexListMessage(true,
                                            SieApiConverter.KardexListToKardexList(data)));
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
     * Get's the user's weekly schedule by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param studentId User's identifier
     */
    public static void getSchedule(String studentId, String token) {
        initializeRestClientAdministration();
        Call<Semana> call = service.schedule(studentId, token);
        call.enqueue(new Callback<Semana>() {
            @Override
            public void onResponse(Call<Semana> call, Response<Semana> response) {
                switch (response.code()) {
                    case 200:
                        Semana data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new ScheduleMessage(true,
                                            SieApiConverter.SemanaToSchedule(data)));
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
