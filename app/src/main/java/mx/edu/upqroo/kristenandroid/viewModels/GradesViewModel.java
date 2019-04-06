package mx.edu.upqroo.kristenandroid.viewModels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;
import mx.edu.upqroo.kristenandroid.data.repositories.GradeRepository;

public class GradesViewModel extends AndroidViewModel {
    private GradeRepository mRepo;

    public GradesViewModel(@NonNull Application application) {
        super(application);
        mRepo = GradeRepository.getInstance(application);
    }

    public LiveData<List<Grade>> getGrades(String userId) {
        return mRepo.getGrades(userId);
    }

    public void updateGradesFromService() {
        mRepo.updateGradesFromService();
    }
}
