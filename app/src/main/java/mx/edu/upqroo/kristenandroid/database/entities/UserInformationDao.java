package mx.edu.upqroo.kristenandroid.database.entities;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserInformationDao {
    @Insert
    void insert(UserInformation profile);

    @Query("DELETE FROM user_information")
    void deleteAll();

    @Query("SELECT * FROM user_information WHERE userId = :id")
    LiveData<UserInformation> getUserInformation(@NonNull String id);

    @Query("SELECT * FROM user_information WHERE userId = :id")
    UserInformation getUserInformationNotLive(@NonNull String id);
}
