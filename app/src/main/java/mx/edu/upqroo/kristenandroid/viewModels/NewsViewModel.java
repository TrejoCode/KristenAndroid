package mx.edu.upqroo.kristenandroid.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import mx.edu.upqroo.kristenandroid.adapters.source.NewsDataSource;
import mx.edu.upqroo.kristenandroid.adapters.source.NewsDataSourceFactory;
import mx.edu.upqroo.kristenandroid.data.models.News;

public class NewsViewModel extends ViewModel {
    private LiveData<PagedList<News>> itemPagedList;
    private NewsDataSourceFactory mDataSourceFactory;

    public NewsViewModel() {
        //getting our data source factory
        mDataSourceFactory = new NewsDataSourceFactory();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPageSize(NewsDataSource.PAGE_SIZE)
                        .build();

        //Building the paged list
        itemPagedList = new LivePagedListBuilder(mDataSourceFactory, pagedListConfig).build();
    }

    public LiveData<PagedList<News>> getNews() {
        return itemPagedList;
    }

    public NewsDataSourceFactory getDataSourceFactory() {
        return mDataSourceFactory;
    }
}