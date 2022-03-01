package com.ketub4.mvvmdemowithdi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.gson.Gson
import com.ketub4.mvvmdemowithdi.R
import com.ketub4.mvvmdemowithdi.databinding.ActivityAppointmentDetailsBinding
import com.ketub4.mvvmdemowithdi.model.Doctors
import java.util.regex.Matcher
import java.util.regex.Pattern


class AppointmentDetailsActivity : AppCompatActivity() {
    private var _binding: ActivityAppointmentDetailsBinding? = null
    private val binding: ActivityAppointmentDetailsBinding get() = _binding!!
    private val VALID_EMAIL_ADDRESS_REGEX: Pattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAppointmentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.appBar.toolbarTitle.text = "Personal Details"

        _binding?.appBar?.back?.setOnClickListener {
            finish()
        }
        _binding?.appBar?.rightIcon?.visibility = View.INVISIBLE

        val doctorObjString = intent.extras?.get("doctorObj").toString()
        if (doctorObjString.isNotEmpty()){
            setupDoctorDetails(doctorObjString)
        }

        binding.DoneBtn.setOnClickListener {
            if (validateInputs()){
                val intent = Intent(this@AppointmentDetailsActivity, SuccessfulActivity::class.java)
                startActivity(intent)
            }
        }


    }

    private fun setupDoctorDetails(doctorData:String){
        val doctor = gson.fromJson(doctorData, Doctors::class.java)
        _binding?.doctorDetails?.apply {
            doctorName.text = doctor.name
            categoryName.text = doctor.category
            distance.text = "Distance: "+doctor.distance
            address.text = doctor.address
            categoryImage.setImageResource(this@AppointmentDetailsActivity.resources.getIdentifier(
                getImageName(doctor.name), "drawable", "com.ketub4.mvvmdemowithdi"));
            setRatingView(doctor.rating)
        }
    }

    fun validateInputs():Boolean{
        if (binding.name.text.isEmpty()){
            binding.name.error = "Enter your Name"
           // Toast.makeText(this, "Enter Your Name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.mobile.text.isEmpty()){
            binding.mobile.error = "Enter Your Phone Number"
            //Toast.makeText(this, "Enter Your Phone Number", Toast.LENGTH_SHORT).show()
            return false
        }else if (binding.mobile.text.length != 10){
            binding.mobile.error = "Enter Your 10 Digit Phone Number"
            //Toast.makeText(this, "Enter Your 10 Digit Phone Number", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.email.text.isEmpty()){
            binding.email.error = "Enter Your Email Id"
            //Toast.makeText(this, "Enter Your Email Id", Toast.LENGTH_SHORT).show()
            return false
        }else if (!validateEmail(binding.email.text.toString())){
            binding.email.error = "Enter Valid Email Id"
            //Toast.makeText(this, "Enter Valid Email Id", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.comments.text.isEmpty()){
            binding.comments.error = "Enter details of the illness"
            //Toast.makeText(this, "Enter details of the illness", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }



    fun validateEmail(emailStr: String): Boolean {
        val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
        return matcher.find()
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
            _binding?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start)
                rate3.setImageResource(R.drawable.start)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==2){
            _binding?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==3){
            _binding?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==4){
            _binding?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start_filled)
                rate5.setImageResource(R.drawable.start)
            }
        }
        if (rating==5){
            _binding?.doctorDetails?.apply {
                rate1.setImageResource(R.drawable.start_filled)
                rate2.setImageResource(R.drawable.start_filled)
                rate3.setImageResource(R.drawable.start_filled)
                rate4.setImageResource(R.drawable.start_filled)
                rate5.setImageResource(R.drawable.start_filled)
            }
        }
    }
}