package mx.edu.upqroo.kristenandroid.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import mx.edu.upqroo.kristenandroid.data.database.entities.Day
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject

@Dao
interface DayDao {

    @get:Query("SELECT * FROM day")
    val all: LiveData<List<Day>>

    @Insert
    fun insert(day: Day): Long

    @Update
    fun update(vararg days: Day)

    @Delete
    fun delete(vararg days: Day)

    @Query("DELETE FROM day")
    fun deleteAll()

    @Query("SELECT * FROM day WHERE dayId = :dayId")
    fun getById(dayId: Long): LiveData<Day>

    @Query("SELECT * FROM day WHERE userId = :userId")
    fun getByUserId(userId: String): LiveData<List<Day>>

    @Query("SELECT COUNT(*) FROM day")
    fun count(): Int

    @Transaction
    @Query("SELECT * FROM day WHERE userId = :userId")
    fun getDaysAndSubjectsFromUser(userId: String): LiveData<List<ScheduleSubject>>

    @Transaction
    @Query("SELECT * FROM day WHERE userId = :userId")
    fun getDaysAndSubjectsFromUserSync(userId: String): List<ScheduleSubject>
}
