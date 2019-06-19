package mx.edu.upqroo.kristenandroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.edu.upqroo.kristenandroid.data.models.Config

@Entity(tableName = "user_information")
class UserInformation(@field:ColumnInfo(name = "name")
                        val name: String,
                      @field:ColumnInfo(name = "career")
                        val career: String,
                      @field:ColumnInfo(name = "enrollment")
                        val enrollment: String,
                      @field:ColumnInfo(name = "creditsAccumulated")
                        val creditsAccumulated: String,
                      @field:ColumnInfo(name = "validity")
                        val validity: String,
                      @field:ColumnInfo(name = "entryPeriod")
                        val entryPeriod: String,
                      @field:ColumnInfo(name = "curp")
                        val curp: String,
                      @field:ColumnInfo(name = "dob")
                        val dob: String,
                      @field:ColumnInfo(name = "address")
                        val address: String,
                      @field:ColumnInfo(name = "phone")
                        val phone: String,
                      @field:ColumnInfo(name = "mobilePhone")
                        val mobilePhone: String,
                      @field:ColumnInfo(name = "email")
                        val email: String,
                      @field:PrimaryKey
                      @field:ColumnInfo(name = "userId")
                        val userId: String,
                      @field:ColumnInfo(name = "password")
                        val password: String,
                      @field:Embedded
                        val config: Config)
