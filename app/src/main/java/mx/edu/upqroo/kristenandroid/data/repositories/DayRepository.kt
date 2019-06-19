package mx.edu.upqroo.kristenandroid.data.repositories

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.database.entities.Day
import mx.edu.upqroo.kristenandroid.data.database.daos.DayDao
import mx.edu.upqroo.kristenandroid.api.sie.SieApiServices

class DayRepository private constructor(application: Application) {
    private val mDayDao: DayDao
    private val mApi: SieApiServices

    init {
        val db = KristenRoomDatabase.getInstance(application)
        mDayDao = db.dayDao()
        mApi = SieApiServices.getInstance(application)
    }

    fun getDay(id: Int): LiveData<Day> {
        return mDayDao.getById(id.toLong())
    }

    fun getDaysByUserId(userId: String): LiveData<List<ScheduleSubject>> {
        val response = mDayDao.getDaysAndSubjectsFromUser(userId)
        AsyncTask.execute {
            if (mDayDao.count() == 0) {
                mApi.getSchedule(SessionManager.instance.session.userId,
                        SessionManager.instance.session.config.userToken)
            }
        }
        return response
    }

    fun updateScheduleFromService() {
        mApi.getSchedule(SessionManager.instance.session.userId,
                SessionManager.instance.session.config.userToken)
    }

    fun getDayByUserIdSync(userId: String): List<ScheduleSubject> {
        return mDayDao.getDaysAndSubjectsFromUserSync(userId)
    }


    fun insert(day: Day, listeners: OnDayInserted?) {
        AsyncTask.execute {
            val id = mDayDao.insert(day)
            listeners?.onInsertCompleted(id)
        }
    }

    fun deleteAll() {
        AsyncTask.execute { mDayDao.deleteAll() }
    }

    fun update(vararg day: Day) {
        AsyncTask.execute { mDayDao.update(*day) }
    }

    interface OnDayInserted {
        fun onInsertCompleted(id: Long)
    }

    companion object {
        var instance: DayRepository? = null
            private set

        fun getInstance(context: Application): DayRepository {
            if (instance == null) {
                instance = DayRepository(context)
            }
            return instance as DayRepository
        }
    }
}
