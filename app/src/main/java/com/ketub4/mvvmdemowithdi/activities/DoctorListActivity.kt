package com.ketub4.mvvmdemowithdi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.adapter.DoctorListAdapter
import com.ketub4.mvvmdemowithdi.adapter.IDoctorListAdapter
import com.ketub4.mvvmdemowithdi.databinding.ActivityDoctorListBinding
import com.ketub4.mvvmdemowithdi.databinding.ActivityMainBinding
import com.ketub4.mvvmdemowithdi.model.Doctors
import com.ketub4.mvvmdemowithdi.viewModel.CustomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoctorListActivity : AppCompatActivity(), IDoctorListAdapter {
    private var _binding: ActivityDoctorListBinding?=null
    private val binding: ActivityDoctorListBinding get() = _binding!!
    private val customViewModel: CustomViewModel by viewModels()
    private var category_id:Int = 0
    private var category_name: String=""
    lateinit var doctorListAdapter:DoctorListAdapter
    lateinit var doctorList: ArrayList<Doctors>
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDoctorListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        category_id = intent.extras!!.getInt("categoryId")
        category_name = intent.extras!!.getString("categoryName")!!
        binding.appBarMain.toolbarTitle.text = category_name+" Doctors List"
        showHideProgressBar()
        customViewModel.getDoctorsList(category_id)

        GlobalScope.launch(Dispatchers.Main) {
            delay(1500)
            customViewModel.doctorsList.observe(this@DoctorListActivity, Observer { list->
                Log.i("DoctorList", "=$list")
                doctorList = list
                if (list.size>0)
                    showHideProgressBar()

            })
            setupRV()
        }

        binding.appBarMain.back.setOnClickListener {
            this.finish()
        }



    }

    private fun setupRV() {
        doctorListAdapter = DoctorListAdapter(doctorList, this, this@DoctorListActivity)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DoctorListActivity)
            hasFixedSize()
            adapter = doctorListAdapter
        }

    }

    override fun onBookBtnClick(doctor: Doctors) {
        val intent = Intent(this, BookAppointmentActivity::class.java)
        val objString = gson.toJson(doctor)
        intent.putExtra("doctorObj",objString)
        startActivity(intent)
    }

    private fun showHideProgressBar() {
        if (_binding!!.proressBar.visibility== View.VISIBLE){
            _binding!!.proressBar.visibility = View.GONE
        }else{
            _binding!!.proressBar.visibility = View.VISIBLE
        }

    }
}