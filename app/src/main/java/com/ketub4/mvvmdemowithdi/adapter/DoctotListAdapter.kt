package com.ketub4.mvvmdemowithdi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.databinding.DoctorListItemBinding
import com.ketub4.mvvmdemowithdi.model.Doctors

class DoctorListAdapter(private val doctorList: ArrayList<Doctors>,
                        private val listener: IDoctorListAdapter,
                        private val context: Context):
                        RecyclerView.Adapter<DoctorListAdapter.DoctorListViewHolder>() {

    class DoctorListViewHolder(val binding: DoctorListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorListViewHolder {
        return DoctorListViewHolder(
            DoctorListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoctorListViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.binding.apply {
            categoryName.text = doctor.category
            doctorName.text = doctor.name
            distance.text = "Distance: "+doctor.distance
            address.text = doctor.address
            setRatingView(doctor.rating, holder)

            var imageName = "doc_m2"
            if (doctor.name.contains("Urmila") || doctor.name.contains("Varsha") ||doctor.name.contains("Vasanti"))
            {
                // For demo project this condition is applied. In actual get the profile pic from API.
                imageName = "doc_f1"
            }

            categoryImage.setImageResource(context.resources.getIdentifier(
                imageName, "drawable", "com.ketub4.mvvmdemowithdi"));

            bookBtn.setOnClickListener {
                listener.onBookBtnClick(doctor)
            }

        }
    }



    override fun getItemCount()= doctorList.size

    private fun setRatingView(rating: Int, holder: DoctorListViewHolder) {
        if (rating==1){
            holder.binding.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start)
                rate3.setImageResource(R.drawable.start)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==2){
            holder.binding.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==3){
            holder.binding.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==4){
            holder.binding.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start_filled)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==5){
            holder.binding.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start_filled)
                rate5.setImageResource(R.drawable.start_filled)
            }
        }
    }
}

interface IDoctorListAdapter{
    fun onBookBtnClick(doctor: Doctors)
}