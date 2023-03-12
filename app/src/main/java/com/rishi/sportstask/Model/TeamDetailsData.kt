package com.rishi.sportstask.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TeamDetailsData {

    @SerializedName("Name_Full")
    val Name_Full: String? = null

    @SerializedName("Name_Short")
    val Name_Short: String? = null

    @SerializedName("Players")
    @Expose
    val playersdetailsdata: Map<String, PlayersDetailsData>? = null

}

