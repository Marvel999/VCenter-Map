package com.WrapX.vcentremap.repo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SloatResponse(
    @Json(name = "sessions")
    var sessions: List<Session>
)