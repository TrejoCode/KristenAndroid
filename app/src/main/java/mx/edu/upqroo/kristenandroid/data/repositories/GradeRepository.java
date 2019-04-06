package mx.edu.upqroo.kristenandroid.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.daos.GradeDao;
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.api.sie.SieApiServices;

public class GradeRepository {
    private static GradeRepository mInstance;
    private GradeDao mGradeDao;
    private SieApiServices mApi;

    private GradeRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mGradeDao = db.gradeDao();
        mApi = SieApiServices.getInstance(application);
    }

    public static GradeRepository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new GradeRepository(application);
        }
        return mInstance;
    }

    public LiveData<List<Grade>> getGrades(String userId) {
        LiveData<List<Grade>> response = mGradeDao.getByUserId(userId);
        AsyncTask.execute(() -> {
            if (mGradeDao.count() == 0) {
                mApi.getGradesList(SessionManager.getInstance().getSession().getUserId(),
                        SessionManager.getInstance().getSession().getConfig().getUserToken());
            }
        });
        return response;
    }

    public void updateGradesFromService() {
        mApi.getGradesList(SessionManager.getInstance().getSession().getUserId(),
                SessionManager.getInstance().getSession().getConfig().getUserToken());
    }

    public void insert(Grade kardex) {
        AsyncTask.execute(() -> mGradeDao.insert(kardex));
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mGradeDao.deleteAll());
    }

    public void update(Grade... kardex){
        AsyncTask.execute(() -> mGradeDao.update(kardex));
    }
}
