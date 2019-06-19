package mx.edu.upqroo.kristenandroid.data.database.daos


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact

@Dao
interface ContactDao {

    @get:Query("SELECT * FROM contact")
    val all: LiveData<List<Contact>>

    @get:Query("SELECT * FROM contact")
    val allSync: List<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg contacts: Contact)

    @Delete
    fun delete(vararg contacts: Contact)

    @Query("DELETE FROM contact")
    fun deleteAll()

    @Query("SELECT * FROM contact WHERE contactId = :contactId")
    fun getById(contactId: String): LiveData<Contact>

    @Query("SELECT COUNT(*) FROM contact")
    fun count(): Int
}
