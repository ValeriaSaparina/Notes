package com.example.feature.folders.impl.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.yellowAccent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFolderDialog(onDismissRequest: () -> Unit, onSaveRequest: (String) -> Unit = {}) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val textFieldValue = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val visualTransformation = remember { VisualTransformation.None }
    val focusRequester = remember { FocusRequester() }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxHeight(0.90f),
        sheetState = sheetState,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cancel",
                color = yellowAccent,
                modifier = Modifier.clickable {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        Log.d("FOLDER_DIALOG", "cancel")
                        if (!sheetState.isVisible) {
                            onDismissRequest()
                        }
                    }
                })
            Text(
                text = "New Folder",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Done",
                color = yellowAccent, fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        onSaveRequest(textFieldValue.value)
                        if (!sheetState.isVisible) {
                            onDismissRequest()
                        }
                    }
                }
            )
        }

        BasicTextField(
            value = textFieldValue.value,
            onValueChange = { value ->
                textFieldValue.value = value
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .focusRequester(focusRequester),
            textStyle = TextStyle(fontSize = 16.sp),
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = textFieldValue.value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                placeholder = { Text("Folder's name") },
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        LaunchedEffect(key1 = Unit) {
            focusRequester.requestFocus()
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun NewFolderDialogPreview() {
//    var showBottomSheet by remember { mutableStateOf(false) }
//    NewFolderDialog(onDismissRequest = { showBottomSheet = false })
//}