package com.ketub4.mvvmdemowithdi.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.databinding.TimeItemBinding
import com.ketub4.mvvmdemowithdi.model.Appointment

class AppointmentAdapter(private var appointmentList: ArrayList<Appointment>,
                         private val listener:ITimeSlotLitener,
                         private val context:Context):RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    class AppointmentViewHolder(val binding: TimeItemBinding): RecyclerView.ViewHolder(binding.root)

    private var selectedPosition: Int? = -1
    private var selectedTime: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return (AppointmentViewHolder(
            TimeItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        ))
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
            val appointment = appointmentList[position]
        holder.binding.apply {
            appointTime.text = appointment.time

            appointTime.setOnClickListener {
                if (!appointment.booked){
                    if (selectedPosition == holder.adapterPosition){
                        selectedPosition = RecyclerView.NO_POSITION
                        notifyDataSetChanged()
                        selectedTime = ""
                        listener.onTimeSlotClicked(appointment.id, appointment.booked, selectedTime)
                        return@setOnClickListener
                    }
                    selectedTime = appointment.time
                    selectedPosition = holder.adapterPosition
                    notifyDataSetChanged()
                    listener.onTimeSlotClicked(appointment.id, appointment.booked, selectedTime)
                }
                Log.i("selectedTime=","selectedTime="+selectedTime)

            }

            if (selectedPosition==position){
                appointTime.setBackgroundResource(R.drawable.background_shape_green)
                appointTime.setTextColor(Color.WHITE)

            }else{
                appointTime.setBackgroundResource(R.drawable.background_shape_white)
                appointTime.setTextColor(context.resources.getColor(R.color.basicGreen))

            }
            Log.i("appointment.isBooked","selectedTime outside="+selectedTime)
            if (appointment.booked){
                appointTime.setBackgroundResource(R.drawable.background_shape_gray)
                appointTime.setTextColor(Color.BLACK)
                selectedTime = ""
            }

        }
    }

    override fun getItemCount(): Int = appointmentList.size

    fun updateTimeSlotList(newList: ArrayList<Appointment>){
        appointmentList.clear()
        appointmentList = newList
        notifyDataSetChanged()
    }


}

interface ITimeSlotLitener{
    fun onTimeSlotClicked(appointId:Int, isBooked:Boolean, time: String)
}