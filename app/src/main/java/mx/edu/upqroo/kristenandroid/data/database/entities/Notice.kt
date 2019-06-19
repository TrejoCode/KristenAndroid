package mx.edu.upqroo.kristenandroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "notice")
class Notice(@field:PrimaryKey
             @field:SerializedName("idAvisos")
             @field:ColumnInfo(name = "noticeId")
                var noticeId: String,
             @field:SerializedName("titulo")
             @field:ColumnInfo(name = "title")
                var title: String?,
             @field:SerializedName("cuerpo")
             @field:ColumnInfo(name = "body")
                var body: String?,
             @field:SerializedName("fecha")
             @field:ColumnInfo(name = "date")
                var date: Date?,
             @field:SerializedName("idUsuarios")
             @field:ColumnInfo(name = "userId")
                var userId: String?,
             @field:SerializedName("idCarrera")
             @field:ColumnInfo(name = "careerId")
                var careerId: Int)
