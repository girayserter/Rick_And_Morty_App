package com.girayserter.rickandmortyvlmediatask.di

import com.girayserter.rickandmortyvlmediatask.data.api.ApiConstants
import com.girayserter.rickandmortyvlmediatask.data.api.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): CharacterApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.BASE_URL)
            .build()
            .create(CharacterApi::class.java)
    }
}