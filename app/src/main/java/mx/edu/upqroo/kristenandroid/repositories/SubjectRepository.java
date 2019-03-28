package mx.edu.upqroo.kristenandroid.repositories;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import mx.edu.upqroo.kristenandroid.database.KristenRoomDatabase;
import mx.edu.upqroo.kristenandroid.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.database.entities.SubjectDao;

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
        return mSubjectDao.getSubjectById(subjectId);
    }

    public LiveData<List<Subject>> getSubjectsByDayId(int dayId) {
        return mSubjectDao.getSubjectsByDayId(dayId);
    }

    public void insert(Subject subject) {
        AsyncTask.execute(() -> mSubjectDao.insert(subject));
    }

    public void deleteAll() {
        AsyncTask.execute(() -> mSubjectDao.deleteAll());
    }
}
