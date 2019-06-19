package mx.edu.upqroo.kristenandroid.data.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "subject",
        indices = [Index("dayId")],
        foreignKeys = [ForeignKey(entity = Day::class,
                parentColumns = ["dayId"],
                childColumns = ["dayId"],
                onDelete = CASCADE)])
class Subject(@field:PrimaryKey(autoGenerate = true)
              @field:ColumnInfo(name = "subjectId")
                val subjectId: Long,
              @field:ColumnInfo(name = "name")
                val name: String,
              @field:ColumnInfo(name = "professor")
                val professor: String,
              @field:ColumnInfo(name = "time")
                val time: String,
              @field:ColumnInfo(name = "dayId")
                val dayId: Long)
