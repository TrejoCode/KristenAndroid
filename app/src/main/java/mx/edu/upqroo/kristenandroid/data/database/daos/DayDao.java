package mx.edu.upqroo.kristenandroid.data.database.daos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;

@Dao
public interface DayDao {

    @Insert
    long insert(Day day);

    @Update
    void update(Day... days);

    @Delete
    void delete(Day... days);

    @Query("DELETE FROM day")
    void deleteAll();

    @Query("SELECT * FROM day WHERE dayId = :dayId")
    LiveData<Day> getById(long dayId);

    @Query("SELECT * FROM day")
    LiveData<List<Day>> getAll();

    @Query("SELECT * FROM day WHERE userId = :userId")
    LiveData<List<Day>> getByUserId(final String userId);

    @Query("SELECT COUNT(*) FROM day")
    int count();
}
