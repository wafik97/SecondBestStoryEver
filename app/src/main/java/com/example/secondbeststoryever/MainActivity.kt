package com.example.secondbeststoryever

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.secondbeststoryever.ui.screens.Home
import com.example.secondbeststoryever.ui.screens.characters.CharacterDetailsScreen
import com.example.secondbeststoryever.ui.screens.characters.CharactersScreen
import com.example.secondbeststoryever.ui.screens.characters.CharactersViewModel
import com.example.secondbeststoryever.ui.theme.Dimens
import com.example.secondbeststoryever.ui.theme.SecondBestStoryEverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondBestStoryEverTheme {
                Scaffold { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { Home(navController) }
                        composable("characters") { CharactersScreen(navController) }
                        /*composable("character/{id}") { backStackEntry ->
                            val characterId = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
                            val character = CharactersViewModel().characters.value.firstOrNull { it.id == characterId }
                            if (character != null) {
                                CharacterDetailsScreen(character)
                            }
                        }*/
                    }
                }
            }
        }

    }
}



@Composable
fun Home(navController: NavController){

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(Dimens.screenPadding).verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.spacedBy(Dimens.elementSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Reject Ranger",
                fontSize = Dimens.titleFontSize,
                fontWeight = FontWeight.Bold
            )

            AsyncImage(
                model = stringResource(R.string.poster),
                contentDescription = "Manga Poster",
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                contentScale = ContentScale.Fit
            )

            /*val buttons = listOf(
                R.string.b1,
                R.string.b2,
                R.string.b3,
                R.string.b4
            )

            buttons.forEach { resId ->
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(Dimens.buttonHeight)
                ) {
                    Text(
                        stringResource(resId),
                        fontSize = Dimens.buttonFontSize
                    )
                }
            }*/

            Button(
                onClick = { navController.navigate("characters") },
                modifier = Modifier.fillMaxWidth().height(Dimens.buttonHeight)
            ) {
                Text(
                    stringResource(R.string.b1),
                    fontSize = Dimens.buttonFontSize
                )
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(Dimens.buttonHeight)
            ) {
                Text(
                    stringResource(R.string.b2),
                    fontSize = Dimens.buttonFontSize
                )
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(Dimens.buttonHeight)
            ) {
                Text(
                    stringResource(R.string.b3),
                    fontSize = Dimens.buttonFontSize
                )
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(Dimens.buttonHeight)
            ) {
                Text(
                    stringResource(R.string.b4),
                    fontSize = Dimens.buttonFontSize
                )
            }

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