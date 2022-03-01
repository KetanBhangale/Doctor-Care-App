package com.ketub4.mvvmdemowithdi.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.gson.Gson
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.adapter.*
import com.ketub4.mvvmdemowithdi.databinding.ActivityBookAppointmentBinding
import com.ketub4.mvvmdemowithdi.fragments.SearchFragment
import com.ketub4.mvvmdemowithdi.model.Appointment
import com.ketub4.mvvmdemowithdi.model.Doctors
import com.ketub4.mvvmdemowithdi.utils.CalendarUtils
import com.ketub4.mvvmdemowithdi.utils.CalendarUtils.Companion.daysInWeekArray
import com.ketub4.mvvmdemowithdi.viewModel.CustomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@AndroidEntryPoint
class BookAppointmentActivity : AppCompatActivity(), ITimeSlotLitener, IViewPagerListener,
    ICalendarAdapter {
    private var _binding: ActivityBookAppointmentBinding? = null
    private val binding: ActivityBookAppointmentBinding get() = _binding!!
    private val customViewModel: CustomViewModel by viewModels()
    private var category_name: String = ""
    lateinit var appointmentAdapter: AppointmentAdapter
    lateinit var timeslotList: ArrayList<Appointment>
    lateinit var viewPager2Adapter: ViewPager2Adapter
    lateinit var calendarAdapter: CalendarAdapter
    lateinit var sortedfList: ArrayList<Appointment>
    private val gson = Gson()
    private var bookedTimeSlot: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""

        setSupportActionBar(binding.appBar.toolbar)
        binding.appBar.toolbarTitle.text = "Book Appointment"

        _binding?.appBar?.back?.setOnClickListener {
            finish()
        }
        _binding?.appBar?.rightIcon?.visibility = View.INVISIBLE
       val doctorObjString = intent.extras?.get("doctorObj").toString()
        if (doctorObjString.isNotEmpty()){
            setupDoctorDetails(doctorObjString)
        }

        loadTimeSlots("22/02/2022",1, true)//for demo purpose

        binding.viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            // This method is triggered when there is any scrolling activity for the current page
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                //Log.i("onPageScrolled","Scrolling="+position)
            }

            // triggered when you select a new page
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.i("onPageSelected","Scrolling="+position)
                sortedListData(position)

            }

            // triggered when there is
            // scroll state will be changed
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                //Log.i("onPageScrollState","Scrolling="+state)
            }
        })

        binding.nextBtn.setOnClickListener {
            if (bookedTimeSlot.isEmpty()){
                Toast.makeText(this@BookAppointmentActivity, "Please select the Time.", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this@BookAppointmentActivity, AppointmentDetailsActivity::class.java)
                intent.putExtra("doctorObj",doctorObjString)
                startActivity(intent)
            }

        }

    }

    private fun setupDoctorDetails(doctorData:String){
        val doctor = gson.fromJson(doctorData, Doctors::class.java)
        _binding?.calendarView?.doctorDetails?.apply {
            doctorName.text = doctor.name
            categoryName.text = doctor.category
            distance.text = "Distance: "+doctor.distance
            address.text = doctor.address
            categoryImage.setImageResource(this@BookAppointmentActivity.resources.getIdentifier(
                getImageName(doctor.name), "drawable", "com.ketub4.mvvmdemowithdi"));
            setRatingView(doctor.rating)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadTimeSlots(date: String, doctorId: Int, isFirstTime: Boolean){
        showHideProgressBar()
        customViewModel.getTimeSlots(date, doctorId) //For demo this is hard coded.

        GlobalScope.launch(Dispatchers.Main) {
            if (isFirstTime){
                setupWeekCalendar()
            }else{
                sortedfList.clear()
                appointmentAdapter.updateTimeSlotList(sortedfList)
            }
            delay(2000)
            customViewModel.timeSlotList.observe(this@BookAppointmentActivity, Observer { list ->
                Log.i("timeSlotList", "=$list")
                timeslotList = list
                 if (list.size>0)
                     showHideProgressBar()

            })

            setupPageViewer()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupWeekCalendar() {
        Log.i("selectedDate", "selectedDate=" + LocalDate.now())
        binding.calendarView.monthYearTV.text =
            CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate)
        val days: ArrayList<LocalDate?> = CalendarUtils.daysInWeekArray(CalendarUtils.selectedDate)


        calendarAdapter = CalendarAdapter(days, this)

        binding.calendarView.calendarRecyclerView.apply {
            layoutManager = GridLayoutManager(this@BookAppointmentActivity, 7)
            hasFixedSize()
            adapter = calendarAdapter
        }

        binding.calendarView.previousWeek.setOnClickListener {
            val oldDate = CalendarUtils.selectedDate.minusWeeks(1)
            if (oldDate < LocalDate.now()) {
                Toast.makeText(
                    this@BookAppointmentActivity,
                    "You can not select Previous Week.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                CalendarUtils.selectedDate = oldDate
                setupWeekCalendar()
            }


        }
        binding.calendarView.nextWeek.setOnClickListener {

            CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1)
            setupWeekCalendar()
        }
        binding.calendarView.calenderIcon.setOnClickListener {
            val isVisibile = binding.calendarView.calendarMainView.visibility
            Log.i("isVisibile", "isVisibile=" + isVisibile)
            if (isVisibile == View.VISIBLE) {
                binding.calendarView.calendarMainView.visibility = View.GONE
            } else {
                binding.calendarView.calendarMainView.visibility = View.VISIBLE
            }
        }
    }

    private fun setupPageViewer() {
        sortedfList = timeslotList.filter {
            s->s.timeSlot == "m"
        } as ArrayList<Appointment>
        Log.i("morningList="+sortedfList.size,"morningList="+sortedfList.toString())
        appointmentAdapter = AppointmentAdapter(sortedfList,this, this)
        viewPager2Adapter =
            ViewPager2Adapter(this@BookAppointmentActivity, appointmentAdapter,this)
        binding.viewpager.apply {
            adapter = viewPager2Adapter
        }
    }

    override fun onTimeSlotClicked(appointId: Int, isBooked: Boolean, time: String) {
        if (isBooked) {
            Toast.makeText(
                this@BookAppointmentActivity,
                "TimeSlot Already Booked.",
                Toast.LENGTH_SHORT
            )
                .show()
            bookedTimeSlot = ""
        } else {
            bookedTimeSlot = time
        }
    }

    override fun nextPage() {

        binding.viewpager.currentItem = binding.viewpager.currentItem + 1
        val currentItem = binding.viewpager.currentItem
        Log.i("currentItem123","currentItem="+currentItem)
        sortedListData(currentItem)
    }

    override fun previousPage() {
        binding.viewpager.currentItem = binding.viewpager.currentItem - 1
        val currentItem = binding.viewpager.currentItem
        Log.i("currentItem","currentItem="+currentItem)
        sortedListData(currentItem)
    }

    private fun sortedListData(currentPos:Int){
        sortedfList.clear()
        when(currentPos){
            0->{
                sortedfList = timeslotList.filter { s->s.timeSlot == "m" } as ArrayList<Appointment>
                true
            }
            1->{
                sortedfList = timeslotList.filter { s->s.timeSlot == "a" } as ArrayList<Appointment>
                true
            }
            2->{
                sortedfList = timeslotList.filter { s->s.timeSlot == "e" } as ArrayList<Appointment>
                true
            }

        }
        Log.i("morningList="+sortedfList.size,"morningList="+sortedfList.toString())
        appointmentAdapter.updateTimeSlotList(sortedfList)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCalendarItemClicked(position: Int, localDate: LocalDate) {
        if (localDate < LocalDate.now()) {
            Toast.makeText(
                this@BookAppointmentActivity,
                "You can not Select Older dates.",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            CalendarUtils.selectedDate = localDate
            setupWeekCalendar()

            loadTimeSlots("22/02/2022",1, false)//for demo same date and doctor id
        }

    }

    private fun showHideProgressBar() {
        binding.progressBar
        if (binding.progressBar.visibility== View.VISIBLE){
            binding.progressBar.visibility = View.GONE
        }else{
            binding.progressBar.visibility = View.VISIBLE
        }

    }

    private fun getImageName(doctorName:String):String{
        var imageName = "doc_m2" // demo purpose hard coded
        if (doctorName.contains("Urmila") || doctorName.contains("Varsha") ||doctorName.contains("Vasanti"))
        {
            // For demo project this condition is applied. In actual get the profile pic from API.
            imageName = "doc_f1"
        }

        return imageName
    }

    private fun setRatingView(rating: Int) {
        if (rating==1){
            _binding?.calendarView?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start)
                rate3.setImageResource(R.drawable.start)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==2){
            _binding?.calendarView?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==3){
            _binding?.calendarView?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==4){
            _binding?.calendarView?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start_filled)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==5){
            _binding?.calendarView?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start_filled)
                rate5.setImageResource(R.drawable.start_filled)
            }
        }
    }

}

