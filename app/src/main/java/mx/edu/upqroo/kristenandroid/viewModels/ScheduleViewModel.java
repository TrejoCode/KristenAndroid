package mx.edu.upqroo.kristenandroid.viewModels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.repositories.DayRepository;
import mx.edu.upqroo.kristenandroid.repositories.SubjectRepository;

public class ScheduleViewModel extends AndroidViewModel {
    private DayRepository mDayRepository;
    private SubjectRepository mSubjectRepository;

    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        mDayRepository = DayRepository.getInstance(application);
        mSubjectRepository = SubjectRepository.getInstance(application);
    }

    public LiveData<List<Day>> getDays(String userId) {
        return mDayRepository.getDaysByUserId(userId);
    }
}
