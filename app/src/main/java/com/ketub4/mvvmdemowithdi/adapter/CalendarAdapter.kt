package com.ketub4.mvvmdemowithdi.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.databinding.CalendarCellBinding
import com.ketub4.mvvmdemowithdi.utils.CalendarUtils
import java.time.LocalDate
import java.util.ArrayList

class CalendarAdapter(private val days: ArrayList<LocalDate?>,
                      private val listener:ICalendarAdapter):RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(val binding: CalendarCellBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return (CalendarViewHolder(CalendarCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )))

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val localDate = days[position]
        if (localDate== null){
            holder.binding.cellDayText.text = ""
        }else{
            holder.binding.cellDayText.text = localDate.dayOfMonth.toString()
            if (localDate == CalendarUtils.selectedDate){
                holder.binding.parentView.setBackgroundResource(R.drawable.background_shape_blue)
                holder.binding.cellDayText.setTextColor(Color.WHITE)
            }
            holder.binding.parentView.setOnClickListener {
                listener.onCalendarItemClicked(holder.adapterPosition, localDate)
            }
        }

    }

    override fun getItemCount() = days.size


}

interface ICalendarAdapter{
    fun onCalendarItemClicked(position: Int, localDate: LocalDate)
}