package mx.edu.upqroo.kristenandroid.data.database.daos;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex;

@Dao
public interface KardexDao {
    @Insert
    long insert(Kardex kardex);

    @Update
    void update(Kardex... kardex);

    @Delete
    void delete(Kardex... kardex);

    @Query("DELETE FROM kardex")
    void deleteAll();

    @Query("SELECT * FROM kardex WHERE kardexId = :kardexId")
    LiveData<Kardex> getById(long kardexId);

    @Query("SELECT * FROM kardex")
    LiveData<List<Kardex>> getAll();

    @Query("SELECT * FROM kardex WHERE userId = :userId")
    LiveData<List<Kardex>> getByUserId(final String userId);

    @Query("SELECT COUNT(*) FROM kardex")
    int count();
}
