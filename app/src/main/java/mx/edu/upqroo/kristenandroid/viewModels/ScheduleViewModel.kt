package mx.edu.upqroo.kristenandroid.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject
import mx.edu.upqroo.kristenandroid.data.repositories.DayRepository
import mx.edu.upqroo.kristenandroid.data.repositories.SubjectRepository

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {
    private val mDayRepository: DayRepository = DayRepository.getInstance(application)
    private val mSubjectRepository: SubjectRepository = SubjectRepository.getInstance(application)

    val subjects: LiveData<List<Subject>>
        get() = mSubjectRepository.all

    fun getDays(userId: String): LiveData<List<ScheduleSubject>> {
        return mDayRepository.getDaysByUserId(userId)
    }

    fun updateScheduleFromService() {
        mDayRepository.updateScheduleFromService()
    }

    fun getSubjects(dayId: Long): LiveData<List<Subject>> {
        return mSubjectRepository.getSubjectsByDayId(dayId)
    }
}
