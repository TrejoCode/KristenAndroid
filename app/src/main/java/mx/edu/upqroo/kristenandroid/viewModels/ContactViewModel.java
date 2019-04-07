package mx.edu.upqroo.kristenandroid.viewModels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact;
import mx.edu.upqroo.kristenandroid.data.repositories.ContactRepository;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository mRepo;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        mRepo = ContactRepository.getInstance(application);
    }

    public LiveData<List<Contact>> getContacts() {
        return mRepo.getContact();
    }

    public void updateFromService() {
        mRepo.updateContacts();
    }
}
