package mx.edu.upqroo.kristenandroid.data.database.daos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;

@Dao
public interface NoticeDao {
    @Insert
    long insert(Notice day);

    @Update
    void update(Notice... days);

    @Delete
    void delete(Notice... days);

    @Query("DELETE FROM notice")
    void deleteAll();

    @Query("SELECT * FROM notice WHERE noticeId = :noticeId")
    LiveData<Notice> getById(String noticeId);

    @Query("SELECT * FROM notice")
    LiveData<List<Notice>> getAll();
}
