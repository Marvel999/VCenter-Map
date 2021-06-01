package com.WrapX.vcentremap.repo.SharePrefrance

import android.content.Context
import android.content.SharedPreferences

class UserSharedPreferences(val context: Context) {

    val sharePreferenceName="com.WrapX.vcentremap"
    private val sharePreference : SharedPreferences

    private val userName="NAME";
    private val areaPinCode="PINCODE";
    init {
        sharePreference = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE)
    }

    var name:String?
               get() = sharePreference.getString(userName,"Buddy").toString()
               set(value) {  sharePreference.edit().putString(userName, value).apply()}

    var pincode:String?
             get() = sharePreference.getString(areaPinCode,"110001").toString()
             set(value) {  sharePreference.edit().putString(areaPinCode, value).apply()}

    fun deleteSharePrefance(){
        sharePreference.edit().clear().apply()
    }

}