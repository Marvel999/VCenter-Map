package com.WrapX.vcentremap.repo.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VaccinationCentre::class], version = 1,exportSchema = false)
abstract class VaccinationDataBase:RoomDatabase() {

    abstract fun vaccinationDao():VaccinationDao

    companion object {
        @Volatile
        private var INSTANCE:VaccinationDataBase?=null

        fun getDatabase(context: Context):VaccinationDataBase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return  tempInstance
            }
            synchronized(this)
            {
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    VaccinationDataBase::class.java,
                    "vaccination_database"
                ).build()
                INSTANCE=instance
                return  instance
            }
        }
    }
}