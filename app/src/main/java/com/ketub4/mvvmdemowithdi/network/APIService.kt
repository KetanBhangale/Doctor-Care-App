package com.ketub4.mvvmdemowithdi.network

import com.ketub4.mvvmdemowithdi.model.MovieResult
import com.ketub4.mvvmdemowithdi.utils.Constant
import retrofit2.http.GET

interface APIService {

    @GET("/3/movie/popular?api_key="+Constant.API_KEY)
    suspend fun getListofMovies():MovieResult

}