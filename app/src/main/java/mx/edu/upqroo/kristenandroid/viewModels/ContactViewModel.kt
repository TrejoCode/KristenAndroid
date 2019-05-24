package mx.edu.upqroo.kristenandroid.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact
import mx.edu.upqroo.kristenandroid.data.repositories.ContactRepository

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: ContactRepository = ContactRepository.getInstance(application)

    val contacts: LiveData<List<Contact>>
        get() = mRepo.contact

    fun updateFromService() {
        mRepo.updateContacts()
    }
}
