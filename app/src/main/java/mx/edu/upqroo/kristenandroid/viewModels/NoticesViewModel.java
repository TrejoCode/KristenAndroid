package mx.edu.upqroo.kristenandroid.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import mx.edu.upqroo.kristenandroid.adapters.source.NoticeDataSource;
import mx.edu.upqroo.kristenandroid.adapters.source.NoticeDataSourceFactory;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;

public class NoticesViewModel extends ViewModel {
    private LiveData<PagedList<Notice>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Notice>> liveDataSource;

    public NoticesViewModel() {
        //getting our data source factory
        NoticeDataSourceFactory itemDataSourceFactory = new NoticeDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(NoticeDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();
    }

    /*public LiveData<List<Notice>> getNotices() {
        return mNoticeRepo.getAll();
    }*/
    public LiveData<PagedList<Notice>> getNotices() {
        return itemPagedList;
    }
}
