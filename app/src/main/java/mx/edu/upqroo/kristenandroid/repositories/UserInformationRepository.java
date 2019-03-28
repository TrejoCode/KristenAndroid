package mx.edu.upqroo.kristenandroid.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.database.entities.UserInformationDao;

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

    public void insert(UserInformation userInformation) {
        new insertAsyncTask(mUserInformationDao).execute(userInformation);
    }

    public void deleteAll() {
        Runnable deleteRunnable = () -> mUserInformationDao.deleteAll();
        deleteRunnable.run();
    }

    private static class insertAsyncTask extends AsyncTask<UserInformation, Void, Void> {

        private UserInformationDao mAsyncTaskDao;

        insertAsyncTask(UserInformationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(final UserInformation... params) {
            mAsyncTaskDao.deleteAll();
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
