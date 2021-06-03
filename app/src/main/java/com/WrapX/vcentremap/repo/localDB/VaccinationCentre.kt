package com.WrapX.vcentremap.repo.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccination_centre")
data class VaccinationCentre(
     @PrimaryKey(autoGenerate = true)
     var id:Int,
     var VaccinationCentreName:String,
     var VaccinationCentreAddress:String,
     var Pincode:String,
     var GoogleMapLink:String,
)
