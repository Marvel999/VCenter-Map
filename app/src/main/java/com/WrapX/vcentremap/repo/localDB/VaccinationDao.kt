package com.WrapX.vcentremap.repo.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VaccinationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVaccinationCentre(vaccinationCentre: VaccinationCentre)

    @Query("SELECT * FROM vaccination_centre ORDER BY id ASC")
    fun readAllData():LiveData<List<VaccinationCentre>>
}