package mx.edu.upqroo.kristenandroid.data.repositories

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.database.daos.GradeDao
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.api.sie.SieApiServices

class GradeRepository private constructor(application: Application) {
    private val mGradeDao: GradeDao
    private val mApi: SieApiServices

    init {
        val db = KristenRoomDatabase.getInstance(application)
        mGradeDao = db.gradeDao()
        mApi = SieApiServices.getInstance(application)
    }

    fun getGrades(userId: String): LiveData<List<Grade>> {
        val response = mGradeDao.getByUserId(userId)
        AsyncTask.execute {
            if (mGradeDao.count() == 0) {
                mApi.getGradesList(SessionManager.instance.session.userId,
                        SessionManager.instance.session.config.userToken)
            }
        }
        return response
    }

    fun updateGradesFromService() {
        mApi.getGradesList(SessionManager.instance.session.userId,
                SessionManager.instance.session.config.userToken)
    }

    fun insert(kardex: Grade) {
        AsyncTask.execute { mGradeDao.insert(kardex) }
    }

    fun deleteAll() {
        AsyncTask.execute { mGradeDao.deleteAll() }
    }

    fun update(vararg kardex: Grade) {
        AsyncTask.execute { mGradeDao.update(*kardex) }
    }

    companion object {
        private var mInstance: GradeRepository? = null

        fun getInstance(application: Application): GradeRepository {
            if (mInstance == null) {
                mInstance = GradeRepository(application)
            }
            return mInstance as GradeRepository
        }
    }
}
