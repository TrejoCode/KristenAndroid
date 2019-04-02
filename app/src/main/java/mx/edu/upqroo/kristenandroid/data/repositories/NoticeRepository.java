package mx.edu.upqroo.kristenandroid.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import mx.edu.upqroo.kristenandroid.adapters.source.NoticeDataSource;
import mx.edu.upqroo.kristenandroid.adapters.source.NoticeDataSourceFactory;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.daos.NoticeDao;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;

public class NoticeRepository {
    private static NoticeRepository mInstance;
    private NoticeDao mNoticeDao;

    private LiveData<PagedList<Notice>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Notice>> liveDataSource;


    private NoticeRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mNoticeDao = db.noticeDao();

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

    public static NoticeRepository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new NoticeRepository(application);
        }
        return mInstance;
    }

    public LiveData<PagedList<Notice>> getNotices() {
        return itemPagedList;
    }

    public LiveData<List<Notice>> getAll() {
        return mNoticeDao.getAll();
    }

    public void insert(Notice notice) {
        AsyncTask.execute(() -> mNoticeDao.insert(notice));
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mNoticeDao.deleteAll());
    }

    public void update(Notice... notice){
        AsyncTask.execute(() -> mNoticeDao.update(notice));
    }
}
