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

import com.ketub4.mvvmdemowithdi.CategoryAdapter
import com.ketub4.mvvmdemowithdi.ICategoryListener
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.activities.DoctorListActivity
import com.ketub4.mvvmdemowithdi.databinding.FragmentHomeBinding

import com.ketub4.mvvmdemowithdi.model.Category
import com.ketub4.mvvmdemowithdi.viewModel.CustomViewModel
import com.synnapps.carouselview.ImageListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment(),ICategoryListener {
    private var _binding: FragmentHomeBinding?=null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val customViewModel: CustomViewModel by activityViewModels()
    lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: ArrayList<Category>

    var sampleImages = intArrayOf(
        R.drawable.banner_image1,
        R.drawable.banner_image2,
        R.drawable.banner_image1,
        R.drawable.banner_image2,
        R.drawable.banner_image1,
        R.drawable.banner_image2,
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customViewModel.getCategoryList()
        //customViewModel.insertDoctorData()
        showHideProgressBar()

        //setupRV()

//        customViewModel._categoryList.observe(requireActivity(), Observer { list->
//            Log.i("category=", "category$list")
//            categoryList = list
//            if (list.size>0) {
//                showHideProgressBar()
//                setupRV()
//            }
//        })


        GlobalScope.launch(Dispatchers.Main) {
            delay(1500)
            customViewModel.categoryList.observe(viewLifecycleOwner, Observer { list->
                Log.i("category=", "category$list")
                categoryList = list
                if (list.size>0) {
                    showHideProgressBar()
                    setupRV()
                }
            })


        }

        val imageListener =
            ImageListener { position, imageView -> imageView.setImageResource(sampleImages[position]) }
        _binding!!.carouselView.apply {
            setImageListener(imageListener)
            pageCount = sampleImages.size

        }
    }

    private fun setupRV() {
        categoryAdapter = CategoryAdapter(this,categoryList, requireContext())
        binding.categoryRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
            adapter = categoryAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryClick(category_id:Int, category_name:String) {
        val intent = Intent(requireContext(), DoctorListActivity::class.java)
        intent.putExtra("categoryId", category_id)
        intent.putExtra("categoryName", category_name)
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