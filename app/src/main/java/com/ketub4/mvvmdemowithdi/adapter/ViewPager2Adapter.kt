package com.ketub4.mvvmdemowithdi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketub4.mvvmdemowithdi.databinding.ViewpagerItemBinding

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.model.Appointment






class ViewPager2Adapter(private val context: Context,
                        private val appointmentAdapter: AppointmentAdapter,
                        private val listener: IViewPagerListener):RecyclerView.Adapter<ViewPager2Adapter.PageViewHolder>() {

    class PageViewHolder(val binding: ViewpagerItemBinding): RecyclerView.ViewHolder(binding.root)

    private val timings = arrayOf(
        "Morning", "Afternoon", "Evening"
    )
    private val timeImages = intArrayOf(
        R.drawable.morning,
        R.drawable.afternoon,
        R.drawable.night,

    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return (PageViewHolder(ViewpagerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )))
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {

        holder.binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context,4)
            recyclerView.hasFixedSize()
            recyclerView.adapter = appointmentAdapter
            Log.i("position","position="+position)
            pageTitle.text = timings[position]
            pageTitleImage.setImageResource(timeImages[position])

            previous.setOnClickListener {
                listener.previousPage()
            }
            next.setOnClickListener {
                listener.nextPage()
            }

        }
    }

    override fun getItemCount()=timings.size
}

interface IViewPagerListener{
    fun nextPage()
    fun previousPage()
}