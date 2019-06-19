package mx.edu.upqroo.kristenandroid.data.models

import androidx.room.Embedded
import androidx.room.Relation
import mx.edu.upqroo.kristenandroid.data.database.entities.Day
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject
import java.util.*
import kotlin.collections.ArrayList

class ScheduleSubject {
    @Embedded
    var day: Day? = null
    @Relation(parentColumn = "dayId", entityColumn = "dayId")
    var subjects: List<Subject> = ArrayList()
}
