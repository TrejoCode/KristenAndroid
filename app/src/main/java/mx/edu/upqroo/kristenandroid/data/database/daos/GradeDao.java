package mx.edu.upqroo.kristenandroid.data.database.daos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;

@Dao
public interface GradeDao {
    @Insert
    long insert(Grade grade);

    @Update
    void update(Grade... grade);

    @Delete
    void delete(Grade... grade);

    @Query("DELETE FROM grade")
    void deleteAll();

    @Query("SELECT * FROM grade WHERE gradeId = :gradeId")
    LiveData<Grade> getById(long gradeId);

    @Query("SELECT * FROM grade")
    LiveData<List<Grade>> getAll();

    @Query("SELECT * FROM grade WHERE userId = :userId")
    LiveData<List<Grade>> getByUserId(final String userId);

    @Query("SELECT COUNT(*) FROM grade")
    int count();
}
