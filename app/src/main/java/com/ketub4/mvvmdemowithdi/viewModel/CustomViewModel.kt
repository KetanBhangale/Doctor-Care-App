package com.ketub4.mvvmdemowithdi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketub4.mvvmdemowithdi.model.*
import com.ketub4.mvvmdemowithdi.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomViewModel @Inject constructor(private val repository: DataRepository) :ViewModel() {

    private var _movieList =MutableLiveData<List<Result>>()
    val movieList: LiveData<List<Result>> get()= _movieList

    var _categoryList =MutableLiveData<ArrayList<Category>>()
    val categoryList: LiveData<ArrayList<Category>> get()= _categoryList

    private var _doctorsList =MutableLiveData<ArrayList<Doctors>>()
    val doctorsList: LiveData<ArrayList<Doctors>> get()= _doctorsList

    private var _notificationsList =MutableLiveData<ArrayList<Notification>>()
    val notificationsList: LiveData<ArrayList<Notification>> get()= _notificationsList

    private var _timeSlotList =MutableLiveData<ArrayList<Appointment>>()
    val timeSlotList: LiveData<ArrayList<Appointment>> get()= _timeSlotList

    fun getMovieData(){
        viewModelScope.launch {
            _movieList.postValue(repository.getMovieList().results)
        }
    }

    fun insertRecords(){
        viewModelScope.launch {
            repository.insertData()
        }
    }
    fun insertDoctorData(){
        viewModelScope.launch {
            repository.insertDoctorData()
        }
    }

    fun getCategoryList(){
        //viewModelScope.launch(Dispatchers.Main) {
        _categoryList.postValue(repository.getCategoryList())
            //_categoryList.value =
            //_categoryList.value = repository.getCategoryList()
        //}
    }

    fun getDoctorsList(category_id: Int){
        viewModelScope.launch {
            _doctorsList.postValue(repository.getDoctorList(category_id))
        }
    }

    fun getNotifications(){
        viewModelScope.launch {
            _notificationsList.postValue(repository.getNotifications())
        }
    }

    fun getTimeSlots(date:String, doctorId: Int){
        viewModelScope.launch {
            _timeSlotList.postValue(repository.getTimeSlots(date, doctorId))
        }
    }
}