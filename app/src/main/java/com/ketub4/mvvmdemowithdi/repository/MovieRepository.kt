package com.ketub4.mvvmdemowithdi.repository

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ketub4.mvvmdemowithdi.model.*
import com.ketub4.mvvmdemowithdi.network.APIService
import com.ketub4.mvvmdemowithdi.network.FirebaseService
import javax.inject.Inject


class MovieRepository @Inject constructor(private val apiService: APIService,
                                          private val firebaseDB: FirebaseFirestore,
                                          private val context: Context

) {



    suspend fun getMovieList():MovieResult{
        return apiService.getListofMovies()
    }

    fun insertData(){
        firebaseDB.collection("Categories")
            .add(Category(8,"Nutritionist  ","Be healthy"))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }

    }
    fun insertDoctorData(){

        firebaseDB.collection("TimeSlots")
            .add(Appointment(1,1,1,"m","22/02/2022","9.30",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(2,1,1,"m","22/02/2022","9.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(3,1,1,"m","22/02/2022","10.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(4,1,1,"m","22/02/2022","10.15",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(5,1,1,"m","22/02/2022","10.30",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(6,1,1,"m","22/02/2022","10.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(7,1,1,"m","22/02/2022","11.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(8,1,1,"m","22/02/2022","11.15",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(9,1,1,"m","22/02/2022","11.30",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }

        firebaseDB.collection("TimeSlots")
            .add(Appointment(10,1,1,"m","22/02/2022","11.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(11,1,1,"m","22/02/2022","12.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }

        ////////////////////////////////////////////////////////////////////

        firebaseDB.collection("TimeSlots")
            .add(Appointment(12,1,1,"a","22/02/2022","12.15",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(13,1,1,"a","22/02/2022","12.30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(14,1,1,"a","22/02/2022","12.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(15,1,1,"a","22/02/2022","01.00",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(16,1,1,"a","22/02/2022","01.15",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(17,1,1,"a","22/02/2022","01.30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(18,1,1,"a","22/02/2022","01.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(19,1,1,"a","22/02/2022","02.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(20,1,1,"a","22/02/2022","02.15",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }

        firebaseDB.collection("TimeSlots")
            .add(Appointment(21,1,1,"a","22/02/2022","02,30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(22,1,1,"a","22/02/2022","02.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(23,1,1,"a","22/02/2022","03.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(24,1,1,"a","22/02/2022","03.15",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(25,1,1,"a","22/02/2022","03.30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(26,1,1,"a","22/02/2022","03.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(27,1,1,"a","22/02/2022","04.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }

        ////////////////////////////////////////////////////////////////////

        firebaseDB.collection("TimeSlots")
            .add(Appointment(28,1,1,"e","22/02/2022","06.15",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(29,1,1,"e","22/02/2022","06.30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(30,1,1,"e","22/02/2022","06.45",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(31,1,1,"e","22/02/2022","07.00",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(32,1,1,"e","22/02/2022","07.15",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(33,1,1,"e","22/02/2022","07.30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(34,1,1,"e","22/02/2022","07.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(35,1,1,"e","22/02/2022","08.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(36,1,1,"e","22/02/2022","08.15",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }

        firebaseDB.collection("TimeSlots")
            .add(Appointment(37,1,1,"e","22/02/2022","08,30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(38,1,1,"e","22/02/2022","08.45",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(39,1,1,"e","22/02/2022","09.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(40,1,1,"e","22/02/2022","09.15",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(41,1,1,"e","22/02/2022","09.30",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(42,1,1,"e","22/02/2022","09.45",true))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }
        firebaseDB.collection("TimeSlots")
            .add(Appointment(43,1,1,"e","22/02/2022","10.00",false))

            .addOnSuccessListener {
                Log.i("Successful","Addition of Record")
            }
            .addOnFailureListener {
                Log.i("Error","Adding Record")
            }



    }

    fun getCategoryList():ArrayList<Category>{
        var categoryList =  ArrayList<Category>()
        firebaseDB.collection("Categories")
            .orderBy("id", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { results->

                for (document in results){
                    categoryList.add(document.toObject(Category::class.java))
                }
                Log.i("Successful","Fetching of Record")

            }
            .addOnFailureListener {
                Log.i("Error","Fetching Record")
            }

        return categoryList
    }

    suspend fun getDoctorList(category_id: Int):ArrayList<Doctors>{
        Log.i("category_id", "category_id=$category_id")
        var doctorList = ArrayList<Doctors>()
        val collection = firebaseDB.collection("Doctors")
        var collectionReference:Query = collection
                if (category_id!=0){
                    collectionReference = collection.whereEqualTo("category_id", category_id)
                }

        collectionReference.orderBy("id", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result->
                for (document in result){
                    doctorList.add(document.toObject(Doctors::class.java))
                }
                Log.i("Successful","Fetching of Doctors Record")

            }

            .addOnFailureListener {
                Log.i("Error","Fetching Doctors Record")
            }

        return  doctorList
    }

    suspend fun getNotifications():ArrayList<Notification>{
        var notificationList = ArrayList<Notification>()
        val collection = firebaseDB.collection("Notifications")
        collection.orderBy("id", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result->
                for (document in result){
                    notificationList.add(document.toObject(Notification::class.java))
                }
                Log.i("Successful","Fetching of notificationList Record")

            }

            .addOnFailureListener {
                Log.i("Error","Fetching notificationList Record")
            }

        return  notificationList
    }

    suspend fun getTimeSlots(date:String, doctorId: Int):ArrayList<Appointment>{
        var timeSlotList = ArrayList<Appointment>()
        val collection = firebaseDB.collection("TimeSlots")
        var query = collection.whereEqualTo("date","22/02/2022")// hardcoded for demo
            //query = query.whereEqualTo(doctorId,1)//later add doctor Id filter
        query
            .orderBy("id", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result->
                for (document in result){
                    timeSlotList.add(document.toObject(Appointment::class.java))
                }
                Log.i("Successful","Fetching of getTimeSlots Record")

            }

            .addOnFailureListener {
                Log.i("Error","Fetching getTimeSlots Record")
            }

        return  timeSlotList
    }


//    suspend fun getTimeSlots():ArrayList<Appointment>{
////        var appointmentList = ArrayList<Appointment>()
////        appointmentList.add(Appointment(1, "m", "22/02/2022","9.30",false))
////        appointmentList.add(Appointment(2, "10.30",false))
////        appointmentList.add(Appointment(3, "11.30",true))
////        appointmentList.add(Appointment(4, "12.30",false))
////        appointmentList.add(Appointment(5, "01.30",false))
////        appointmentList.add(Appointment(6, "02.30",true))
////        appointmentList.add(Appointment(7, "03.30",false))
////        appointmentList.add(Appointment(9, "09.30",false))
////        appointmentList.add(Appointment(10, "10.30",false))
////        appointmentList.add(Appointment(11, "11.30",true))
////        appointmentList.add(Appointment(12, "12.30",false))
////        appointmentList.add(Appointment(13, "01.30",false))
////        appointmentList.add(Appointment(14, "02.30",true))
////        appointmentList.add(Appointment(15, "03.30",false))
////        appointmentList.add(Appointment(16, "09.30",false))
////        appointmentList.add(Appointment(17, "10.30",false))
////        appointmentList.add(Appointment(18, "11.30",true))
////        appointmentList.add(Appointment(19, "12.30",false))
////        appointmentList.add(Appointment(20, "31.30",false))
////        appointmentList.add(Appointment(21, "32.30",true))
////        appointmentList.add(Appointment(22, "33.30",false))
////        val collection = firebaseDB.collection("TimeSlots")
////        collection.orderBy("id", Query.Direction.ASCENDING)
////            .get()
////            .addOnSuccessListener { result->
////                for (document in result){
////                    notificationList.add(document.toObject(Notification::class.java))
////                }
////                Log.i("Successful","Fetching of notificationList Record")
////
////            }
////
////            .addOnFailureListener {
////                Log.i("Error","Fetching notificationList Record")
////            }
//
//        return  appointmentList
//    }



}

