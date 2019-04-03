package mx.edu.upqroo.kristenandroid.services.sie;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Alumno;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Calificacion;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Semana;
import mx.edu.upqroo.kristenandroid.services.sie.messages.GradesListMessage;
import mx.edu.upqroo.kristenandroid.services.sie.messages.KardexListMessage;
import mx.edu.upqroo.kristenandroid.services.sie.messages.LoginMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SieApiServices {
    private static SieApiServices mInstance;
    private static SieApiInterface service;
    private Application mApp;
    private static final String authorizationToken = "Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8=";

    private SieApiServices(Application application) {
        mApp = application;
        service = SieApiClient.createService(SieApiInterface.class, authorizationToken);
    }

    public static SieApiServices getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new SieApiServices(application);
        }
        return mInstance;
    }

    /**
     * Login the user by calling the API.
     * When the login is finished, weather is successful or not, it posts a message using EventBus
     * to notify the caller the finish of the task, so the caller must be registered to the event,
     * otherwise it will not be able to listen to the response.
     * @param user User's identifier
     * @param password User's password
     */
    public void login(String user, String password) {
        Call<Alumno> call = service.login(user, password);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(@NotNull Call<Alumno> call, @NotNull Response<Alumno> response) {
                switch (response.code()) {
                    case 200:
                        Alumno data = response.body();
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(new LoginMessage(true,
                                            SieApiConverter.AlumnoToGeneralInfo(data)));
                        } else {
                            Crashlytics.log("200 Error data null while login");
                        }
                        break;
                    case 404:
                        EventBus.getDefault()
                                .post(new LoginMessage(false, "El usuario no existe"));
                        break;
                    case 403:
                        //todo: agregar logica para actualizar el token de auth.
                        break;
                    default:
                        EventBus.getDefault()
                                .post(new LoginMessage(false, response.message()));
                        Crashlytics.log(response.code() + "Error code while getting login");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<Alumno> call, @NotNull Throwable t) {
                EventBus.getDefault()
                        .post(new LoginMessage(false, t.getMessage()));
                Crashlytics.log(t.getMessage() + " - Error code while getting login");
            }
        });
    }

    /**
     * Gets the actual grade´s list of a user by calling the API and inserts it to the database
     * @param studentId User's identifier
     */
    public void getGradesList(String studentId, String token) {
        Call<List<Grade>> call = service.getGrades(studentId, token);
        call.enqueue(new Callback<List<Grade>>() {
            @Override
            public void onResponse(@NotNull Call<List<Grade>> call,
                                   @NotNull Response<List<Grade>> response) {
                switch (response.code()) {
                    case 200:
                        List<Grade> data = response.body();
                        if (data != null) {
                            SieApiConverter.insertGrades(mApp, data);
                        } else {
                            Crashlytics.log("200 Error data null while getting grades list");
                        }
                        break;
                    default:
                        Crashlytics.log(response.code() + "Error code while getting grades");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Grade>> call, @NotNull Throwable t) {
                Crashlytics.log(t.getMessage() + " - Error code while getting grades");
            }
        });
    }

    /**
     * Gets the user's kardex by calling the API and inserts it to the database
     * @param studentId s
     */
    public void getKardexList(String studentId, String token) {
        Call<List<Kardex>> call = service.getKardex(studentId, token);
        call.enqueue(new Callback<List<Kardex>>() {
            @Override
            public void onResponse(@NotNull Call<List<Kardex>> call,
                                   @NotNull Response<List<Kardex>> response) {
                switch (response.code()) {
                    case 200:
                        List<Kardex> data = response.body();
                        if (data != null) {
                            SieApiConverter.insertKardex(mApp, data);
                        } else {
                            Crashlytics.log("200 Error data null while getting kardex");
                        }
                        break;
                    default:
                        Crashlytics.log(response.code() + "Error code while getting kardex");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Kardex>> call, @NotNull Throwable t) {
                Crashlytics.log(t.getMessage() + " - Error code while getting kardex");
            }
        });
    }


    /**
     * Get's the user's weekly getSchedule by calling the API and inserts it to the database
     * @param studentId User's identifier
     */
    public void getSchedule(String studentId, String token) {
        Call<Semana> call = service.getSchedule(studentId, token);
        call.enqueue(new Callback<Semana>() {
            @Override
            public void onResponse(@NotNull Call<Semana> call, @NotNull Response<Semana> response) {
                switch (response.code()) {
                    case 200:
                        Semana data = response.body();
                        if (data != null) {
                            SieApiConverter.insertSchedule(data, mApp);
                        } else {
                            Crashlytics.log("200 Error data null while getting the getSchedule");
                        }
                        break;
                    default:
                        Crashlytics.log(response.code() + "Error code while getting getSchedule");
                        break;
                }
            }

            @Override
            public void onFailure(@NotNull Call<Semana> call, @NotNull Throwable t) {
                Crashlytics.log(t.getMessage() + " - Error code while getting getSchedule");
            }
        });
    }
}
