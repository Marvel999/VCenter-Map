package com.WrapX.vcentremap.repo.retrofit

import com.WrapX.vcentremap.repo.model.SloatResponse
import retrofit2.Response

interface SlotFeed {
     suspend fun getFeed(pincode:String,date:String): Response<SloatResponse>
}