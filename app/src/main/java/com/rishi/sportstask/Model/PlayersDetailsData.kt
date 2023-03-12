package com.rishi.sportstask.Model

import com.google.gson.annotations.SerializedName

class PlayersDetailsData {

    @SerializedName("Position")
    var Position: String? = null

    @SerializedName("Name_Full")
    var Name_Full: String? = null

    @SerializedName("Iscaptain")
    var Iscaptain: Boolean? = null

    @SerializedName("Iskeeper")
    var Iskeeper: Boolean? = null

    @SerializedName("Batting")
    var battingdata: BattingData? = null

    @SerializedName("Bowling")
    var bowlingdata: BowlingData? = null
}