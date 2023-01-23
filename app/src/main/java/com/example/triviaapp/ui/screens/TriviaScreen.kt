package com.example.triviaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.triviaapp.ui.components.TriviaItem

@Composable
fun TriviaScreen(viewModel: TriviaViewModel) {
    val data = viewModel.data.value
    val loading = viewModel.fetchingTrivia.value

    val questions = viewModel.data.value.data?.toMutableList()
    val currentIndex = remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (loading) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (data.error != null) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = data.error.toString())
            }
        } else {
            val currentQuestion = try {
                questions?.get(currentIndex.value)
            } catch (ex: Exception) {
                null
            }

            if (currentQuestion != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TriviaItem(
                        questionItem = currentQuestion,
                        index = currentIndex.value + 1,
                        outOff = questions!!.size,
                    )
                    Button(
                        onClick = { currentIndex.value += 1 },
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Next",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
