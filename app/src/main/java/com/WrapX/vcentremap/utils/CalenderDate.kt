package com.WrapX.vcentremap.utils

import androidx.annotation.Keep
import java.util.*

object CalenderDate {
    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val day = c.get(Calendar.DAY_OF_MONTH)

    fun getDate():String{
        return "${day}-${month}-${year}"
    }

}