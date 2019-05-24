package mx.edu.upqroo.kristenandroid.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade
import mx.edu.upqroo.kristenandroid.data.repositories.GradeRepository

class GradesViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: GradeRepository = GradeRepository.getInstance(application)

    fun getGrades(userId: String): LiveData<List<Grade>> {
        return mRepo.getGrades(userId)
    }

    fun updateGradesFromService() {
        mRepo.updateGradesFromService()
    }
}
