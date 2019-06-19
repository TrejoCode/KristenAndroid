package mx.edu.upqroo.kristenandroid.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex

@Dao
interface KardexDao {

    @get:Query("SELECT * FROM kardex")
    val all: LiveData<List<Kardex>>

    @Insert
    fun insert(kardex: Kardex): Long

    @Update
    fun update(vararg kardex: Kardex)

    @Delete
    fun delete(vararg kardex: Kardex)

    @Query("DELETE FROM kardex")
    fun deleteAll()

    @Query("SELECT * FROM kardex WHERE kardexId = :kardexId")
    fun getById(kardexId: Long): LiveData<Kardex>

    @Query("SELECT * FROM kardex WHERE userId = :userId")
    fun getByUserId(userId: String): LiveData<List<Kardex>>

    @Query("SELECT COUNT(*) FROM kardex")
    fun count(): Int
}
