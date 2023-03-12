package com.rishi.sportstask.Model

import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("Matchdetail")
    var matchdetailsdata: MatchdetailsData? = null

    @SerializedName("Innings")
    var innigsdata: List<InnigsData>? = null

    @SerializedName("Teams")
    var teamsdata: TeamsData? = null
}