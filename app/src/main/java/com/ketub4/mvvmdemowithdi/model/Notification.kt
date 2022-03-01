package com.ketub4.mvvmdemowithdi.model

data class Notification(
    val id: Int,
    val categoryId: Int,
    val categoryName: String,
    val title:String,
    val message: String,
    val date: String,

) {
    constructor(): this(
        0,0,"","","",""
    )
}