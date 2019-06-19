package mx.edu.upqroo.kristenandroid.viewModels

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import mx.edu.upqroo.kristenandroid.adapters.source.NoticeDataSourceFactory
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice
import mx.edu.upqroo.kristenandroid.data.repositories.NoticeRepository

class NoticesViewModel(application: Application) : AndroidViewModel(application) {
    private val mNoticeRepo: NoticeRepository = NoticeRepository.getInstance(application)

    val notices: LiveData<PagedList<Notice>>
        get() = mNoticeRepo.notices
    val dataSourceFactory: NoticeDataSourceFactory
        get() = mNoticeRepo.dataSourceFactory

}
