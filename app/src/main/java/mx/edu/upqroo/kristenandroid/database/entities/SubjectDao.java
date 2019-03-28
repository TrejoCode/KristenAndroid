package mx.edu.upqroo.kristenandroid.database.entities;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
    LiveData<Subject> getSubjectById(int subjectId);

    @Query("SELECT * FROM subject")
    LiveData<List<Subject>> getAllSubjects();

    @Query("SELECT * FROM subject WHERE dayId = :dayId")
    LiveData<List<Subject>> getSubjectsByDayId(final int dayId);
}
