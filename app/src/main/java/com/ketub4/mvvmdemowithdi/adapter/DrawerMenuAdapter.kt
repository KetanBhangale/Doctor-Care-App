package com.ketub4.mvvmdemowithdi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ketub4.mvvmdemowithdi.databinding.DrawerMenuItemBinding
import com.ketub4.mvvmdemowithdi.R

class DrawerMenuAdapter(private val listener:IDrawerMenuAdapter):RecyclerView.Adapter<DrawerMenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(val binding: DrawerMenuItemBinding): RecyclerView.ViewHolder(binding.root)

    private val menuList:ArrayList<String> = ArrayList(4)
    private var selectedPosition: Int? = -1
    init {
        menuList.add("Home")
        menuList.add("Search")
        menuList.add("Notifications")
        menuList.add("Profile")

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return (MenuViewHolder(DrawerMenuItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )))
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.binding.apply {
            menuText.text = menu
            when(menu){
                "Home"->{
                    menuImage.setImageResource(R.drawable.home)
                    true
                }
                "Search"->{
                    menuImage.setImageResource(R.drawable.search)
                    true
                }
                "Notifications"->{
                    menuImage.setImageResource(R.drawable.notification)
                    true
                }
                "Profile"->{
                    menuImage.setImageResource(R.drawable.profile)
                    true
                }
                else->{
                    menuImage.setImageResource(R.drawable.home)
                    true
                }
            }

            holder.binding.menuItem.setOnClickListener {
                if (selectedPosition ==  holder.adapterPosition){
                    selectedPosition = RecyclerView.NO_POSITION
                    notifyDataSetChanged()
                    return@setOnClickListener
                }
                selectedPosition = holder.adapterPosition
                notifyDataSetChanged()

                listener.onMenuItemClicked(position)
            }

            if (selectedPosition == position){
                menuItem.setBackgroundResource(R.drawable.background_shape_square_blue)
                menuText.setTextColor(Color.WHITE)
                menuImage.setColorFilter(Color.argb(255, 255, 255, 255));
            }else{
                menuItem.setBackgroundResource(R.drawable.background_shape_white)
                menuText.setTextColor(Color.BLACK)
                menuImage.setColorFilter(Color.argb(0, 0, 0, 0));
            }

        }
    }

    override fun getItemCount()= menuList.size


}

interface IDrawerMenuAdapter{
    fun onMenuItemClicked(id:Int)
}