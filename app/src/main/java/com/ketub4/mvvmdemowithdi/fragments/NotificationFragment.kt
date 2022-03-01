package com.ketub4.mvvmdemowithdi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.adapter.DoctorListAdapter
import com.ketub4.mvvmdemowithdi.adapter.NotificationAdapter
import com.ketub4.mvvmdemowithdi.databinding.FragmentNotificationBinding
import com.ketub4.mvvmdemowithdi.model.Notification
import com.ketub4.mvvmdemowithdi.viewModel.CustomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding?=null
    private val binding: FragmentNotificationBinding get() = _binding!!
    private val customViewModel: CustomViewModel by activityViewModels()
    lateinit var notificationAdapter: NotificationAdapter
    lateinit var notificationList: ArrayList<Notification>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showHideProgressBar()

        customViewModel.getNotifications()
        GlobalScope.launch(Dispatchers.Main) {
            delay(1500)
            customViewModel.notificationsList.observe(requireActivity(), Observer { list ->
                Log.i("doctors=", "doctors=${list.size}")
                notificationList = list
                if (list.size>0)
                    showHideProgressBar()
            })
            setupRV()
        }

    }

    private fun setupRV() {
        notificationAdapter = NotificationAdapter(notificationList, requireActivity())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            hasFixedSize()
            adapter = notificationAdapter
        }

    }

    private fun showHideProgressBar() {
        if (_binding!!.progressBar.visibility== View.VISIBLE){
            _binding!!.progressBar.visibility = View.GONE
        }else{
            _binding!!.progressBar.visibility = View.VISIBLE
        }

    }

}