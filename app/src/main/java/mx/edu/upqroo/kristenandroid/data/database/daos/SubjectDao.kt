package mx.edu.upqroo.kristenandroid.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject

@Dao
interface SubjectDao {

    @get:Query("SELECT * FROM subject")
    val all: LiveData<List<Subject>>

    @Insert
    fun insert(subject: Subject)

    @Update
    fun update(vararg subjects: Subject)

    @Delete
    fun delete(vararg subjects: Subject)

    @Query("DELETE FROM subject")
    fun deleteAll()

    @Query("SELECT * FROM subject WHERE subjectId = :subjectId")
    fun getById(subjectId: Long): LiveData<Subject>

    @Query("SELECT * FROM subject WHERE dayId = :dayId")
    fun getByDayId(dayId: Long): LiveData<List<Subject>>
}
