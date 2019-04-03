package mx.edu.upqroo.kristenandroid.viewModels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex;
import mx.edu.upqroo.kristenandroid.data.repositories.KardexRepository;

public class KardexViewModel extends AndroidViewModel {
    private KardexRepository mRepo;

    public KardexViewModel(@NonNull Application application) {
        super(application);
        mRepo = KardexRepository.getInstance(application);
    }

    public LiveData<List<Kardex>> getKardex(String userId) {
        return mRepo.getKardex(userId);
    }
}
