package ru.ivan.reminder.ui.reminder.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import ru.ivan.reminder.R

@Composable
fun ReminderTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
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
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = modifier.fillMaxWidth(),
    )
}