package com.WrapX.vcentremap.repo.SharePrefrance

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class UserSharedPreferences(val context: Context) {

    val sharePreferenceName="com.WrapX.vcentremap"
    private val sharePreference : SharedPreferences
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    private val userName="NAME";
    private val areaPinCode="PINCODE";
    private val dateMinOne="DATE";
    init {
        sharePreference = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE)
    }

    var name:String?
               get() = sharePreference.getString(userName,"").toString()
               set(value) {  sharePreference.edit().putString(userName, value).apply()}

    var pincode:String?
             get() = sharePreference.getString(areaPinCode,"110001").toString()
             set(value) {  sharePreference.edit().putString(areaPinCode, value).apply()}

    var date:String?
             get() = sharePreference.getString(dateMinOne,"${day-1}-${month}-${year}").toString()
             set(value) {  sharePreference.edit().putString(dateMinOne, value).apply()}

    fun deleteSharePrefance(){
        sharePreference.edit().clear().apply()
    }

}