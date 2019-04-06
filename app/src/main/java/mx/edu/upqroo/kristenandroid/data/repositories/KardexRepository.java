package mx.edu.upqroo.kristenandroid.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.daos.KardexDao;
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.api.sie.SieApiServices;

public class KardexRepository {
    private static KardexRepository mInstance;
    private KardexDao mKardexDao;
    private SieApiServices mApi;

    private KardexRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mKardexDao = db.kardexDao();
        mApi = SieApiServices.getInstance(application);
    }

    public static KardexRepository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new KardexRepository(application);
        }
        return mInstance;
    }

    public LiveData<List<Kardex>> getKardex(String userId) {
        LiveData<List<Kardex>> response = mKardexDao.getByUserId(userId);
        AsyncTask.execute(() -> {
            if (mKardexDao.count() == 0) {
                mApi.getKardexList(SessionManager.getInstance().getSession().getUserId(),
                        SessionManager.getInstance().getSession().getConfig().getUserToken());
            }
        });
        return response;
    }

    public void updateKardexFromService() {
        mApi.getKardexList(SessionManager.getInstance().getSession().getUserId(),
                SessionManager.getInstance().getSession().getConfig().getUserToken());
    }

    public void insert(Kardex kardex) {
        AsyncTask.execute(() -> mKardexDao.insert(kardex));
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mKardexDao.deleteAll());
    }

    public void update(Kardex... kardex){
        AsyncTask.execute(() -> mKardexDao.update(kardex));
    }
}
