package mx.edu.upqroo.kristenandroid.data.database.daos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;

@Dao
public interface SubjectDao {

    @Insert
    void insert(Subject subject);

    @Update
    void update(Subject... subjects);

    @Delete
    void delete(Subject... subjects);

    @Query("DELETE FROM subject")
    void deleteAll();

    @Query("SELECT * FROM subject WHERE subjectId = :subjectId")
    LiveData<Subject> getById(long subjectId);

    @Query("SELECT * FROM subject")
    LiveData<List<Subject>> getAll();

    @Query("SELECT * FROM subject WHERE dayId = :dayId")
    List<Subject> getByDayId(final long dayId);
}
