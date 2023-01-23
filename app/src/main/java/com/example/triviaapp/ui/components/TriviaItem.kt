package com.example.triviaapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triviaapp.models.QuestionItem


@Composable
fun TriviaItem(questionItem: QuestionItem, index: Int, outOff: Int) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
    val selectedAnswer = remember { mutableStateOf("") }
    val isCorrectAnswer = remember {
        mutableStateOf<Boolean?>(null)
    }
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color.Blue, Color.Cyan
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                .clip(shape = RoundedCornerShape(25.dp))
                .background(color = Color.Transparent),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(index*0.005f)
                    .height(10.dp)
                    .padding(1.dp)
                    .background(brush = gradient),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold, fontSize = 30.sp
                )
            ) {
                this.append("Question $index/")
            }
            withStyle(
                style = SpanStyle()
            ) {
                this.append("$outOff")
            }
        })
        androidx.compose.foundation.Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(1.5.dp),
        ) {
            drawLine(
                color = Color.LightGray,
                start = Offset(0f, 0f),
                end = Offset(size.width, y = 0f),
                pathEffect = pathEffect,
            )
        }
        Text(
            text = questionItem.question,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))
        questionItem.choices.forEach {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(vertical = 5.dp)
                    .border(
                        width = 2.dp,
                        brush = gradient,
                        shape = RoundedCornerShape(15.dp),
                    ).clickable {
                        selectedAnswer.value = it
                        isCorrectAnswer.value = questionItem.answer == it
                    }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedAnswer.value == it,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = if (isCorrectAnswer.value == true) {
                                Color.Green
                            } else {
                                Color.Red
                            }
                        ),
                        onClick = {
                            selectedAnswer.value = it
                            isCorrectAnswer.value = questionItem.answer == it
                        },
                    )
                    Text(text = it, modifier = Modifier.padding(horizontal = 10.dp))
                }
            }
        }
    }
}

