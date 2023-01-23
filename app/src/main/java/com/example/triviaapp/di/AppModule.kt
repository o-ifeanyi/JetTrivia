package com.example.triviaapp.di

import com.example.triviaapp.data.TriviaApi
import com.example.triviaapp.repository.TriviaRepository
import com.example.triviaapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideQuestionApi(): TriviaApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(TriviaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTriviaRepository(api: TriviaApi) : TriviaRepository = TriviaRepository(api)
}