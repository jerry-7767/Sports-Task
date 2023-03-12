package com.rishi.sportstask.UI

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonObject
import com.rishi.sportstask.R
import com.rishi.sportstask.Retrofit.ApiService
import com.rishi.sportstask.Retrofit.RetrofitBase
import com.rishi.sportstask.ViewModel.TeamViewModel
import com.rishi.sportstask.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TeamViewModel
    var strteam1: String? = null
    var strteam2: String? = null
    val api = RetrofitBase().getClient()!!.create(ApiService::class.java)
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog!!.setCancelable(false)

        showProgressDialog()
        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        viewModel.getCricketapidata()
        viewModel.observeMatchdetailsDataLiveData().observe(this, Observer { it ->
            hideProgressDialog()
            binding.txtWeather.text = it.Weather
            binding.txtStatus.text = it.Status
            binding.txtResult.text = it.Result
            binding.txtUmpire.text = it.officialsdata!!.Umpires
            binding.txtTourname.text = it.seriesdata!!.Tour_Name
            binding.txtVenue.text = it.venuedata!!.Name
            binding.txtDateTime.text = it.matchdata!!.Date + ", " + it.matchdata!!.Time
            strteam1 = it.Team_Away
            strteam2 = it.Team_Home
        })
        viewModel.observeTeamDetailsData().observe(this, Observer {
            hideProgressDialog()
            binding.txtTeamName1.text = it[strteam1]!!.Name_Short
            binding.txtTeamName2.text = it[strteam2]!!.Name_Short

        })
        binding.rlViewTeamPlayersInfo.setOnClickListener {
            val intent = Intent(this@MainActivity, TeamPlayersActivity::class.java)
            startActivity(intent)
        }
    }


    private fun showProgressDialog() {
        if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
            progressDialog!!.setContentView(R.layout.item_loader)
            Objects.requireNonNull<Window>(progressDialog!!.window)
                .setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun hideProgressDialog() {
        if (progressDialog!!.isShowing) progressDialog!!.dismiss()
    }

    override fun attachBaseContext(newBase: Context) {
        val override = Configuration(newBase.resources.configuration)
        override.fontScale = 1.0f
        applyOverrideConfiguration(override)
        super.attachBaseContext(newBase)
    }
}