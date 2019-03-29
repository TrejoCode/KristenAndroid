package mx.edu.upqroo.kristenandroid.viewModels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject;
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

    public LiveData<List<ScheduleSubject>> getDays(String userId) {
        return mDayRepository.getDaysByUserId(userId);
    }

    public LiveData<List<Subject>> getSubjects(long dayId) {
        return mSubjectRepository.getSubjectsByDayId(dayId);
    }

    public LiveData<List<Subject>> getSubjects() {
        return mSubjectRepository.getAll();
    }
}
