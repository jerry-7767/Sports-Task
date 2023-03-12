package com.rishi.sportstask.Model

import com.google.gson.annotations.SerializedName

class MatchdetailsData {

    @SerializedName("Team_Home")
    var Team_Home: String? = null

    @SerializedName("Team_Away")
    var Team_Away: String? = null

    @SerializedName("Match")
    var matchdata: MatchData? = null

    @SerializedName("Series")
    var seriesdata: SeriesData? = null

    @SerializedName("Venue")
    var venuedata: VenueData? = null

    @SerializedName("Officials")
    var officialsdata: OfficialsData? = null

    @SerializedName("Weather")
    var Weather: String? = null

    @SerializedName("Tosswonby")
    var Tosswonby: String? = null

    @SerializedName("Status")
    var Status: String? = null

    @SerializedName("Result")
    var Result: String? = null

}