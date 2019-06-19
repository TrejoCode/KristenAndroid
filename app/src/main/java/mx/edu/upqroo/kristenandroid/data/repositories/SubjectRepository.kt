package mx.edu.upqroo.kristenandroid.data.repositories

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject
import mx.edu.upqroo.kristenandroid.data.database.daos.SubjectDao

class SubjectRepository private constructor(application: Application) {
    private val mSubjectDao: SubjectDao

    val all: LiveData<List<Subject>>
        get() = mSubjectDao.all

    init {
        val db = KristenRoomDatabase.getInstance(application)
        mSubjectDao = db.subjectDao()
    }

    fun getSubject(subjectId: Int): LiveData<Subject> {
        return mSubjectDao.getById(subjectId.toLong())
    }

    fun getSubjectsByDayId(dayId: Long): LiveData<List<Subject>> {
        return mSubjectDao.getByDayId(dayId)
    }

    fun insert(subject: Subject) {
        AsyncTask.execute { mSubjectDao.insert(subject) }
    }

    fun deleteAll() {
        AsyncTask.execute { mSubjectDao.deleteAll() }
    }

    companion object {

        private var mInstance: SubjectRepository? = null

        fun getInstance(context: Application): SubjectRepository {
            if (mInstance == null) {
                mInstance = SubjectRepository(context)
            }
            return mInstance as SubjectRepository
        }
    }
}
