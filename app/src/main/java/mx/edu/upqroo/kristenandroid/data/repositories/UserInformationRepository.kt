package mx.edu.upqroo.kristenandroid.data.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.database.daos.UserInformationDao
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation

class UserInformationRepository private constructor(application: Application) {
    private val mUserInformationDao: UserInformationDao

    init {
        val db = KristenRoomDatabase.getInstance(application)
        mUserInformationDao = db.userInformationDao()
    }

    fun getUserInformation(id: String): LiveData<UserInformation> {
        return mUserInformationDao.getUserInformation(id)
    }

    fun getUserInformationNotLive(id: String): UserInformation {
        return mUserInformationDao.getUserInformationNotLive(id)
    }

    fun insert(userInformation: UserInformation) {
        AsyncTask.execute {
            mUserInformationDao.deleteAll()
            mUserInformationDao.insert(userInformation)
        }
    }

    fun deleteAll() {
        AsyncTask.execute { mUserInformationDao.deleteAll() }
    }

    companion object {
        private var mInstance: UserInformationRepository? = null

        fun getInstance(context: Application): UserInformationRepository {
            if (mInstance == null) {
                mInstance = UserInformationRepository(context)
            }
            return mInstance as UserInformationRepository
        }
    }
}
