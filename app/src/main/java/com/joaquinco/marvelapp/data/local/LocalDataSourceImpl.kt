package com.joaquinco.marvelapp.data.local

import com.joaquinco.marvelapp.data.local.model.MVCharacterLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao:MVCharacterDao
): LocalDataSource {
    override fun getCharacters(): Flow<List<MVCharacterLocal>> {
        return dao.getAllCharacters()
    }

    override fun insertCharacters(characters: List<MVCharacterLocal>) {
        dao.insertAll(characters)
    }

    override fun getNumberOfCharacters(): Int {
        return dao.getNumberOfCharacters()
    }
}