package ru.ivan.reminder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import ru.ivan.reminder.R

@Composable
fun Screen(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.padding(it)
           /*     .background(
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.black),
                            colorResource(id = R.color.navy)
                        )
                    )
                )*/
        ) {
            content()
        }
    }
}