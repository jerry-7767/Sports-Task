package com.rishi.sportstask.Model

import com.google.gson.annotations.SerializedName

class MatchData {

    @SerializedName("Number")
    var Number: String? = null

    @SerializedName("Date")
    var Date: String? = null

    @SerializedName("Time")
    var Time: String? = null
}