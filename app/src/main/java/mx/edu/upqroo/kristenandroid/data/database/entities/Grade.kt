package mx.edu.upqroo.kristenandroid.data.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName

@Entity(tableName = "grade",
        indices = [Index("userId")],
        foreignKeys = [ForeignKey(entity = UserInformation::class,
                parentColumns = ["userId"],
                childColumns = ["userId"],
                onDelete = CASCADE)])
class Grade(@field:PrimaryKey(autoGenerate = true)
            @field:ColumnInfo(name = "gradeId")
                val gradeId: Long,
            @field:SerializedName("grupo")
            @field:ColumnInfo(name = "code")
                val code: String,
            @field:SerializedName("nombre_mat")
            @field:ColumnInfo(name = "subject")
                val subject: String,
            @field:SerializedName("calificacion")
            @field:ColumnInfo(name = "generalGrade")
                val generalGrade: String,
            @field:SerializedName("parcial_1")
            @field:ColumnInfo(name = "gradeOne")
                val gradeOne: String,
            @field:SerializedName("parcial_2")
            @field:ColumnInfo(name = "gradeTwo")
                val gradeTwo: String,
            @field:SerializedName("parcial_3")
            @field:ColumnInfo(name = "gradeThree")
                val gradeThree: String,
            @field:SerializedName("parcial_4")
            @field:ColumnInfo(name = "gradeFour")
                val gradeFour: String,
            @field:SerializedName("parcial_5")
            @field:ColumnInfo(name = "gradeFive")
                val gradeFive: String,
            @field:SerializedName("matricula")
            @field:ColumnInfo(name = "userId")
                val userId: String)
