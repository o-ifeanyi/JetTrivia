package com.example.triviaapp.data

import com.example.triviaapp.models.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TriviaApi {
    @GET("world.json")
    suspend fun getAllQuestions() : Question
}