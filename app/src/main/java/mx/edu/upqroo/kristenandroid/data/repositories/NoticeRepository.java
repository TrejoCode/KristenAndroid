package mx.edu.upqroo.kristenandroid.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.daos.NoticeDao;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;
import mx.edu.upqroo.kristenandroid.services.kristen.KristenApiServices;

public class NoticeRepository {
    private static NoticeRepository mInstance;
    private NoticeDao mNoticeDao;
    private Application mApp;

    private NoticeRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mApp = application;
        mNoticeDao = db.noticeDao();
    }

    public static NoticeRepository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new NoticeRepository(application);
        }
        return mInstance;
    }

    public LiveData<List<Notice>> getAll() {
        /*AsyncTask.execute(() -> {
            if (mDayDao.count() == 0) {
                mApi.getSchedule(SessionManager.getInstance().getSession().getUserId(),
                        SessionManager.getInstance().getSession().getConfig().getUserToken());
            }
        });*/
        KristenApiServices.getInstance().getNotices(mApp);
        return mNoticeDao.getAll();
    }

    public void insert(Notice notice) {
        AsyncTask.execute(() -> mNoticeDao.insert(notice));
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mNoticeDao.deleteAll());
    }

    public void update(Notice... notice){
        AsyncTask.execute(() -> mNoticeDao.update(notice));
    }
}
