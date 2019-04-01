package mx.edu.upqroo.kristenandroid.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import mx.edu.upqroo.kristenandroid.adapters.source.NewsDataSource;
import mx.edu.upqroo.kristenandroid.adapters.source.NewsDataSourceFactory;
import mx.edu.upqroo.kristenandroid.data.models.News;

public class NewsViewModel extends ViewModel {
    private LiveData<PagedList<News>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, News>> liveDataSource;

    public NewsViewModel() {
        //getting our data source factory
        NewsDataSourceFactory itemDataSourceFactory = new NewsDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(NewsDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();
    }

    /*public LiveData<List<Notice>> getNotices() {
        return mNoticeRepo.getAll();
    }*/
    public LiveData<PagedList<News>> getNews() {
        return itemPagedList;
    }
}