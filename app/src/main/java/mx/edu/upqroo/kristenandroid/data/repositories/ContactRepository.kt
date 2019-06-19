package mx.edu.upqroo.kristenandroid.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.api.kristen.KristenApiServices
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.data.database.daos.ContactDao
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact

class ContactRepository private constructor(private val mApp: Application) {
    private val mContactDao: ContactDao
    private val mApi: KristenApiServices

    val contact: LiveData<List<Contact>>
        get() {
            mApi.getContacts(mApp)
            return mContactDao.all
        }

    val contactSync: List<Contact>
        get() = mContactDao.allSync

    init {
        val db = KristenRoomDatabase.getInstance(mApp)
        mContactDao = db.contactDao()
        mApi = KristenApiServices.instance
    }

    fun updateContacts() {
        mApi.getContacts(mApp)
    }

    fun upsert(entity: Contact) {
        val id = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }

    fun insert(contact: Contact): Long {
        return mContactDao.insert(contact)
    }

    fun deleteAll() {
        mContactDao.deleteAll()
    }

    fun update(vararg contact: Contact) {
        mContactDao.update(*contact)
    }

    companion object {
        private var mInstance: ContactRepository? = null

        fun getInstance(application: Application): ContactRepository {
            if (mInstance == null) {
                mInstance = ContactRepository(application)
            }
            return mInstance as ContactRepository
        }
    }
}
