package com.beeldi.beelding.di

import com.beeldi.beelding.data.repository.BeeldingRepositoryImplementation
import com.beeldi.beelding.domain.repository.BeeldingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BeeldingModule{

    @Provides
    @Singleton
    fun provideBeeldingRepository():BeeldingRepository{
        return BeeldingRepositoryImplementation()
    }
}