package mx.edu.upqroo.kristenandroid.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.data.database.daos.SubjectDao;

public class SubjectRepository {

    private static SubjectRepository mInstance;
    private SubjectDao mSubjectDao;

    private SubjectRepository(Application application) {
        KristenRoomDatabase db = KristenRoomDatabase.getInstance(application);
        mSubjectDao = db.subjectDao();
    }

    public static SubjectRepository getInstance(Application context) {
        if (mInstance == null) {
            mInstance = new SubjectRepository(context);
        }
        return mInstance;
    }

    public LiveData<Subject> getSubject(int subjectId) {
        return mSubjectDao.getById(subjectId);
    }

    public LiveData<List<Subject>> getSubjectsByDayId(long dayId) {
        return mSubjectDao.getByDayId(dayId);
    }

    public LiveData<List<Subject>> getAll() {
        return mSubjectDao.getAll();
    }

    public void insert(Subject subject) {
        AsyncTask.execute(() -> mSubjectDao.insert(subject));
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mSubjectDao.deleteAll());
    }
}
