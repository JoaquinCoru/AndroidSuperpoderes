package com.joaquinco.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.joaquinco.marvelapp.data.local.MVCharacterDatabase
import com.joaquinco.marvelapp.data.local.MVCharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MVCharacterDatabase {
        return Room.databaseBuilder(
            context,
            MVCharacterDatabase::class.java, "mv-database"
        )
            .build()
    }

    @Provides
    fun provideDao(database: MVCharacterDatabase): MVCharacterDao {
        return database.getDao()
    }
}