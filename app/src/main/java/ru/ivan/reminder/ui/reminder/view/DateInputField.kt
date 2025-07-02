package ru.ivan.reminder.ui.reminder.view

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun DateInputField(
    date: String,
    onValueChange: (String) -> Unit,
    onClickDate: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    TextField(
        value = date,
        onValueChange = onValueChange,
        label = { Text("Date") },
        modifier = modifier,
        interactionSource = remember { MutableInteractionSource() }.also {
            LaunchedEffect(it) {
                it.interactions.collect {
                    if (it is PressInteraction.Release) {
                        onClickDate(date)
                    }
                }
            }
        },
        readOnly = true,

    )
}