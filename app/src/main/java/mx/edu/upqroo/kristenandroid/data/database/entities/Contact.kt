package mx.edu.upqroo.kristenandroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contact")
class Contact(
        @field:PrimaryKey
        @field:ColumnInfo(name = "contactId")
        @field:SerializedName("idContacto")
            var contactId: String,
        @field:ColumnInfo(name = "name")
        @field:SerializedName("nombre")
            var name: String?, @field:ColumnInfo(name = "email")
        @field:SerializedName("correo")
            var email: String?)
