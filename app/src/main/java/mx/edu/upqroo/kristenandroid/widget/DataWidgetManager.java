package mx.edu.upqroo.kristenandroid.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.util.Serializer;

public class DataWidgetManager {
    private static final String DATA_KEY = "DataWidgetManagerSaved";
    private static final String PREFERENCE_FILE_NAME = "WidgetDataSaved";

    public static void updateSchedule(List<ScheduleSubject> scheduleSubjects, Context context) {
        Type scheduleSubjectType = new TypeToken<List<ScheduleSubject>>(){}.getType();
        String serialize = Serializer.Serialize(scheduleSubjects, scheduleSubjectType);

        SharedPreferences mSharedPref = context
                .getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(DataWidgetManager.DATA_KEY, serialize);
        editor.apply();
    }

    public static void deleteSchedule(Context context) {
        SharedPreferences mSharedPref = context
                .getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.remove(DataWidgetManager.DATA_KEY);
        editor.apply();
    }

    static List<ScheduleSubject> getSchedule(Context context) {
        SharedPreferences mSharedPref = context
                .getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        String data = mSharedPref.getString(DataWidgetManager.DATA_KEY, "");
        Type listType = new TypeToken<ArrayList<ScheduleSubject>>(){}.getType();
        return Serializer.Deserialize(data, listType);
    }

    public static void updateWidgetAsync(Context context) {
        AsyncTask.execute(() -> {
            if (SessionManager.getInstance().getSession() != null) {
                List<ScheduleSubject> scheduleSubjects = KristenRoomDatabase
                        .getInstance(context)
                        .dayDao()
                        .getDaysAndSubjectsFromUserSync(SessionManager.getInstance()
                                .getSession().getUserId());
                updateSchedule(scheduleSubjects, context);
                ScheduleWidgetProvider.sendRefreshBroadcast(context);
            } else {
                deleteSchedule(context);
                ScheduleWidgetProvider.sendRefreshBroadcast(context);
            }
        });
    }
}
