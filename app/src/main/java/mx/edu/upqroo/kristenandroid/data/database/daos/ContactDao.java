package mx.edu.upqroo.kristenandroid.data.database.daos;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact;

@Dao
public interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Contact contact);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);

    @Query("DELETE FROM contact")
    void deleteAll();

    @Query("SELECT * FROM contact WHERE contactId = :contactId")
    LiveData<Contact> getById(String contactId);

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAll();

    @Query("SELECT * FROM contact")
    List<Contact> getAllSync();

    @Query("SELECT COUNT(*) FROM contact")
    int count();
}
