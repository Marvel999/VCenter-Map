package com.WrapX.vcentremap.repo.localDB

import android.util.Log
import androidx.lifecycle.LiveData

class VacinnationCentreRepository(private val vaccinationDao: VaccinationDao) {

    val  readAllData:LiveData<List<VaccinationCentre>> =vaccinationDao.readAllData()

    suspend fun addVaccinationCentre(vaccinationCentre: VaccinationCentre){
        Log.e("Add","Data In Database")
        vaccinationDao.addVaccinationCentre(vaccinationCentre)
    }

   suspend fun deleteTable(){
        vaccinationDao.deleteTable()
    }
}