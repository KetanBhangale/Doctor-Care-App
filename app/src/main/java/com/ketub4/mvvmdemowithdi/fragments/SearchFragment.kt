package com.ketub4.mvvmdemowithdi.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.activities.BookAppointmentActivity
import com.ketub4.mvvmdemowithdi.adapter.DoctorListAdapter
import com.ketub4.mvvmdemowithdi.adapter.IDoctorListAdapter
import com.ketub4.mvvmdemowithdi.databinding.FragmentHomeBinding
import com.ketub4.mvvmdemowithdi.databinding.FragmentSearchBinding
import com.ketub4.mvvmdemowithdi.model.Doctors
import com.ketub4.mvvmdemowithdi.viewModel.CustomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), IDoctorListAdapter {
    private var _binding: FragmentSearchBinding?=null
    private val binding: FragmentSearchBinding get() = _binding!!
    private val customViewModel: CustomViewModel by activityViewModels()
    lateinit var doctorListAdapter:DoctorListAdapter
    lateinit var doctorList: ArrayList<Doctors>
    private val gson = Gson()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showHideProgressBar()

        customViewModel.getDoctorsList(0)
        GlobalScope.launch(Dispatchers.Main) {
            delay(5000)
            customViewModel.doctorsList.observe(requireActivity(), Observer { list ->
                Log.i("doctors=", "doctors=${list.size}")
                doctorList = list
                if (list.size>0)
                    showHideProgressBar()
            })
            setupRV()
        }

    }

    private fun setupRV() {
        doctorListAdapter = DoctorListAdapter(doctorList, this, requireActivity())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            hasFixedSize()
            adapter = doctorListAdapter
        }

    }

    private fun showHideProgressBar() {
        if (_binding!!.progressBar.visibility== View.VISIBLE){
            _binding!!.progressBar.visibility = View.GONE
        }else{
            _binding!!.progressBar.visibility = View.VISIBLE
        }

    }

    override fun onBookBtnClick(doctor: Doctors) {
        val intent = Intent(requireActivity(), BookAppointmentActivity::class.java)
        val objString = gson.toJson(doctor)
        intent.putExtra("doctorObj",objString)
        startActivity(intent)
    }

}