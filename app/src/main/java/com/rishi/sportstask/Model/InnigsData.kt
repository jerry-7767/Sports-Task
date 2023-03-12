package com.rishi.sportstask.Model

import com.google.gson.annotations.SerializedName

class InnigsData {

    @SerializedName("Number")
    val Number: String? = null

    @SerializedName("Batsmen")
    val batsmenlistdata: List<BatsmenDatalist>? = null

    @SerializedName("Bowlers")
    val bowlerlistdata: List<BowlerDatalist>? = null
}