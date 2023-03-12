package com.rishi.sportstask.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rishi.sportstask.Model.ApiResponse
import com.rishi.sportstask.Model.MatchdetailsData
import com.rishi.sportstask.Retrofit.ApiService
import com.rishi.sportstask.Retrofit.RetrofitBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamViewModel : ViewModel() {


    private var matchdetaild_data = MutableLiveData<MatchdetailsData>()

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

}