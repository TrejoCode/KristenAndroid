package mx.edu.upqroo.kristenandroid.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice

@Dao
interface NoticeDao {

    @get:Query("SELECT * FROM notice")
    val all: LiveData<List<Notice>>

    @Insert
    fun insert(day: Notice): Long

    @Update
    fun update(vararg days: Notice)

    @Delete
    fun delete(vararg days: Notice)

    @Query("DELETE FROM notice")
    fun deleteAll()

    @Query("SELECT * FROM notice WHERE noticeId = :noticeId")
    fun getById(noticeId: String): LiveData<Notice>
}
