package com.rishi.sportstask.Model

import com.google.gson.annotations.SerializedName

class BattingData {

    @SerializedName("Style")
    var Style: String? = null

    @SerializedName("Average")
    var Average: String? = null

    @SerializedName("Strikerate")
    var Strikerate: String? = null

    @SerializedName("Runs")
    var Runs: String? = null
}