package com.notesplusplus

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(setShowDialog: (Boolean) -> Unit, returnText: (String) -> Unit) {

    val textValue = remember { mutableStateOf("") }

    Dialog(onDismissRequest = {
        setShowDialog(false)
    }) {
        Column() {
            Text(text = "Add Value")
            OutlinedTextField(
                value = textValue.value,
                onValueChange = {
                    textValue.value = it
                })
            Button(onClick = {
                returnText(textValue.value)
                setShowDialog(false)
            }) {
                Text(text = "Add Value")
            }
        }
    }

}