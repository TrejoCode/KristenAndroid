package mx.edu.upqroo.kristenandroid.adapters.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice

class NoticeDataSourceFactory : DataSource.Factory<Int, Notice>() {

    val liveDataSource = MutableLiveData<PageKeyedDataSource<Int, Notice>>()

    override fun create(): DataSource<Int, Notice> {
        //getting our data source object
        val itemDataSource = NoticeDataSource()

        //posting the data source to get the values
        liveDataSource.postValue(itemDataSource)

        //returning the datasource
        return itemDataSource
    }
}
