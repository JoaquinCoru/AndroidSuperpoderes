package com.joaquinco.marvelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joaquinco.marvelapp.data.local.MVCharacterDao
import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal

@Database(entities = [MVCharacterLocal::class], version = 1)
abstract class MVCharacterDatabase: RoomDatabase() {
    abstract fun getDao():MVCharacterDao
}