package com.rishi.sportstask.Model

import com.google.gson.annotations.SerializedName

class BowlingData {

    @SerializedName("Style")
    var Style: String? = null

    @SerializedName("Average")
    var Average: String? = null

    @SerializedName("Economyrate")
    var Economyrate: String? = null

    @SerializedName("Wickets")
    var Wickets: String? = null
}