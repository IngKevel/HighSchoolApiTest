package com.chilate.nychighschoolsapp.feature_highschools.di

import com.chilate.nychighschoolsapp.feature_highschools.data.remote.HighSchoolApi
import com.chilate.nychighschoolsapp.feature_highschools.data.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Kevel on 3/2/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHighSchoolApi(): HighSchoolApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(HighSchoolApi::class.java)
    }
}