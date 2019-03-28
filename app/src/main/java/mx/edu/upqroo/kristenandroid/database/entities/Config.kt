package mx.edu.upqroo.kristenandroid.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo

class Config(@NonNull @ColumnInfo(name = "generalTopic") val generalTopic: String,
             @NonNull @ColumnInfo(name = "userTopic") val userTopic: String,
             @NonNull @ColumnInfo(name = "baseAddress") val baseAddress: String,
             @NonNull @ColumnInfo(name = "userToken") val userToken: String)
