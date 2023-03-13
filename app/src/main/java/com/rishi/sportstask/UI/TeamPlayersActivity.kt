package com.rishi.sportstask.UI

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rishi.sportstask.Model.BatsmenDatalist
import com.rishi.sportstask.Model.PlayersDetailsData
import com.rishi.sportstask.R
import com.rishi.sportstask.Retrofit.ApiService
import com.rishi.sportstask.Retrofit.RetrofitBase
import com.rishi.sportstask.ViewModel.TeamViewModel
import com.rishi.sportstask.databinding.ActivityTeamPlayersBinding
import com.rishi.sportstask.databinding.RowPlayerListBinding
import java.util.*

class TeamPlayersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamPlayersBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var teamplayeradapter: TeamplayerAdapter
    private lateinit var teamplayeradapter1: TeamplayerAdapter1
    var myMap = MutableLiveData<Map<String, PlayersDetailsData>>()
    var myMap1 = MutableLiveData<Map<String, PlayersDetailsData>>()

    var strteam1: String? = null
    var strteam2: String? = null
    var strteam1_name: String? = null
    var strteam2_name: String? = null
    val api = RetrofitBase().getClient()!!.create(ApiService::class.java)
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this@TeamPlayersActivity)
        progressDialog!!.setCancelable(false)

        if (intent != null) {
            strteam1 = intent.getStringExtra("team1")
            strteam2 = intent.getStringExtra("team2")
            strteam1_name = intent.getStringExtra("team1_name")
            strteam2_name = intent.getStringExtra("team2_name")
        }

        showProgressDialog()
        prepareRecyclerView()
        prepareRecyclerView2()
        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        viewModel.getCricketapidata()
        viewModel.observeInningsLiveData().observe(this, androidx.lifecycle.Observer {
            hideProgressDialog()
            teamplayeradapter.setInningslist(it[0].batsmenlistdata)
            teamplayeradapter1.setInningslist(it[1].batsmenlistdata)
        })
        viewModel.observeTeamDetailsData().observe(this, androidx.lifecycle.Observer {
            hideProgressDialog()

            myMap.value = it[strteam1]?.playersdetailsdata
            myMap1.value = it[strteam2]?.playersdetailsdata

        })

        binding.txtTeam1.text = strteam1_name
        binding.txtTeam2.text = strteam2_name

    }

    private fun prepareRecyclerView() {
        teamplayeradapter = TeamplayerAdapter(this)
        binding.recyclerTeam1.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = teamplayeradapter
        }
    }

    private fun prepareRecyclerView2() {
        teamplayeradapter1 = TeamplayerAdapter1(this)
        binding.recyclerTeam2.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = teamplayeradapter1
        }
    }

    class TeamplayerAdapter(val teamPlayersActivity: TeamPlayersActivity) :
        RecyclerView.Adapter<TeamplayerAdapter.ViewHolder>() {

        private var inningslist = ArrayList<BatsmenDatalist>()

        fun setInningslist(inningslist: List<BatsmenDatalist>?) {
            this.inningslist = inningslist as ArrayList<BatsmenDatalist>
            notifyDataSetChanged()
        }

        class ViewHolder(val binding: RowPlayerListBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }

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
                (teamPlayersActivity as TeamPlayersActivity).myMap.value?.get(inningslist[position].Batsman)?.Name_Full


            if ((teamPlayersActivity as TeamPlayersActivity).myMap.value?.get(inningslist[position].Batsman)?.Iscaptain == true) {
                holder.binding.txtPlayerWcCpt.text = "  (C)  "
            }
            if ((teamPlayersActivity as TeamPlayersActivity).myMap.value?.get(inningslist[position].Batsman)?.Iskeeper == true) {
                holder.binding.txtPlayerWcCpt.text = "  (WC)  "
            }

            holder.itemView.setOnClickListener {
                Toast.makeText(
                    teamPlayersActivity,
                    "Batting Style :- " + (teamPlayersActivity as TeamPlayersActivity).myMap.value?.get(inningslist[position].Batsman)?.battingdata!!.Style
                    + "\n" + "Bowling Style :- " +(teamPlayersActivity as TeamPlayersActivity).myMap.value?.get(inningslist[position].Batsman)?.bowlingdata!!.Style,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun getItemCount(): Int {
            return inningslist.size
        }

    }


    class TeamplayerAdapter1(val teamPlayersActivity: TeamPlayersActivity) :
        RecyclerView.Adapter<TeamplayerAdapter1.ViewHolder>() {

        private var inningslist = ArrayList<BatsmenDatalist>()

        fun setInningslist(inningslist: List<BatsmenDatalist>?) {
            this.inningslist = inningslist as ArrayList<BatsmenDatalist>
            notifyDataSetChanged()
        }

        class ViewHolder(val binding: RowPlayerListBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }

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
                (teamPlayersActivity as TeamPlayersActivity).myMap1.value?.get(inningslist[position].Batsman)?.Name_Full

            if ((teamPlayersActivity as TeamPlayersActivity).myMap1.value?.get(inningslist[position].Batsman)?.Iscaptain == true) {
                holder.binding.txtPlayerWcCpt.text = "  (C)  "
            }
            if ((teamPlayersActivity as TeamPlayersActivity).myMap1.value?.get(inningslist[position].Batsman)?.Iskeeper == true) {
                holder.binding.txtPlayerWcCpt.text = "  (WC)  "
            }

            holder.itemView.setOnClickListener {
                Toast.makeText(
                    teamPlayersActivity,
                    "Batting Style :- " + (teamPlayersActivity as TeamPlayersActivity).myMap1.value?.get(inningslist[position].Batsman)?.battingdata!!.Style
                            + "\n" + "Bowling Style :- " +(teamPlayersActivity as TeamPlayersActivity).myMap1.value?.get(inningslist[position].Batsman)?.bowlingdata!!.Style,
                    Toast.LENGTH_SHORT
                ).show()
            }

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