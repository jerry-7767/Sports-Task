package com.rishi.sportstask.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.rishi.sportstask.Model.ApiResponse
import com.rishi.sportstask.Model.InnigsData
import com.rishi.sportstask.Model.MatchdetailsData
import com.rishi.sportstask.Model.TeamDetailsData
import com.rishi.sportstask.Retrofit.ApiService
import com.rishi.sportstask.Retrofit.RetrofitBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamViewModel : ViewModel() {

    private var matchdetaild_data = MutableLiveData<MatchdetailsData>()
    private val myMap = MutableLiveData<Map<String, TeamDetailsData>>()
    private var inningsdata = MutableLiveData<List<InnigsData>>()

    fun getCricketapidata() {
        val api = RetrofitBase().getClient()!!.create(ApiService::class.java)
        val call: Call<ApiResponse> =
            api.getIndNz(
            )
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                if (response.body() != null) {

                    matchdetaild_data.value = response.body()!!.matchdetailsdata
                    myMap.value = response.body()!!.data
                    inningsdata.value = response.body()!!.innigsdata

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }

    fun observeMatchdetailsDataLiveData(): LiveData<MatchdetailsData> {
        return matchdetaild_data
    }

    fun observeTeamDetailsData(): LiveData<Map<String, TeamDetailsData>> {
        return myMap
    }

    fun observeInningsLiveData(): LiveData<List<InnigsData>> {
        return inningsdata
    }

}