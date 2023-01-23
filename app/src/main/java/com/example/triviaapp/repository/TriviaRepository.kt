package com.example.triviaapp.repository

import android.util.Log
import com.example.triviaapp.util.DataOrException
import com.example.triviaapp.models.QuestionItem
import com.example.triviaapp.data.TriviaApi
import javax.inject.Inject

class TriviaRepository @Inject constructor(private val api: TriviaApi) {

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Exception> {
        val response = DataOrException<ArrayList<QuestionItem>, Exception>()
        try {
            response.data = api.getAllQuestions()
        } catch (exc: Exception) {
            response.error = exc
            Log.d("TAG", "getAllQuestions: ${exc.localizedMessage}")
        }
        return response
    }

}