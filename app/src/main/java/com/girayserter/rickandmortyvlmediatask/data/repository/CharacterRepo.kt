package com.girayserter.rickandmortyvlmediatask.data.repository

import com.girayserter.rickandmortyvlmediatask.data.api.CharacterApi
import com.girayserter.rickandmortyvlmediatask.data.api.model.Result
import javax.inject.Inject

class CharacterRepo @Inject constructor(
    private val characterApi: CharacterApi
) {

    suspend fun getCharacters(page: Int): List<Result> {
        return characterApi.getCharacters(page).results
    }

    suspend fun loadNextPage(page: Int): List<Result> {
        return characterApi.getCharacters(page).results
    }

    suspend fun getCharacterById(id: Int): Result{
        return characterApi.getCharacterById(id)
    }


}