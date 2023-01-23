package com.example.triviaapp.util

data class DataOrException<T, E: Exception>(
    var data: T? = null,
    var error: E? = null
)
