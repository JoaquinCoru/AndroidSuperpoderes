package com.joaquinco.marvelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MVCharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<MVCharacterLocal>>

    @Insert(onConflict = REPLACE)
    fun insertAll(characters: List<MVCharacterLocal>)

    @Query("SElECT COUNT(id) FROM characters")
    fun getNumberOfCharacters():Int

    @Update
    fun updateCharacter(character: MVCharacterLocal)
}