package mx.edu.upqroo.kristenandroid.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;
import mx.edu.upqroo.kristenandroid.data.repositories.NoticeRepository;

public class NoticesViewModel extends AndroidViewModel {
    private NoticeRepository mNoticeRepo;

    public NoticesViewModel(Application application) {
        super(application);
        mNoticeRepo = NoticeRepository.getInstance(application);
    }

    public LiveData<PagedList<Notice>> getNotices() {
        return mNoticeRepo.getNotices();
    }
}
