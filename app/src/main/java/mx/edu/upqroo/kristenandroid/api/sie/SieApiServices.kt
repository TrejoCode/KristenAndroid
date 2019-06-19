package mx.edu.upqroo.kristenandroid.api.sie

import android.app.Application

import com.crashlytics.android.Crashlytics

import org.greenrobot.eventbus.EventBus

import mx.edu.upqroo.kristenandroid.data.database.entities.Grade
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex
import mx.edu.upqroo.kristenandroid.api.sie.containers.Alumno
import mx.edu.upqroo.kristenandroid.api.sie.containers.Semana
import mx.edu.upqroo.kristenandroid.api.sie.messages.LoginMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SieApiServices private constructor(private val mApp: Application) {

    init {
        if (service == null) {
            service = SieApiClient.createService(SieApiInterface::class.java, authorizationToken)
        }
    }

    /**
     * Login the user by calling the API.
     * When the login is finished, weather is successful or not, it posts a message using EventBus
     * to notify the caller the finish of the task, so the caller must be registered to the event,
     * otherwise it will not be able to listen to the response.
     * @param user User's identifier
     * @param password User's password
     */
    fun login(user: String, password: String) {
        val call = service!!.login(user, password)
        call.enqueue(object : Callback<Alumno> {
            override fun onResponse(call: Call<Alumno>, response: Response<Alumno>) {
                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        if (data != null) {
                            EventBus.getDefault()
                                    .post(LoginMessage(true,
                                            SieApiConverter.AlumnoToGeneralInfo(data)))
                        } else {
                            Crashlytics.log("200 Error data null while login")
                        }
                    }
                    404 -> EventBus.getDefault()
                            .post(LoginMessage(false, "El usuario no existe"))
                    403 -> {
                    }
                    else -> {
                        EventBus.getDefault()
                                .post(LoginMessage(false, response.message()))
                        Crashlytics.log(response.code().toString() + "Error code while getting login")
                    }
                }//todo: agregar logica para actualizar el token de auth.
            }

            override fun onFailure(call: Call<Alumno>, t: Throwable) {
                EventBus.getDefault()
                        .post(LoginMessage(false, t.message!!))
                Crashlytics.log(t.message + " - Error code while getting login")
            }
        })
    }

    /**
     * Gets the actual grade´s list of a user by calling the API and inserts it to the database
     * @param studentId User's identifier
     */
    fun getGradesList(studentId: String, token: String) {
        val call = service!!.getGrades(studentId, token)
        call.enqueue(object : Callback<List<Grade>> {
            override fun onResponse(call: Call<List<Grade>>,
                                    response: Response<List<Grade>>) {
                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        if (data != null) {
                            SieApiConverter.insertGrades(mApp, data)
                        } else {
                            Crashlytics.log("200 Error data null while getting grades list")
                        }
                    }
                    else -> Crashlytics.log(response.code().toString() + "Error code while getting grades")
                }
            }

            override fun onFailure(call: Call<List<Grade>>, t: Throwable) {
                Crashlytics.log(t.message + " - Error code while getting grades")
            }
        })
    }

    /**
     * Gets the user's kardex by calling the API and inserts it to the database
     * @param studentId s
     */
    fun getKardexList(studentId: String, token: String) {
        val call = service!!.getKardex(studentId, token)
        call.enqueue(object : Callback<List<Kardex>> {
            override fun onResponse(call: Call<List<Kardex>>,
                                    response: Response<List<Kardex>>) {
                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        if (data != null) {
                            SieApiConverter.insertKardex(mApp, data)
                        } else {
                            Crashlytics.log("200 Error data null while getting kardex")
                        }
                    }
                    else -> Crashlytics.log(response.code().toString() + "Error code while getting kardex")
                }
            }

            override fun onFailure(call: Call<List<Kardex>>, t: Throwable) {
                Crashlytics.log(t.message + " - Error code while getting kardex")
            }
        })
    }

    /**
     * Get's the user's weekly getSchedule by calling the API and inserts it to the database
     * @param studentId User's identifier
     */
    fun getSchedule(studentId: String, token: String) {
        val call = service!!.getSchedule(studentId, token)
        call.enqueue(object : Callback<Semana> {
            override fun onResponse(call: Call<Semana>, response: Response<Semana>) {
                when (response.code()) {
                    200 -> {
                        val data = response.body()
                        if (data != null) {
                            SieApiConverter.insertSchedule(data, mApp)
                        } else {
                            Crashlytics.log("200 Error data null while getting the getSchedule")
                        }
                    }
                    else -> Crashlytics.log(response.code().toString() + "Error code while getting getSchedule")
                }
            }

            override fun onFailure(call: Call<Semana>, t: Throwable) {
                Crashlytics.log(t.message + " - Error code while getting getSchedule")
            }
        })
    }

    companion object {
        private var mInstance: SieApiServices? = null
        private var service: SieApiInterface? = null
        private const val authorizationToken = "Q2nDsWEgwqFvaCBwYXRyaWEhIHR1cyBzaWVuZXMgZGUgb2xpdmEgZGUgbGEgcGF6IGVsIGFyY8OhbmdlbCBkaXZpbm8="

        fun getInstance(application: Application): SieApiServices {
            if (mInstance == null) {
                mInstance = SieApiServices(application)
            }
            return mInstance as SieApiServices
        }

        fun getService(): SieApiInterface {
            if (service == null) {
                service = SieApiClient.createService(SieApiInterface::class.java, authorizationToken)
            }
            return service as SieApiInterface
        }
    }
}
