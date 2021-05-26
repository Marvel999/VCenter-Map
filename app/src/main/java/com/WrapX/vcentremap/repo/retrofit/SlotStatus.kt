package com.WrapX.vcentremap.repo.retrofit

import com.WrapX.vcentremap.repo.model.SloatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface SlotStatus {

    @GET("/api/v2/appointment/sessions/public/findByPin")
    suspend fun getStatus(
        @Query("pincode")author:String?=null,
        @Query("date")tag:String?=null,
    ): Response<SloatResponse>

}