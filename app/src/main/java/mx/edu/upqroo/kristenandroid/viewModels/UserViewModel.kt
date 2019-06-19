package mx.edu.upqroo.kristenandroid.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val mUserInfoRepo: UserInformationRepository =
            UserInformationRepository.getInstance(application)

    fun getUser(id: String): LiveData<UserInformation> {
        return mUserInfoRepo.getUserInformation(id)
    }

    fun insert(userInformation: UserInformation) {
        mUserInfoRepo.insert(userInformation)
    }
}
