package com.example.triviaapp.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaapp.util.DataOrException
import com.example.triviaapp.models.QuestionItem
import com.example.triviaapp.repository.TriviaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(private val triviaRepository: TriviaRepository) : ViewModel() {
    val fetchingTrivia = mutableStateOf(false)
    val data = mutableStateOf(DataOrException<ArrayList<QuestionItem>, Exception>(null, null))

    init {
        getAllTrivia()
    }

    private fun getAllTrivia() {
        viewModelScope.launch {
            fetchingTrivia.value = true
            data.value = triviaRepository.getAllQuestions()
            fetchingTrivia.value = false
        }
    }
}