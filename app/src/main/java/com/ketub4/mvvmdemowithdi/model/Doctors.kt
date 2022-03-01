package com.ketub4.mvvmdemowithdi.model

data class Doctors(
    val category_id: Int,
    val id: Int,
    val category: String,
    val name: String,
    val address: String,
    val distance: String,
    val rating: Int,

) {
   constructor(): this(
       0,0,"","","","",0
   )
}