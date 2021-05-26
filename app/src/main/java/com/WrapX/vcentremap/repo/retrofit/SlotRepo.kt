package com.WrapX.vcentremap.repo.retrofit

import com.WrapX.vcentremap.repo.model.SloatResponse
import retrofit2.Response

class SlotRepo:SlotFeed {
    override suspend fun getFeed(pincode:String, date:String):Response<SloatResponse> = SloatStatusClient.api.getStatus(pincode,date)




}