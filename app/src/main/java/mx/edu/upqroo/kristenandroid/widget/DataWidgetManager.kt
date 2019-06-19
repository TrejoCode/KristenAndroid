package mx.edu.upqroo.kristenandroid.widget

import android.content.Context
import android.os.AsyncTask
import com.google.gson.reflect.TypeToken
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.util.Serializer
import java.util.*

object DataWidgetManager {
    private const val DATA_KEY = "DataWidgetManagerSaved"
    private const val PREFERENCE_FILE_NAME = "WidgetDataSaved"

    private fun updateSchedule(scheduleSubjects: List<ScheduleSubject>, context: Context) {
        val scheduleSubjectType = object : TypeToken<List<ScheduleSubject>>() {

        }.type
        val serialize = Serializer.serialize(scheduleSubjects, scheduleSubjectType)

        val mSharedPref = context
                .getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        val editor = mSharedPref.edit()
        editor.putString(DATA_KEY, serialize)
        editor.apply()
    }

    fun deleteSchedule(context: Context) {
        val mSharedPref = context
                .getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        val editor = mSharedPref.edit()
        editor.remove(DATA_KEY)
        editor.apply()
    }

    internal fun getSchedule(context: Context): List<ScheduleSubject> {
        val mSharedPref = context
                .getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        val data = mSharedPref.getString(DATA_KEY, "")
        val listType = object : TypeToken<ArrayList<ScheduleSubject>>() {

        }.type
        return Serializer.deserialize(data!!, listType)
    }

    fun updateWidgetAsync(context: Context) {
        AsyncTask.execute {
            SessionManager.instance.session
            val scheduleSubjects = KristenRoomDatabase
                    .getInstance(context)?.dayDao()?.getDaysAndSubjectsFromUserSync(SessionManager.instance
                            .session.userId)
            if (scheduleSubjects != null) {
                updateSchedule(scheduleSubjects, context)
            }
            ScheduleWidgetProvider.sendRefreshBroadcast(context)
        }
    }
}
