package mx.edu.upqroo.kristenandroid.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade

@Dao
interface GradeDao {

    @get:Query("SELECT * FROM grade")
    val all: LiveData<List<Grade>>

    @Insert
    fun insert(grade: Grade): Long

    @Update
    fun update(vararg grade: Grade)

    @Delete
    fun delete(vararg grade: Grade)

    @Query("DELETE FROM grade")
    fun deleteAll()

    @Query("SELECT * FROM grade WHERE gradeId = :gradeId")
    fun getById(gradeId: Long): LiveData<Grade>

    @Query("SELECT * FROM grade WHERE userId = :userId")
    fun getByUserId(userId: String): LiveData<List<Grade>>

    @Query("SELECT COUNT(*) FROM grade")
    fun count(): Int
}
