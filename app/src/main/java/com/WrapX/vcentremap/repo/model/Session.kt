package com.WrapX.vcentremap.repo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Session(
    @Json(name = "address")
    var address: String,
    @Json(name = "available_capacity")
    var availableCapacity: Int,
    @Json(name = "available_capacity_dose1")
    var availableCapacityDose1: Int,
    @Json(name = "available_capacity_dose2")
    var availableCapacityDose2: Int,
    @Json(name = "block_name")
    var blockName: String,
    @Json(name = "center_id")
    var centerId: Int,
    @Json(name = "date")
    var date: String,
    @Json(name = "district_name")
    var districtName: String,
    @Json(name = "fee")
    var fee: String,
    @Json(name = "fee_type")
    var feeType: String,
    @Json(name = "from")
    var from: String,
    @Json(name = "lat")
    var lat: Int,
    @Json(name = "long")
    var long: Int,
    @Json(name = "min_age_limit")
    var minAgeLimit: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "pincode")
    var pincode: Int,
    @Json(name = "session_id")
    var sessionId: String,
    @Json(name = "slots")
    var slots: List<String>,
    @Json(name = "state_name")
    var stateName: String,
    @Json(name = "to")
    var to: String,
    @Json(name = "vaccine")
    var vaccine: String
)