package mx.edu.upqroo.kristenandroid.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.database.entities.Day;
import mx.edu.upqroo.kristenandroid.database.entities.DayDao;

public class DayRepository {
    private static DayRepository mInstance;
    private DayDao mDayDao;

    private DayRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mDayDao = db.dayDao();
    }

    public static DayRepository getInstance(Application context) {
        if (mInstance == null) {
            mInstance = new DayRepository(context);
        }
        return mInstance;
    }

    public LiveData<Day> getDay(int id) {
        return mDayDao.getDayById(id);
    }

    public LiveData<List<Day>> getDaysByUserId(String userId) {
        return mDayDao.getDaysByUserId(userId);
    }

    public void insert(Day day) {
        AsyncTask.execute(() -> mDayDao.insert(day));
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mDayDao.deleteAll());
    }
}
