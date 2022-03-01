package com.ketub4.mvvmdemowithdi.model

data class Appointment(
    val id: Int,
    val doctorId: Int,
    val categoryId: Int,
    val timeSlot: String,
    val date: String,
    val time: String,
    val booked: Boolean

) {
    constructor(): this(0,0,0,"","","", false)
}