package mx.edu.upqroo.kristenandroid.adapters.source;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import mx.edu.upqroo.kristenandroid.data.models.News;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, News>> itemLiveDataSource;

    @Override
    public DataSource<Integer, News> create() {
        NewsDataSource itemDataSource = new NewsDataSource();

        itemLiveDataSource = new MutableLiveData<>();
        itemLiveDataSource.postValue(itemDataSource);

        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, News>> getLiveDataSource() {
        return itemLiveDataSource;
    }
}