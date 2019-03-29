package mx.edu.upqroo.kristenandroid.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.data.database.daos.DayDao;
import mx.edu.upqroo.kristenandroid.services.sie.SieApiServices;

public class DayRepository {
    private static DayRepository mInstance;
    private DayDao mDayDao;
    private SieApiServices mApi;

    private DayRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mDayDao = db.dayDao();
        mApi = SieApiServices.getInstance(application);
    }

    public static DayRepository getInstance(Application context) {
        if (mInstance == null) {
            mInstance = new DayRepository(context);
        }
        return mInstance;
    }

    public LiveData<Day> getDay(int id) {
        return mDayDao.getById(id);
    }

    public LiveData<List<ScheduleSubject>> getDaysByUserId(String userId) {
        LiveData<List<ScheduleSubject>> response = mDayDao.getDaysAndSubjectsFromUser(userId);
        AsyncTask.execute(() -> {
            if (mDayDao.count() == 0) {
                mApi.getSchedule(SessionManager.getInstance().getSession().getUserId(),
                        SessionManager.getInstance().getSession().getConfig().getUserToken());
            }
        });
        return response;
    }

    public void insert(Day day, OnDayInserted listeners) {
        AsyncTask.execute(() -> {
            long id = mDayDao.insert(day);
            if (listeners != null) {
                listeners.onInsertCompleted(id);
            }
        });
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mDayDao.deleteAll());
    }

    public void update(Day... day){
        AsyncTask.execute(() -> mDayDao.update(day));
    }

    public interface OnDayInserted {
        void onInsertCompleted(long id);
    }
}
