package ru.ivan.reminder.ui.reminder.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import ru.ivan.reminder.R

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
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = colorResource(id = R.color.blue),
            cursorColor = colorResource(id = R.color.blue),
            focusedPlaceholderColor = colorResource(id = R.color.blue),
            focusedLabelColor = colorResource(id = R.color.blue),
            unfocusedLabelColor = colorResource(id = R.color.blue),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}