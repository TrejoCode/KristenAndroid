package mx.edu.upqroo.kristenandroid.database.entities;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DayDao {

    @Insert
    void insert(Day day);

    @Update
    void update(Day... days);

    @Delete
    void delete(Day... days);

    @Query("DELETE FROM day")
    void deleteAll();

    @Query("SELECT * FROM day WHERE dayId = :dayId")
    LiveData<Day> getDayById(int dayId);

    @Query("SELECT * FROM day")
    LiveData<List<Day>> getAllDays();

    @Query("SELECT * FROM day WHERE userId = :userId")
    LiveData<List<Day>> getDaysByUserId(final String userId);
}
