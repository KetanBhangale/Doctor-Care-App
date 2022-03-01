package com.ketub4.mvvmdemowithdi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ketub4.mvvmdemowithdi.databinding.ActivitySuccessfulBinding

class SuccessfulActivity : AppCompatActivity() {
    private var _binding: ActivitySuccessfulBinding? = null
    private val binding: ActivitySuccessfulBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySuccessfulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _binding?.appBar?.toolbarTitle?.text = "Confirmation"
        _binding?.appBar?.rightIcon?.visibility = View.INVISIBLE
        _binding?.homeBtn?.setOnClickListener {
            launchMainActivity()
        }
        _binding?.appBar?.back?.setOnClickListener {
            launchMainActivity()
        }
    }

    private fun launchMainActivity(){
        val intent = Intent(this@SuccessfulActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}