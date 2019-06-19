package mx.edu.upqroo.kristenandroid.data.repositories

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.database.daos.KardexDao
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.api.sie.SieApiServices

class KardexRepository private constructor(application: Application) {
    private val mKardexDao: KardexDao
    private val mApi: SieApiServices

    init {
        val db = KristenRoomDatabase.getInstance(application)
        mKardexDao = db.kardexDao()
        mApi = SieApiServices.getInstance(application)
    }

    fun getKardex(userId: String): LiveData<List<Kardex>> {
        val response = mKardexDao.getByUserId(userId)
        AsyncTask.execute {
            if (mKardexDao.count() == 0) {
                mApi.getKardexList(SessionManager.instance.session.userId,
                        SessionManager.instance.session.config.userToken)
            }
        }
        return response
    }

    fun updateKardexFromService() {
        mApi.getKardexList(SessionManager.instance.session.userId,
                SessionManager.instance.session.config.userToken)
    }

    fun insert(kardex: Kardex) {
        AsyncTask.execute { mKardexDao.insert(kardex) }
    }

    fun deleteAll() {
        AsyncTask.execute { mKardexDao.deleteAll() }
    }

    fun update(vararg kardex: Kardex) {
        AsyncTask.execute { mKardexDao.update(*kardex) }
    }

    companion object {
        private var mInstance: KardexRepository? = null

        fun getInstance(application: Application): KardexRepository {
            if (mInstance == null) {
                mInstance = KardexRepository(application)
            }
            return mInstance as KardexRepository
        }
    }
}
