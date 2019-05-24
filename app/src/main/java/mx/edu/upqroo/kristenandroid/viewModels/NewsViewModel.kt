package mx.edu.upqroo.kristenandroid.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import mx.edu.upqroo.kristenandroid.adapters.source.NewsDataSource
import mx.edu.upqroo.kristenandroid.adapters.source.NewsDataSourceFactory
import mx.edu.upqroo.kristenandroid.data.models.News

class NewsViewModel : ViewModel() {
    val news: LiveData<PagedList<News>>
    val dataSourceFactory: NewsDataSourceFactory = NewsDataSourceFactory()

    init {
        //getting our data source factory

        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(NewsDataSource.PAGE_SIZE)
                .build()

        //Building the paged list
        news = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }
}