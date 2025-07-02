package ru.ivan.reminder.ui.reminder.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ivan.reminder.R
import ru.ivan.reminder.domain.Reminder

@Composable
fun ReminderItem(
    reminder: Reminder,
    modifier: Modifier = Modifier,
    onClick: (Reminder) -> Unit = {},
    remove: (Reminder) -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(color = colorResource(R.color.dark), RoundedCornerShape(25.dp))
            .border(0.5.dp, colorResource(id = R.color.blue), RoundedCornerShape(25.dp))
            .padding(start = 10.dp)
            .clickable { onClick(reminder) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = reminder.text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "${reminder.date} ${reminder.time}",
            style = TextStyle(color = colorResource(id = R.color.blue))
        )

        IconButton(
            onClick = { remove(reminder) },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "Delete",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
@Preview
private fun ReminderItemPreview() {
    MaterialTheme {
        ReminderItem(
            reminder = Reminder(
                id = 1,
                text = "Reminder 1",
                date = "2023-01-01",
                time = "12:00"
            )
        )
    }
}