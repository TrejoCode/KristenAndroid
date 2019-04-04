package mx.edu.upqroo.kristenandroid.adapters.source;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;

public class NoticeDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Notice>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Notice> create() {
        //getting our data source object
        NoticeDataSource itemDataSource = new NoticeDataSource();

        //posting the data source to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Notice>> getLiveDataSource() {
        return itemLiveDataSource;
    }
}
