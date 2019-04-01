package mx.edu.upqroo.kristenandroid.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository;

public class UserViewModel extends AndroidViewModel {
    private UserInformationRepository mUserInfoRepo;
    private LiveData<UserInformation> mUserInfo;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mUserInfoRepo = UserInformationRepository.getInstance(application);
    }

    public LiveData<UserInformation> getUser(@NonNull String id) {
        return mUserInfoRepo.getUserInformation(id);
    }

    public void insert(UserInformation userInformation) {
        mUserInfoRepo.insert(userInformation);
    }
}
