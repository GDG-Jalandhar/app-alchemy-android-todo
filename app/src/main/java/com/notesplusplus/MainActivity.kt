package com.notesplusplus

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notesplusplus.ui.theme.NotesPlusPlusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesPlusPlusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val list = remember { mutableStateListOf("Item 1", "Item 2", "item 3") }
    val setShowDialog = remember { mutableStateOf(false) }

    if(setShowDialog.value) {
        CustomDialog(setShowDialog = {
            setShowDialog.value = false
        }, returnText = {txt ->
            list.add(txt)
        })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "CRUD Operations") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                setShowDialog.value = true
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            for(i in 0 until list.size) {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = list[i])
                    Icon(Icons.Filled.Clear,
                        contentDescription = "Delete",
                        modifier = Modifier.clickable {
                            list.removeAt(i)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesPlusPlusTheme {
        Greeting("Android")
    }
}