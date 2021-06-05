package com.WrapX.vcentremap.ui.FindVCentre

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.WrapX.vcentremap.repo.firebaseDatabase.GetVCentre
import com.WrapX.vcentremap.repo.localDB.VaccinationCentre
import com.WrapX.vcentremap.repo.localDB.VaccinationDao
import com.WrapX.vcentremap.repo.localDB.VaccinationDataBase
import com.WrapX.vcentremap.repo.localDB.VacinnationCentreRepository
import com.WrapX.vcentremap.repo.model.VCentre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FindVCentreViewModel(application: Application) : AndroidViewModel(application) {

    var dataBasevaccinationCentreList: LiveData<List<VaccinationCentre>>
    private val repositor: VacinnationCentreRepository
    private val getdata = GetVCentre()
    private var _vCList = MutableLiveData<ArrayList<VCentre>>()
    var vCList: LiveData<ArrayList<VCentre>> = _vCList

    init {
        val vaccinationDao: VaccinationDao =
            VaccinationDataBase.getDatabase(application).vaccinationDao()
        repositor = VacinnationCentreRepository(vaccinationDao)
        dataBasevaccinationCentreList = repositor.readAllData()
    }

    fun getData() {
        viewModelScope.launch {
            getdata.getdata {
                _vCList.postValue(it)
            }
        }
    }

    fun addVaccinationCentre(vaccinationCentre: VaccinationCentre) {
        viewModelScope.launch(Dispatchers.IO) {
            repositor.addVaccinationCentre(vaccinationCentre)
        }
    }

     fun getVaccinationcentreData() {
        dataBasevaccinationCentreList = repositor.readAllData()
    }

    fun deleteTable() {
        viewModelScope.launch {
            repositor.deleteTable()
        }
    }


}