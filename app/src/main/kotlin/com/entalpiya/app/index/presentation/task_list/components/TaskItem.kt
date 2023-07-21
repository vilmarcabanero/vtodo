package com.entalpiya.app.index.presentation.task_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entalpiya.app.index.domain.model.Task

@Composable
fun TaskItem(
    task: Task,
    handleToggleIsComplete: () -> Unit,
    navigateToAddEditTaskScreen: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToAddEditTaskScreen()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(10f)
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.body1,
                    textDecoration = if (task.isComplete) TextDecoration.LineThrough else null,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                )
            }

            if (!task.description.isNullOrBlank()) {
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.body2,
                    textDecoration = if (task.isComplete) TextDecoration.LineThrough else null,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp
                )
            }
        }

        Checkbox(checked = task.isComplete, onCheckedChange = { handleToggleIsComplete() })
    }
}

@Preview
@Composable
fun TaskItemPreview() {
//    Surface {
//        TodoItem(
//            "1",
//            "Task 1",
//            "This is task 1",
//            false,
//            {},
//            {},
//
//        )
//    }
}
