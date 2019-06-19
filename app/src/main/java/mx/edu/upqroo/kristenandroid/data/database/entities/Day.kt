package mx.edu.upqroo.kristenandroid.data.database.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "day",
        indices = [Index("userId")],
        foreignKeys = [ForeignKey(
                entity = UserInformation::class,
                parentColumns = ["userId"],
                childColumns = ["userId"],
                onDelete = CASCADE)])
class Day(@field:PrimaryKey(autoGenerate = true)
          @field:ColumnInfo(name = "dayId")
            val dayId: Long,
          @field:ColumnInfo(name = "name")
            val name: String,
          @field:ColumnInfo(name = "userId")
            val userId: String)
