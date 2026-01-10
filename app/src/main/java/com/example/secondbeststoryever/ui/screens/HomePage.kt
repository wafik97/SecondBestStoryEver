package com.example.secondbeststoryever.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.secondbeststoryever.Greeting
import com.example.secondbeststoryever.Home
import com.example.secondbeststoryever.ui.theme.SecondBestStoryEverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondBestStoryEverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun Home(){

/*
    Scaffold(
        topBar = {

        }
    ) { banana ->(





            )

    }*/

    Column() {

        Text(
            text = "Reject Ranger",
            fontSize = 40.sp
        )

        AsyncImage(
            model = "https://static.wikia.nocookie.net/ranger-reject-sentai/images/f/f5/Season_1_Cover.png/revision/latest?cb=20240305194156",
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier.size(250.dp)
        )

        Button(onClick = { }) {
            Text("Characters")
        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SecondBestStoryEverTheme {
        Home()
    }
}