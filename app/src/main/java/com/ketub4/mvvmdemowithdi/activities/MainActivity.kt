package com.ketub4.mvvmdemowithdi.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.adapter.DrawerMenuAdapter
import com.ketub4.mvvmdemowithdi.adapter.IDrawerMenuAdapter
import com.ketub4.mvvmdemowithdi.databinding.ActivityMainBinding
import com.ketub4.mvvmdemowithdi.fragments.SearchFragment

import com.ketub4.mvvmdemowithdi.network.FirebaseService
import com.ketub4.mvvmdemowithdi.viewModel.CustomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IDrawerMenuAdapter {
    private var _binding: ActivityMainBinding?=null
    val binding: ActivityMainBinding get() = _binding!!
    private val customViewModel: CustomViewModel by viewModels()
    lateinit var listener: FirebaseService
    private var drawerOpened: Boolean = false
    lateinit var drawerMenuAdapter: DrawerMenuAdapter
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        title=""
        actionBar?.elevation= 0f
        setSupportActionBar(binding.mainAppBar.toolbar)
        binding.mainAppBar.appbarLayout.outlineProvider = null
        binding.mainAppBar.toolbarTitle.text = "Mumbai"

        binding.mainAppBar.home.setOnClickListener {
            toggleDrawerNavigation()
        }
        binding.mainAppBar.homeSearch.setOnClickListener {
            navController.navigate(R.id.searchFragment)
        }

        binding.drawerInnerLayout.backBtn.setOnClickListener {
            toggleDrawerNavigation()
        }
        drawerMenuAdapter = DrawerMenuAdapter(this)
        binding.menuRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            hasFixedSize()
            adapter = drawerMenuAdapter
        }

//        binding.menuItem1.setOnClickListener {
//            binding.menuItem1.setBackgroundResource(R.drawable.background_shape_square_blue)
//            binding.menuText1.setTextColor(Color.WHITE)
//            binding.menuImage1.setColorFilter(Color.argb(255, 255, 255, 255));
//        }
//        binding.menuItem2.setOnClickListener {
//            binding.menuItem2.setBackgroundResource(R.drawable.background_shape_square_blue)
//            binding.menuText2.setTextColor(Color.WHITE)
//            binding.menuImage2.setColorFilter(Color.argb(255, 255, 255, 255));
//        }

       // movieViewModel.getMovieData();
        //movieViewModel.getCategoryList()
//        customViewModel.movieList.observe(this, Observer { list->
//            Log.i("data=","data"+list.size)
//        })

        //movieViewModel.insertRecords()
        //movieViewModel.insertDoctorData()

//        GlobalScope.launch(Dispatchers.Main) {
//            delay(3000)
//            movieViewModel.categoryList.observe(this@MainActivity, Observer { list->
//                Log.i("category=","category"+list.toString())
//            })
//        }





    }



    private fun setupNavigation() {
        val navView: BottomNavigationView = _binding!!.bottomNavigation

        navController = findNavController(R.id.fragmentContainerView)

        navView.setupWithNavController(navController)
    }

    private fun toggleDrawerNavigation() {
        if (!drawerOpened){
            binding.drawerLayout.visibility = View.VISIBLE
            val animate = TranslateAnimation(
                -binding.drawerLayout.width.toFloat(),
                0F,
                0f,
                0F
            )
            animate.duration = 500
            animate.fillAfter = true
            binding.drawerLayout.startAnimation(animate)
            binding.menuRV.visibility = View.VISIBLE
        }else{
            binding.drawerLayout.visibility = View.GONE
            val animate = TranslateAnimation(
                0F,
                -binding.drawerLayout.width.toFloat(),
                0F,
                0F
            )
            animate.duration = 500
            animate.fillAfter = true
            binding.drawerLayout.startAnimation(animate)
            binding.menuRV.visibility = View.GONE

        }
        //binding.drawerLayout.setOnClickListener(null)
        drawerOpened = !drawerOpened
    }

    override fun onMenuItemClicked(id:Int) {
        toggleDrawerNavigation()
        when(id){
            0->{
                navController.navigate(R.id.homeFragment)
                true
            }
            1->{
                navController.navigate(R.id.searchFragment)
                true
            }
            2->{
                navController.navigate(R.id.notificationFragment2)
                true
            }
            3->{
                navController.navigate(R.id.profileFragment)
                true
            }
            else ->{
                navController.navigate(R.id.homeFragment)
                true
            }
        }


    }


}