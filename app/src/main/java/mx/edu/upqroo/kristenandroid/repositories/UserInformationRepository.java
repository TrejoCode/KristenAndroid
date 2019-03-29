package mx.edu.upqroo.kristenandroid.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.data.database.daos.UserInformationDao;

public class UserInformationRepository {
    private static UserInformationRepository mInstance;
    private UserInformationDao mUserInformationDao;

    private UserInformationRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mUserInformationDao = db.userInformationDao();
    }

    public static UserInformationRepository getInstance(Application context) {
        if (mInstance == null) {
            mInstance = new UserInformationRepository(context);
        }
        return mInstance;
    }

    public LiveData<UserInformation> getUserInformation(String id) {
        return mUserInformationDao.getUserInformation(id);
    }

    public UserInformation getUserInformationNotLive(String id) {
        return mUserInformationDao.getUserInformationNotLive(id);
    }

    public void insert(UserInformation userInformation) {
        AsyncTask.execute(() -> {
            mUserInformationDao.deleteAll();
            mUserInformationDao.insert(userInformation);
        });
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mUserInformationDao.deleteAll());
    }
}
