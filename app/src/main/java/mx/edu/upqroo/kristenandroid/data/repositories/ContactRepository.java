package mx.edu.upqroo.kristenandroid.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.api.kristen.KristenApiServices;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.daos.ContactDao;
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact;

public class ContactRepository {
    private static ContactRepository mInstance;
    private ContactDao mContactDao;
    private KristenApiServices mApi;
    private Application mApp;

    private ContactRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mContactDao = db.contactDao();
        mApi = KristenApiServices.getInstance();
        mApp = application;
    }

    public static ContactRepository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new ContactRepository(application);
        }
        return mInstance;
    }

    public LiveData<List<Contact>> getContact() {
        mApi.getContacts(mApp);
        return mContactDao.getAll();
    }

    public void updateContacts() {
        mApi.getContacts(mApp);
    }

    public List<Contact> getContactSync() {
        return mContactDao.getAllSync();
    }

    public void upsert(Contact entity) {
        long id = insert(entity);
        if (id == -1) {
            update(entity);
        }
    }

    public long insert(Contact contact) {
        return mContactDao.insert(contact);
    }

    public void deleteAll() {
        mContactDao.deleteAll();
    }

    public void update(Contact... contact){
        mContactDao.update(contact);
    }
}
