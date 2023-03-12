package com.rishi.sportstask.UI

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rishi.sportstask.Model.BatsmenDatalist
import com.rishi.sportstask.Model.InnigsData
import com.rishi.sportstask.R
import com.rishi.sportstask.Retrofit.ApiService
import com.rishi.sportstask.Retrofit.RetrofitBase
import com.rishi.sportstask.ViewModel.TeamViewModel
import com.rishi.sportstask.databinding.ActivityTeamPlayersBinding
import com.rishi.sportstask.databinding.RowPlayerListBinding
import java.util.*
import kotlin.coroutines.coroutineContext

class TeamPlayersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamPlayersBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var teamplayeradapter: TeamplayerAdapter

    var strteam1: String? = null
    var strteam2: String? = null
    val api = RetrofitBase().getClient()!!.create(ApiService::class.java)
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this@TeamPlayersActivity)
        progressDialog!!.setCancelable(false)

        showProgressDialog()
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        viewModel.getCricketapidata()
        viewModel.observeInningsLiveData().observe(this, androidx.lifecycle.Observer {
            hideProgressDialog()
            teamplayeradapter.setInningslist(it[0].batsmenlistdata)
        })
    }

    private fun prepareRecyclerView() {
        teamplayeradapter = TeamplayerAdapter()
        binding.recyclerTeam1.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = teamplayeradapter
        }
    }

    class TeamplayerAdapter : RecyclerView.Adapter<TeamplayerAdapter.ViewHolder>() {
        private var inningslist = ArrayList<BatsmenDatalist>()
        fun setInningslist(inningslist: List<BatsmenDatalist>?) {
            this.inningslist = inningslist as ArrayList<BatsmenDatalist>
            notifyDataSetChanged()
        }

        class ViewHolder(val binding: RowPlayerListBinding) :
            RecyclerView.ViewHolder(binding.root) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                RowPlayerListBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.binding.txtPlayerName.text =
                inningslist[position].Batsman

        }

        override fun getItemCount(): Int {
            return inningslist.size
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