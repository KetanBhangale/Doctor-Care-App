package com.ketub4.mvvmdemowithdi.model

data class Category(val id: Int,
                    val name: String,
                    val description: String){

    constructor(): this(
        0,"",""
    )
}