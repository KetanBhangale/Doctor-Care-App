package com.ketub4.mvvmdemowithdi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.databinding.NotificationItemBinding
import com.ketub4.mvvmdemowithdi.model.Notification

class NotificationAdapter(
    private val notificatioList: ArrayList<Notification>,
    private val context: Context
): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(val binding: NotificationItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            NotificationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
         val notification = notificatioList[position]
        holder.binding.apply {
            notificationTitle.text = notification.title
            notificationText.text = notification.message
            notificationDate.text = notification.date

            val imageName = "cat_"+notification.categoryId
            categoryImage.setImageResource(context.resources.getIdentifier(
                imageName, "drawable", "com.ketub4.mvvmdemowithdi"));

            when(notification.categoryId){
                1->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicGreen))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicGreen))
                    true
                }
                2->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicOrange))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicOrange))
                    true
                }
                3->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicBlue))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicBlue))
                    true
                }
                4->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicGreen))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicGreen))
                    true
                }
                5->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicYellow))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicYellow))
                    true
                }
                6->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicBlue))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicBlue))
                    true
                }
                7->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicGreen))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicGreen))
                    true
                }
                8->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicBlue))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicBlue))
                    true
                }
                10->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicOrange))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicOrange))
                    true
                }
                 else ->{
                    leftBar.setBackgroundColor(context.resources.getColor(R.color.basicPink))
                    rightBar.setBackgroundColor(context.resources.getColor(R.color.basicPink))
                    true
                }
            }

        }
    }

    override fun getItemCount()= notificatioList.size


}