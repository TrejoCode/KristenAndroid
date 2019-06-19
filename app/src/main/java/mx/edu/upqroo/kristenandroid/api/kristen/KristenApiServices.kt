package mx.edu.upqroo.kristenandroid.api.kristen

import android.app.Application
import com.crashlytics.android.Crashlytics
import mx.edu.upqroo.kristenandroid.api.kristen.containers.PublicacionContenido
import mx.edu.upqroo.kristenandroid.api.kristen.messages.CalendarUrlMessage
import mx.edu.upqroo.kristenandroid.api.kristen.messages.NewsDetailMessage
import mx.edu.upqroo.kristenandroid.api.kristen.messages.PostContentMessage
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * <h1>KristenApiServices</h1>
 * Class that encapsulates the call to the API defined in the Interface.
 * Every method response is notify by a EventBus post.
 */
class KristenApiServices private constructor() {

    var service: KristenApiInterface = KristenApiClient.createService(KristenApiInterface::class.java)

    /**
     * Gets the content of a post by calling the API.
     * When the call is finish a message is posted by EventBus, so the caller must be subscribe
     * to it.
     * @param postId Post's identifier
     */
    fun getPostContent(postId: String) {
        val call = service.listContents(postId)
        call.enqueue(object : Callback<PublicacionContenido> {
            override fun onResponse(call: Call<PublicacionContenido>,
                                    response: Response<PublicacionContenido>) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null) {
                        EventBus.getDefault()
                                .post(NewsDetailMessage(true, KristenApiConverter
                                        .publicacionToNewsDetail(data)))
                    } else {
                        Crashlytics.log("200 Error data null while getting post content")
                        EventBus.getDefault()
                                .post(NewsDetailMessage(false, null))
                    }
                } else {
                    Crashlytics.log(
                            response.code().toString() + "Error code while getting content")
                    EventBus.getDefault()
                            .post(NewsDetailMessage(false, null))
                }
            }

            override fun onFailure(call: Call<PublicacionContenido>, t: Throwable) {
                EventBus.getDefault()
                        .post(PostContentMessage(false, null))
                Crashlytics.log(t.message + " - Error code while getting post content")
            }
        })
    }

    fun getCalendarUrl() {
        val call = service.calendarUrl
        call.enqueue(object : Callback<PublicacionContenido> {
            override fun onResponse(call: Call<PublicacionContenido>,
                                    response: Response<PublicacionContenido>) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null) {
                        EventBus.getDefault()
                                .post(CalendarUrlMessage(
                                        data.contenidos!![0].contenido!!.texto!!,
                                        data.contenidos!![0].contenido!!.url!!))
                    } else {
                        EventBus.getDefault()
                                .post(CalendarUrlMessage("", ""))
                        Crashlytics.log("200 Error data null while getting the calendar")
                    }
                } else {
                    EventBus.getDefault()
                            .post(CalendarUrlMessage("", ""))
                    Crashlytics.log(
                            response.code().toString() + "Error code while getting calendar")
                }
            }

            override fun onFailure(call: Call<PublicacionContenido>, t: Throwable) {
                EventBus.getDefault()
                        .post(CalendarUrlMessage("", ""))
                Crashlytics.log(t.message + " - Error code while getting the calendar")
            }
        })
    }

    fun getContacts(application: Application) {
        val call = service.contacts
        call.enqueue(object : Callback<List<Contact>> {
            override fun onResponse(call: Call<List<Contact>>,
                                    response: Response<List<Contact>>) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null) {
                        KristenApiConverter.insertContacts(data, application)
                    } else {
                        Crashlytics.log("200 Error data null while getting contacts")
                    }
                } else {
                    Crashlytics.log(
                            response.code().toString() + "Error code while getting contacts")
                }
            }

            override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
                Crashlytics.log(t.message + " - Error code while getting contacts")
            }
        })
    }

    companion object {
        private var mInstance: KristenApiServices? = null

        val instance: KristenApiServices
            get() {
                if (mInstance == null) {
                    mInstance = KristenApiServices()
                }
                return mInstance as KristenApiServices
            }
    }
}
