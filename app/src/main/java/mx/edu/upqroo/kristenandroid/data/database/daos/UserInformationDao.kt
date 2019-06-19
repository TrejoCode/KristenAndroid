package mx.edu.upqroo.kristenandroid.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation

@Dao
interface UserInformationDao {
    @Insert
    fun insert(profile: UserInformation)

    @Query("DELETE FROM user_information")
    fun deleteAll()

    @Query("SELECT * FROM user_information WHERE userId = :id")
    fun getUserInformation(id: String): LiveData<UserInformation>

    @Query("SELECT * FROM user_information WHERE userId = :id")
    fun getUserInformationNotLive(id: String): UserInformation
}
