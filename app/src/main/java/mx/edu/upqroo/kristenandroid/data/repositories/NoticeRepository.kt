package mx.edu.upqroo.kristenandroid.data.repositories

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import mx.edu.upqroo.kristenandroid.adapters.source.NewsDataSourceFactory
import mx.edu.upqroo.kristenandroid.adapters.source.NoticeDataSource
import mx.edu.upqroo.kristenandroid.adapters.source.NoticeDataSourceFactory
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.database.daos.NoticeDao
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice

class NoticeRepository private constructor(application: Application) {
    private val mNoticeDao: NoticeDao

    val notices: LiveData<PagedList<Notice>>
    val dataSourceFactory: NoticeDataSourceFactory

    val all: LiveData<List<Notice>>
        get() = mNoticeDao.all


    init {
        val db = KristenRoomDatabase.getInstance(application)
        mNoticeDao = db.noticeDao()

        //getting our data source factory
        dataSourceFactory = NoticeDataSourceFactory()

        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(NoticeDataSource.PAGE_SIZE).build()

        //Building the paged list
        notices = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }

    fun insert(notice: Notice) {
        AsyncTask.execute { mNoticeDao.insert(notice) }
    }

    fun deleteAll() {
        AsyncTask.execute { mNoticeDao.deleteAll() }
    }

    fun update(vararg notice: Notice) {
        AsyncTask.execute { mNoticeDao.update(*notice) }
    }

    companion object {
        private var mInstance: NoticeRepository? = null

        fun getInstance(application: Application): NoticeRepository {
            if (mInstance == null) {
                mInstance = NoticeRepository(application)
            }
            return mInstance as NoticeRepository
        }
    }
}
