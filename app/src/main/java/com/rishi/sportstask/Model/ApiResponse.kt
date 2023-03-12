package com.rishi.sportstask.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("Matchdetail")
    var matchdetailsdata: MatchdetailsData? = null

    @SerializedName("Innings")
    var innigsdata: List<InnigsData>? = null

    @SerializedName("Teams")
    @Expose
    val data: Map<String, TeamDetailsData>? = null

}