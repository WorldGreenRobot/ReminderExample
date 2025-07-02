package ru.ivan.reminder.ui.reminder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ivan.reminder.R

@Composable
fun InputForm(
    reminderText: String,
    date: String,
    time: String,
    modifier: Modifier = Modifier,
    onValueChangeReminder: (String) -> Unit = {},
    onClickDate: (String) -> Unit = {},
    onClickTime: () -> Unit = {},
    onValueChangeDate: (String) -> Unit = {},
    onValueChangeTime: (String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.dark_navy), RoundedCornerShape(15.dp))
            .border(0.5.dp, colorResource(id = R.color.blue), RoundedCornerShape(15.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.form_title),
            style = TextStyle(
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Thin
            )
        )

        ReminderTextField(
            value = reminderText,
            label = stringResource(R.string.new_reminder),
            onValueChange = onValueChangeReminder,
            modifier = Modifier.fillMaxWidth()
        )
        DateInputField(
            date = date,
            onValueChange = onValueChangeDate,
            onClickDate = onClickDate,
            modifier = Modifier
                .fillMaxWidth()
        )
        TimeInputFiled(
            time = time,
            onValueChange = onValueChangeTime,
            onClickTime = onClickTime,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = {

            },
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(id = R.color.button_gradient_purple),
                            colorResource(id = R.color.button_gradient_blue)
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = stringResource(R.string.form_create),
                color = Color.White,
                fontSize = 18.sp
            )
        }

    }
}

@Composable
@Preview
private fun InputFormPreview() {
    MaterialTheme {
        InputForm(
            "Напоминание",
            "12.12.2023",
            "12:00"
        )
    }
}
