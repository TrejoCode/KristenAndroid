package mx.edu.upqroo.kristenandroid.data.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName

@Entity(tableName = "kardex",
        indices = [Index("userId")],
        foreignKeys = [ForeignKey(entity = UserInformation::class,
                parentColumns = ["userId"],
                childColumns = ["userId"],
                onDelete = CASCADE)])
class Kardex(@field:PrimaryKey(autoGenerate = true)
             @field:ColumnInfo(name = "kardexId")
                val kardexId: Long,
             @field:SerializedName("nombre_mat")
             @field:ColumnInfo(name = "subject")
                val subject: String,
             @field:SerializedName("calificacion")
             @field:ColumnInfo(name = "grade")
                val grade: String,
             @field:SerializedName("cuatrimestre")
             @field:ColumnInfo(name = "quarter")
                val quarter: String,
             @field:SerializedName("matricula")
             @field:ColumnInfo(name = "userId")
                val userId: String)
