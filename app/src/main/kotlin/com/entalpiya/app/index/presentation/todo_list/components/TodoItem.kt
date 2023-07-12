package com.entalpiya.app.index.presentation.todo_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Composable
fun TodoItem(
    id: String,
    title: String,
    description: String?,
    isComplete: Boolean,
    handleToggleIsComplete: () -> Unit,
    navigateToAddEditTodoScreen: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToAddEditTodoScreen()
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
                    text = title,
                    style = MaterialTheme.typography.body1,
                    textDecoration = if (isComplete) TextDecoration.LineThrough else null,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400
                )
            }

            if (!description.isNullOrBlank()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    textDecoration = if (isComplete) TextDecoration.LineThrough else null,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp
                )
            }
        }

        Checkbox(checked = isComplete, onCheckedChange = { handleToggleIsComplete() })
    }
}

@Preview
@Composable
fun TodoItemPreview() {
//    Surface {
//        TodoItem(
//            "1",
//            "Todo 1",
//            "This is todo 1",
//            false,
//            {},
//            {},
//
//        )
//    }
}
