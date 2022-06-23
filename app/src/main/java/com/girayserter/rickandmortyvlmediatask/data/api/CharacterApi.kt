package com.girayserter.rickandmortyvlmediatask.data.api

import com.girayserter.rickandmortyvlmediatask.data.api.model.Response
import com.girayserter.rickandmortyvlmediatask.data.api.model.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET(ApiConstants.END_POINT)
    suspend fun getCharacters(@Query("page") page: Int): Response

    @GET(ApiConstants.END_POINT+"/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Result
}