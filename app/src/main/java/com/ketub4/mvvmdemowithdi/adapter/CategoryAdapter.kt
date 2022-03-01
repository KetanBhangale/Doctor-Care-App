package com.ketub4.mvvmdemowithdi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ketub4.mvvmdemowithdi.databinding.CateoryItemBinding
import com.ketub4.mvvmdemowithdi.model.Category
import java.util.*
import kotlin.collections.ArrayList

class CategoryAdapter(private val listener: ICategoryListener,
                      private val categoryList: ArrayList<Category>,
                      private val context: Context):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: CateoryItemBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CateoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val category = categoryList[position]
        holder.binding.apply {
            categoryTitle.text = category.name
            subCategoryTitle.text = category.description
            val imageName = "cat_"+category.id
                categoryImage.setImageResource(context.resources.getIdentifier(
                    imageName, "drawable", "com.ketub4.mvvmdemowithdi"));
            categoryLayout.setOnClickListener {
                listener.onCategoryClick(category.id, category.name)
            }


        }
    }

    override fun getItemCount() = categoryList.size
}

interface ICategoryListener{
    fun onCategoryClick(category_id:Int, category_name: String)
}