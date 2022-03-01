package com.ketub4.mvvmdemowithdi.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.ketub4.mvvmdemowithdi.network.APIService
import com.ketub4.mvvmdemowithdi.network.FirebaseService

import com.ketub4.mvvmdemowithdi.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): APIService{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideFireStoreInstance():FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun getContext(@ApplicationContext context: Context):Context{
        return context
    }

}