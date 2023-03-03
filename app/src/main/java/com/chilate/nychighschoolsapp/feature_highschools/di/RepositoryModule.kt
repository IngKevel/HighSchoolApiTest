package com.chilate.nychighschoolsapp.feature_highschools.di

import com.chilate.nychighschoolsapp.feature_highschools.data.repository.HighSchoolRepositoryImpl
import com.chilate.nychighschoolsapp.feature_highschools.domain.repository.HighSchoolRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Kevel on 3/2/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun highSchoolRepository(repositoryImpl: HighSchoolRepositoryImpl): HighSchoolRepository
}