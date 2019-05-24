package mx.edu.upqroo.kristenandroid.adapters.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import mx.edu.upqroo.kristenandroid.data.models.News

class NewsDataSourceFactory : DataSource.Factory<Int, News>() {

    var liveDataSource: MutableLiveData<PageKeyedDataSource<Int, News>>? = null
        private set

    override fun create(): DataSource<Int, News> {
        val itemDataSource = NewsDataSource()

        liveDataSource = MutableLiveData()
        liveDataSource!!.postValue(itemDataSource)

        return itemDataSource
    }
}