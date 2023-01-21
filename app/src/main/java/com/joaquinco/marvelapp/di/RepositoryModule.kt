package com.joaquinco.marvelapp.di

import com.joaquinco.marvelapp.data.RepositoryImpl
import com.joaquinco.marvelapp.data.remote.RemoteDataSource
import com.joaquinco.marvelapp.data.remote.RemoteDataSourceImpl
import com.joaquinco.marvelapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}