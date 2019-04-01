package mx.edu.upqroo.kristenandroid.adapters.source;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import mx.edu.upqroo.kristenandroid.data.models.News;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, News>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, News> create() {
        //getting our data source object
        NewsDataSource itemDataSource = new NewsDataSource();

        //posting the data source to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, News>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}