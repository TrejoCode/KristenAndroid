package mx.edu.upqroo.kristenandroid.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex
import mx.edu.upqroo.kristenandroid.data.repositories.KardexRepository

class KardexViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: KardexRepository

    init {
        mRepo = KardexRepository.getInstance(application)
    }

    fun getKardex(userId: String): LiveData<List<Kardex>> {
        return mRepo.getKardex(userId)
    }

    fun updateKardexFromService() {
        mRepo.updateKardexFromService()
    }
}
